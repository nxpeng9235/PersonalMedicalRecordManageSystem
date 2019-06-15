package actor;

public class DiseaseHistory {

    private String pid;
    private String hbp;
    private String diab;
    private String chd;
    private String ci;
    private String lc;
    private String familyHistory;

    public void setPID(int n) {
        pid = String.valueOf(n);
    }

    public String getPID() {
        return pid;
    }

    public void setHBP(String s) {
        hbp = s;
    }

    public String getHBP() {
        return hbp;
    }

    public void setDIAB(String s) {
        diab = s;
    }

    public String getDIAB() {
        return diab;
    }

    public void setCHD(String s) {
        chd = s;
    }

    public String getCHD() {
        return chd;
    }

    public void setCI(String s) {
        ci = s;
    }

    public String getCI() {
        return ci;
    }

    public void setLC(String s) {
        lc = s;
    }

    public String getLC() {
        return lc;
    }

    public void setFamilyHistory(String s) {
        familyHistory = s;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }
    
}
