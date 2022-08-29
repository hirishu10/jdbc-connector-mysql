package JdbcMySQLConnector.Concreate;

import JdbcMySQLConnector.Interface.JDBCConnectorConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCMySQLConnection implements JDBCConnectorConfiguration {

    private Connection connection;
    private Statement statement;

    @Override
    public void createConnection(String url, String userName, String passWord) {
        try{

            Connection connection = DriverManager.getConnection(url,userName,passWord);

            if(!connection.isClosed()){
                this.connection = connection;
                System.out.println("Connection created successfully.");
            }
            else{
                System.out.println("Something went wrong while creating connection.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
            if(connection.isClosed()){
                System.out.println("Connection closed successfully.");
            }
            else{
                System.out.println("Something went wrong while closing connection.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void closeStatement(){
        try{
            this.statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(String tableName, String tableConfig) {
        try{

            // CREATE TABLE "TableName" ("TableCol");
            String sqlRaw = "CREATE TABLE " + tableName + " ( " + tableConfig + " );";

            this.statement = connection.createStatement();

            statement.executeUpdate(sqlRaw);

            System.out.println("Table created successfully.");

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertDataInsideTable(String tableName, String tableDataConfig, String tableDataConfigValue) {
        try{

            // INSERT INTO "TableName" ("TableColumn") VALUES ("ColumnValue");
            String sqlRaw = "INSERT INTO " + tableName + " ( " +
                            tableDataConfig + " )" + " VALUES (" + tableDataConfigValue + " );";

            this.statement = connection.createStatement();

            int condition = statement.executeUpdate(sqlRaw);

            if(condition == 1){
                System.out.println("Data Inserted Successfully");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(String tableName, String setterColumn, String uniqueSelector) {
        try{
            String updateSQL = "UPDATE " + tableName + " SET " +
                    setterColumn + " WHERE " + uniqueSelector;
            this.statement = connection.createStatement();
            this.statement.executeUpdate(updateSQL);
            System.out.println("Data updated successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showTables() {
        try{

        String query = "SHOW TABLES;";

        this.statement = connection.createStatement();

        ResultSet set = statement.executeQuery(query);

        int index = 1;
        System.out.println("Table List Below :");
        while (set.next()){
            System.out.println(index + ": " + set.getString(1));
            index++;
        }

        set.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet getAllData(String tableName) {
        try{
            String query = "SELECT * FROM " + tableName + ";";
            this.statement = connection.createStatement();
            return this.statement.executeQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet otherQuerySQL(String SQLSyntax) {
        try{
            this.statement = connection.createStatement();
            return this.statement.executeQuery(SQLSyntax);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void otherOperationSQL(String SQLSyntax) {
        try{
            this.statement = connection.createStatement();
            this.statement.executeUpdate(SQLSyntax);
            System.out.println("SQL executed successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}