package by.ageenko.web.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    static Logger logger = LogManager.getLogger();
    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);
    private BlockingDeque<ProxyConnection> free = new LinkedBlockingDeque<>(8);
    private BlockingDeque<ProxyConnection> used = new LinkedBlockingDeque<>(8);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.error("The driver was not registered");
        }
    }

    private ConnectionPool() throws InterruptedException {
        final String url = "jdbc:mysql://localhost:3306/userdb";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "root");
        for (int i = 0; i < 8; i++) {
            Connection connection = createConnection(url,properties);
            free.add((ProxyConnection) connection);
        }
    }

    public static ConnectionPool getInstance() {
        if (!create.get()){
            lock.lock();
            try {
                if (instance == null){
                    instance = new ConnectionPool();
                    create.getAndSet(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            logger.error("Connection was not taken", e);
        }
        return connection;
    }
    public boolean releaseConnection(Connection connection){
        boolean match = true;
        try {
            match = used.remove(connection);
            if (match){
                free.put((ProxyConnection) connection);
            }
        } catch (InterruptedException e) {
            logger.error("Failed to release the connection to the pool",e);
        }
        return match;
    }
    public Connection createConnection(String url, Properties properties){
        Connection connection = null;
        try {
            connection = new ProxyConnection(DriverManager.getConnection(url,properties));
        } catch (SQLException e) {
            logger.error("Failed to create connection");
        }
        return connection;
    }
}
