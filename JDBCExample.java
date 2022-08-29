package JdbcMySQLConnector;

import JdbcMySQLConnector.Concreate.JDBCCredentials;
import JdbcMySQLConnector.Concreate.JDBCMySQLConnection;

import java.sql.ResultSet;

public class JDBCExample {

    public static void main(String[] args) {

        var credentials = JDBCCredentials.getCredentials();

        JDBCMySQLConnection mySQLConnection = new JDBCMySQLConnection();
        mySQLConnection.createConnection(credentials.getUrl(),credentials.getUserName(),credentials.getPassWord());


        mySQLConnection.showTables();


        //::> Print All Data // Working
        ResultSet set = mySQLConnection.getAllData("testingNewTable");
        try{
            while (set.next()) {
                System.out.println("Id: " + set.getInt(1) + ", Name: " + set.getString(2));
            }
            set.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }


        //::> Create Table //working
        String tableCol = "Id INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(20)";
        mySQLConnection.createTable("testingNewTable",tableCol);

        //::> Inserting Table //working
        mySQLConnection.insertDataInsideTable("testingNewTable","Name","\"India\"");

        //::> Update Table //working
        mySQLConnection.updateTable("testingNewTable","Name=\"Delhi\"","id=1");


        //::> Other Query
        try{
            String query = "SELECT * FROM testingNewTable where id=2;";
            ResultSet otherSet = mySQLConnection.otherQuerySQL(query);
            System.out.println("Data Below:");
            while (otherSet.next()) {
                System.out.println("Id: " + otherSet.getInt(1) + ", Name: " + otherSet.getString(2));
            }
            otherSet.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        //::> Other Operation
        try{
            String query = "UPDATE testingNewTable SET Name=\"Uhsir\" where id=1;";
            mySQLConnection.otherOperationSQL(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        mySQLConnection.closeStatement();
        mySQLConnection.closeConnection();
    }
}