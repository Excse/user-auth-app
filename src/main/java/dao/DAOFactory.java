package dao;

import util.JNDIFactory;

public class DAOFactory {

    private static final JNDIFactory JNDI_FACTORY = JNDIFactory.getInstance();

    public static UserDAOImpl getUserDAO() {
        try {
            boolean is_release = JNDI_FACTORY.getEnvironmentAsBoolean("release");
            if (!is_release) {
                return new UserDAOImpl("jdbc/local");
            } else {
                return new UserDAOImpl("jdbc/production");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
