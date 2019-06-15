package actor;

import java.sql.Date;

public class BodyCheck {

    private String id;
    private String pid;
    private String date;
    private String height;
    private String weight;
    private String waistline;
    private String pulse;
    private String sbp;
    private String dbp;
    private String tc;
    private String triglyceride;
    private String ldl;
    private String hdl;
    private String fbg;
    private String pbg;

    public void setID(int n) {
        id = String.valueOf(n);
    }

    public String getID() {
        return id;
    }

    public void setPID(int n) {
        pid = String.valueOf(n);
    }

    public String getPID() {
        return pid;
    }

    public void setDate(Date d) {
        date = d.toString();
    }

    public String getDate() {
        return date;
    }

    public void setHeight(String s) {
        height = s;
    }

    public String getHeight() {
        return height;
    }

    public void setWeight(String s) {
        weight = s;
    }

    public String getWeight() {
        return weight;
    }

    public void setWaistline(int n) {
        waistline = String.valueOf(n);
    }

    public String getWaistline() {
        return waistline;
    }

    public void setPulse(int n) {
        pulse = String.valueOf(n);
    }

    public String getPulse() {
        return pulse;
    }

    public void setSBP(String s) {
        sbp = s;
    }

    public String getSBP() {
        return sbp;
    }

    public void setDBP(String s) {
        dbp = s;
    }

    public String getDBP() {
        return dbp;
    }

    public void setTC(String s) {
        tc = s;
    }

    public String getTC() {
        return tc;
    }

    public void setTriglyceride(String s) {
        triglyceride = s;
    }

    public String getTriglyceride() {
        return triglyceride;
    }

    public void setLDL(String s) {
        ldl = s;
    }

    public String getLDL() {
        return ldl;
    }

    public void setHDL(String s) {
        hdl = s;
    }

    public String getHDL() {
        return hdl;
    }

    public void setFBG(String s) {
        fbg = s;
    }

    public String getFBG() {
        return fbg;
    }

    public void setPBG(String s) {
        pbg = s;
    }

    public String getPBG() {
        return pbg;
    }
    
}
