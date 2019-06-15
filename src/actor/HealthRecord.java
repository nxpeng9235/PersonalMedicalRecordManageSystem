package actor;

import java.sql.Date;

public class HealthRecord {

    private String id;
    private String PID;
    private String DID;
    private String date;
    private String feature;
    private String yx;
    private String jy;
    private String diagnosis;
    private String measures;
    private String advice;

    public void setID(int n) {
        id = String.valueOf(n);
    }

    public String getID() {
        return id;
    }

    public void setPID(int n) {
        PID = String.valueOf(n);
    }

    public String getPID() {
        return PID;
    }

    public void setDID(int n) {
        DID = String.valueOf(n);
    }

    public String getDID() {
        return DID;
    }

    public void setDate(Date d) {
        date = d.toString();
    }

    public String getDate() {
        return date;
    }

    public void setFeature(String s) {
        feature = s;
    }

    public String getFeature() {
        return feature;    
    }

    public void setYX(String s) {
        yx = s;
    }

    public String getYX() {
        return yx;
    }

    public void setJY(String s) {
        jy = s;
    }

    public String getJY() {
        return jy;
    }

    public void setDiagnosis(String s) {
        diagnosis = s;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setMeasures(String s) {
        measures = s;
    }

    public String getMeasures() {
        return measures;
    }

    public void setAdvice(String s) {
        advice = s;
    }

    public String getAdvice() {
        return advice;
    }

}
