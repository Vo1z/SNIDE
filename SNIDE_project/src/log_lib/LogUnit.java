package log_lib;

import autilities.SnideUtils;

public class LogUnit
{
    private double logID;
    private LogType logType;
    private String time;
    private String description;
    private String invocationPlace;

    private static double logIDCounter = 0;

    public LogUnit(LogType logType, String description, Object invocationPlace)
    {
        logIDCounter++;

        this.logID = logIDCounter;
        this.logType = logType;
        this.time = SnideUtils.getCurrentTime();
        this.description = description;
        this.invocationPlace = getInvocationPlaceInformation(invocationPlace);
    }

    public LogUnit(Object place, String description)
    {
        logIDCounter++;

        this.logID = logIDCounter;
        this.logType = LogType.NOTIFICATION;
        this.time = SnideUtils.getCurrentTime();
        this.description = description;
        this.invocationPlace = getInvocationPlaceInformation(place);
    }

    public LogUnit(Object place, LogType logType)
    {
        logIDCounter++;

        this.logID = logIDCounter;
        this.logType = logType;
        this.time = SnideUtils.getCurrentTime();
        this.description = "NONE";
        this.invocationPlace = getInvocationPlaceInformation(place);
    }

    public LogUnit(Object place)
    {
        logIDCounter++;

        this.logID = logIDCounter;
        this.logType = LogType.NOTIFICATION;
        this.time = SnideUtils.getCurrentTime();
        this.description = "NONE";
        this.invocationPlace = getInvocationPlaceInformation(place);
    }

    public String getInvocationPlaceInformation(Object place)
    {
        String placeInfo = "";

        placeInfo += "[ " + "Class name: " + place.getClass().getSimpleName() + " | " +
                        "Package name: " + place.getClass().getPackageName() + " ]";

        return placeInfo;
    }

    @Override
    public String toString()
    {
        return "LOG #" + this.logID + "\n" +
                "Type:" + this.logType + "\n" +
                "Invocation time " + this.time + "\n" +
                "Description: " + this.description + "\n" +
                "Invocation place: " + this.invocationPlace + "\n" + "\n";
    }
}
