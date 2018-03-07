/*
 * The MIT License
 *
 * Copyright 2018 George <mrgeorge.ro @ gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ro.anost.jdbcconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class JDBCConnectionManager {
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static PreparedStatement ppstm = null;
    private static Connection connection = null;
    private static String sqlUser = "anost";
    private static String sqlPasswd = "anost";
    private static String sqlUrl = "jdbc:derby://localhost:1527/anost_db;create=true";
    private static String driver = "org.apache.derby.jdbc.ClientDriver";
    
    public static Connection getJDBCConnection(){
        try{
            Class driverClass = Class.forName(driver);
            try{
                connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPasswd);                
            }catch(SQLException ex){
                System.out.println("Failed to establish database connection.");
                java.util.logging.Logger.getLogger(JDBCConnectionManager.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }catch(ClassNotFoundException ex){
            System.out.println("Driver not found.");
            java.util.logging.Logger.getLogger(JDBCConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    public static void closeJDBCConnection(){
        if (resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException ex){
                    System.out.println("Failed to close resultSet.");
                    Logger.getLogger(JDBCConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        if (statement != null){
            try{
                statement.close();
            } catch (SQLException ex){
                System.out.println("Failed to close statement.");
                Logger.getLogger(JDBCConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ppstm != null){
            try{
                ppstm.close();
            } catch (SQLException ex){
                System.out.println("Failed to close prepared statement.");
                Logger.getLogger(JDBCConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (connection != null){
            try{
                connection.close();
            } catch(SQLException ex){
                System.out.println("Failed to close connection.");
                Logger.getLogger(JDBCConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}