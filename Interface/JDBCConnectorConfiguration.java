package JdbcMySQLConnector.Interface;

import java.sql.ResultSet;

public interface JDBCConnectorConfiguration {

    /**
     * Create Connection with database
     * @param url Connection URL to database
     * @param userName username
     * @param passWord password
     */
    void createConnection(String url,String userName,String passWord);

    /**
     * Connection will be closed while calling this function.
     */
    void closeConnection();

    /**
     * Statement will be closed while calling this function.
     */
    void closeStatement();

    /**
     * Create Table inside the database
     * @param tableName  Table Name to create
     * @param tableConfig Column details with comma ( , )
     */
    void createTable(String tableName,String tableConfig);

    /**
     * Insert Data inside the selected Table
     * @param tableName Table name to select to insert
     * @param tableDataConfig Each column name with comma ( , )
     * @param tableDataConfigValue Each column value with comma ( , )
     */
    void insertDataInsideTable(String tableName,String tableDataConfig,String tableDataConfigValue);

    /**
     * Updating Data inside the selected Table
     * @param tableName Table name to select to update
     * @param setterColumn Column name with new value
     * @param uniqueSelector Primary key or unique selector for updating
     */
    void updateTable(String tableName,String setterColumn,String uniqueSelector);

    /**
     * Prints all the Table inside the database
     */
    void showTables();

    /**
     * Get all the data from Tables inside the database
     * @param tableName Table name to print all the data
     * @return ResultSet return all data from the given table
     */
    ResultSet getAllData(String tableName);

    /**
     * Run for Query the data
     * @param SQLSyntax SQL Syntax to query any details and print the same
     * Note: Make sure to insert correct syntax otherwise throws Exception
     */
    ResultSet otherQuerySQL(String SQLSyntax);

    /**
     * Run for INSERTING, UPDATING, DELETING any other SQL Operation.
     * @param SQLSyntax SQL Syntax to do operation
     * Note: Make sure to insert correct syntax otherwise throws Exception
     */
    void otherOperationSQL(String SQLSyntax);
}