package actor;

public class Doctor extends User {

    private String sex;
    private String dept;
    private String hospital;
    private String title;
    private int status;
    
    public void setSex(String s) {
        sex = s;
    }

    public String getSex() {
        return sex;
    }

    public void setDept(String s) {
        dept = s;
    }

    public String getDept() {
        return dept;
    }

    public void setHospital(String s) {
        hospital = s;
    }

    public String getHospital() {
        return hospital;
    }
    
    public void setTitle(String s) {
        title = s;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(int n) {
        status = n;
    }

    public int getStatus() {
        return status;
    }

    public String toString() {
        String str = "";
        str += "医生编号：" + id + '\n';
        str += "用  户  名：" + name + '\n';
        str += "性        别：" + ((sex == null) ? "未输入" : sex) + '\n';
        str += "所属科室：" + ((dept == null) ? "未输入" : dept) + '\n';
        str += "所属医院：" + ((hospital == null) ? "未输入" : hospital) + '\n';
        str += "职        称：" + ((title == null) ? "未输入" : title) + '\n';

        return str;
    }

}
