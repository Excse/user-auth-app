package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JNDIFactory {

    private static JNDIFactory instance = null;

    protected JNDIFactory() {
    }

    public Connection getConnection(String source) throws NamingException, SQLException {
        Context initalContext = new InitialContext();

        Context enviromentContext = (Context) initalContext.lookup("java:/comp/env");
        if (enviromentContext == null) {
            throw new NamingException("InitialContext lookup wrong");
        }

        DataSource dataSource = (DataSource) enviromentContext.lookup(source);
        if (dataSource == null) {
            throw new NamingException("No Datasource");
        }

        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("No Connection found");
        }

        return connection;
    }

    public Object getEnvironment(String name) throws NamingException {
        InitialContext initialContext = new InitialContext();
        Context enviromentContext = (Context) initialContext.lookup("java:comp/env");

        try {
            return enviromentContext.lookup(name);
        } catch (NamingException exception) {
            System.out.println("Environment '" + name + "' is missing.");
            return null;
        }
    }

    public static synchronized JNDIFactory getInstance() {
        if (instance == null) {
            instance = new JNDIFactory();
        }

        return instance;
    }

}
