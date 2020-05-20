package com.hamza.firestoreadmin;

public class pendingShifts {

     String username;
     String userID;
     String shiftName;
     String location;
     String date;
     String status;
     String ShiftID;


    public pendingShifts(String username, String shiftName, String location, String date, String userID, String status, String ShiftID) {
        this.username = username;
        this.shiftName = shiftName;
        this.location = location;
        this.date = date;
        this.userID = userID;
        this.status = status;
        this.ShiftID = ShiftID;
    }

    public String getShiftID() {
        return ShiftID;
    }

    public void setShiftID(String shiftID) {
        ShiftID = shiftID;
    }

    public String getUserID() {
        return userID;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getShiftName() {
        return shiftName;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public pendingShifts()
    {


    }

}
