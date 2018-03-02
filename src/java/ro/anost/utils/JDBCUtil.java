/*
 * The MIT License
 *
 * Copyright 2018 George <mrgeorge.ro@gmail.com>.
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
package ro.anost.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import ro.anost.beans.FbEvents;

/**
 *
 * @author George <mrgeorge.ro@gmail.com>
 */
public class JDBCUtil {
    public static List<FbEvents> displayEvents(Connection conn) throws SQLException{
        String query = "SELECT EVENT_ID, NAME, CITY, PLACE, ATTENDING_COUNT, INTERESTED_COUNT, START_DATE, START_TIME, END_DATE, END_TIME, LAST_UPDATE, URL FROM FB_EVENT_DETAILS ORDER BY START_DATE DESC";
        PreparedStatement pstm = conn.prepareStatement(query);
        ResultSet rs = pstm.executeQuery();
        List<FbEvents> eventsList = new LinkedList<>();
        while (rs.next()){
            String eventId = rs.getString("EVENT_ID");
            String eventName = rs.getString("NAME");
            String eventCity = rs.getString("CITY");
            String eventPlace = rs.getString("PLACE");
            int eventAttendingCount = rs.getInt("ATTENDING_COUNT");
            long eventInterestedCount = rs.getLong("INTERESTED_COUNT");
            Date eventStartDate = rs.getDate("START_DATE");
            Time eventStartTime = rs.getTime("START_TIME");
            Date eventEndDate = rs.getDate("END_DATE");
            Time eventEndTime = rs.getTime("END_TIME");
            String eventUrl = rs.getString("URL");
            Timestamp eventTimestamp = rs.getTimestamp("LAST_UPDATE");
            FbEvents fbEvent = new FbEvents(eventId, eventName, eventCity, eventPlace, eventAttendingCount, eventInterestedCount, eventStartDate, eventStartTime, eventEndDate, eventEndTime, eventUrl, eventTimestamp);
            fbEvent.setFbEventId(eventId);
            fbEvent.setFbEventName(eventName);
            fbEvent.setFbEventCity(eventCity);
            fbEvent.setFbEventPlace(eventPlace);
            fbEvent.setFbEventAttendingCount(eventAttendingCount);
            fbEvent.setFbEventInterestedCount(eventInterestedCount);
            fbEvent.setFbEventStartDate(eventStartDate);
            fbEvent.setFbEventStartTime(eventStartTime);
            fbEvent.setFbEventEndDate(eventEndDate);
            fbEvent.setFbEventEndTime(eventEndTime);
            fbEvent.setFbEventUrl(eventUrl);
            fbEvent.setFbEventTimestamp(eventTimestamp);
            eventsList.add(fbEvent);
        }
        return eventsList;
    }
}
