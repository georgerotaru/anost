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
package ro.anost.beans;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author George <mrgeorge.ro@gmail.com>
 */
public class FbEvents {
    private String fbEventId;
    private String fbEventName;
    private String fbEventCity;
    private String fbEventPlace;
    private String fbEventCountry;
    private int fbEventAttendingCount;
    private long fbEventInterestedCount;
    private double fbEventLatitude;
    private double fbEventLongitude;
    private Date fbEventStartDate;
    private Time fbEventStartTime;
    private Date fbEventEndDate;
    private Time fbEventEndTime;
    private String fbEventUrl;
    private Timestamp fbEventTimestamp;
    private String fbEventDescription;

    public FbEvents(String fbEventId, String fbEventName, String fbEventCity, String fbEventPlace, int fbEventAttendingCount, long fbEventInterestedCount, Date fbEventStartDate, Time fbEventStartTime, Date fbEventEndDate, Time fbEventEndTime, String fbEventUrl, Timestamp fbEventTimestamp) {
        this.fbEventId = fbEventId;
        this.fbEventName = fbEventName;
        this.fbEventCity = fbEventCity;
        this.fbEventPlace = fbEventPlace;
        this.fbEventAttendingCount = fbEventAttendingCount;
        this.fbEventInterestedCount = fbEventInterestedCount;
        this.fbEventStartDate = fbEventStartDate;
        this.fbEventStartTime = fbEventStartTime;
        this.fbEventEndDate = fbEventEndDate;
        this.fbEventEndTime = fbEventEndTime;
        this.fbEventUrl = fbEventUrl;
        this.fbEventTimestamp = fbEventTimestamp;
    }

    public FbEvents(String fbEventId, String fbEventName, String fbEventCity, String fbEventPlace, String fbEventCountry, int fbEventAttendingCount, long fbEventInterestedCount, double fbEventLatitude, double fbEventLongitude, Date fbEventStartDate, Time fbEventStartTime, Date fbEventEndDate, Time fbEventEndTime, String fbEventUrl, Timestamp fbEventTimestamp, String fbEventDescription) {
        this.fbEventId = fbEventId;
        this.fbEventName = fbEventName;
        this.fbEventCity = fbEventCity;
        this.fbEventPlace = fbEventPlace;
        this.fbEventCountry = fbEventCountry;
        this.fbEventAttendingCount = fbEventAttendingCount;
        this.fbEventInterestedCount = fbEventInterestedCount;
        this.fbEventLatitude = fbEventLatitude;
        this.fbEventLongitude = fbEventLongitude;
        this.fbEventStartDate = fbEventStartDate;
        this.fbEventStartTime = fbEventStartTime;
        this.fbEventEndDate = fbEventEndDate;
        this.fbEventEndTime = fbEventEndTime;
        this.fbEventUrl = fbEventUrl;
        this.fbEventTimestamp = fbEventTimestamp;
        this.fbEventDescription = fbEventDescription;
    }

    public String getFbEventId() {
        return fbEventId;
    }

    public void setFbEventId(String fbEventId) {
        this.fbEventId = fbEventId;
    }

    public String getFbEventName() {
        return fbEventName;
    }

    public void setFbEventName(String fbEventName) {
        this.fbEventName = fbEventName;
    }

    public String getFbEventCity() {
        return fbEventCity;
    }

    public void setFbEventCity(String fbEventCity) {
        this.fbEventCity = fbEventCity;
    }

    public String getFbEventPlace() {
        return fbEventPlace;
    }

    public void setFbEventPlace(String fbEventPlace) {
        this.fbEventPlace = fbEventPlace;
    }

    public String getFbEventCountry() {
        return fbEventCountry;
    }

    public void setFbEventCountry(String fbEventCountry) {
        this.fbEventCountry = fbEventCountry;
    }

    public int getFbEventAttendingCount() {
        return fbEventAttendingCount;
    }

    public void setFbEventAttendingCount(int fbEventAttendingCount) {
        this.fbEventAttendingCount = fbEventAttendingCount;
    }

    public long getFbEventInterestedCount() {
        return fbEventInterestedCount;
    }

    public void setFbEventInterestedCount(long fbEventInterestedCount) {
        this.fbEventInterestedCount = fbEventInterestedCount;
    }

    public double getFbEventLatitude() {
        return fbEventLatitude;
    }

    public void setFbEventLatitude(double fbEventLatitude) {
        this.fbEventLatitude = fbEventLatitude;
    }

    public double getFbEventLongitude() {
        return fbEventLongitude;
    }

    public void setFbEventLongitude(double fbEventLongitude) {
        this.fbEventLongitude = fbEventLongitude;
    }

    public Date getFbEventStartDate() {
        return fbEventStartDate;
    }

    public void setFbEventStartDate(Date fbEventStartDate) {
        this.fbEventStartDate = fbEventStartDate;
    }

    public Time getFbEventStartTime() {
        return fbEventStartTime;
    }

    public void setFbEventStartTime(Time fbEventStartTime) {
        this.fbEventStartTime = fbEventStartTime;
    }

    public Date getFbEventEndDate() {
        return fbEventEndDate;
    }

    public void setFbEventEndDate(Date fbEventEndDate) {
        this.fbEventEndDate = fbEventEndDate;
    }

    public Time getFbEventEndTime() {
        return fbEventEndTime;
    }

    public void setFbEventEndTime(Time fbEventEndTime) {
        this.fbEventEndTime = fbEventEndTime;
    }

    public String getFbEventUrl() {
        return fbEventUrl;
    }

    public void setFbEventUrl(String fbEventUrl) {
        this.fbEventUrl = fbEventUrl;
    }

    public Timestamp getFbEventTimestamp() {
        return fbEventTimestamp;
    }

    public void setFbEventTimestamp(Timestamp fbEventTimestamp) {
        this.fbEventTimestamp = fbEventTimestamp;
    }

    public String getFbEventDescription() {
        return fbEventDescription;
    }

    public void setFbEventDescription(String fbEventDescription) {
        this.fbEventDescription = fbEventDescription;
    }

}
