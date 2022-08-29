package JdbcMySQLConnector.Concreate;

public class JDBCCredentials {

    public static JDBCCredentials instance = new JDBCCredentials();

    private static final String connector = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/rishu";
    private static final String userName = "root";
    private static final String passWord = "root@1234";

    private JDBCCredentials() {
        // No one initialise this constructor Singleton Pattern
    }

    public static JDBCCredentials getCredentials(){
        return instance;
    }

    public String getConnector(){
        return connector;
    }

    public String getUrl(){
        return url;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassWord(){
        return passWord;
    }
}
