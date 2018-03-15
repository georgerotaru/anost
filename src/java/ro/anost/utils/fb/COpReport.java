/*
 * The MIT License
 *
 * Copyright 2018 George.
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

package ro.anost.utils.fb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.anost.jdbcconn.JDBCConnectionManager;

/**
 *
 */
public class COpReport {
    private String[] checkBoxes;
    private String copOption;
    private String message;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private PreparedStatement ppstm = null;
    private Connection connection = null;

    public COpReport(String[] checkBoxes, String copOption) {
        try{
            connection = JDBCConnectionManager.getJDBCConnection();
            statement = connection.createStatement();
            String query = "UPDATE FB_EVENT_REPORTSTATUS SET REPORT_STATUS=?, LAST_UPDATE=? WHERE EVENT_ID=?";
            ppstm = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            for(String parseEvents : checkBoxes){
                java.util.Date nowDateJava = new java.util.Date();
                ppstm.setString(1, copOption);
                ppstm.setTimestamp(2, new java.sql.Timestamp(nowDateJava.getTime()));
                ppstm.setString(3, parseEvents);
                ppstm.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);
            JDBCConnectionManager.closeJDBCConnection();
            this.message = "Event(s) marked as being "+copOption+" for COp!";
        }catch (SQLException ex) {
            System.out.println("Could not update COp report status.");
            this.message = "Could not update COp report status. If problem persists, please contact the administrator.";
            Logger.getLogger(COpReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMessage() {
        return message;
    }
    
}
