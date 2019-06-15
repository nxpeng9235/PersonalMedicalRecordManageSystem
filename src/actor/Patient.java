package actor;

public class Patient extends User {

    private String sex;
    private String age;
    private String idNum;
    private String birthPlace;
    private String address;
    private String job;
    private String hys;
    private String allergy;
    private int status;

    public void setSex(String s) {
        sex = s;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(int n) {
        age = String.valueOf(n);
    }

    public String getAge() {
        return age;
    }

    public void setIDNum(String s) {
        idNum = s;
    }

    public String getIDNum() {
        return idNum;
    }

    public void setBirthPlace(String s) {
        birthPlace = s;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setAddress(String s) {
        address = s;
    }

    public String getAddress() {
        return address;
    }

    public void setJob(String s) {
        job = s;
    }

    public String getJob() {
        return job;
    }

    public void setHYS(String s) {
        hys = s;
    }

    public String getHYS() {
        return hys;
    }

    public void setAllergy(String s) {
        allergy = s;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setStatus(int n) {
        status = n;
    }

    public int getStatus() {
        return status;
    }

    public String toString() {
        String str = "";
        str += "患者编号：" + id + '\n';
        str += "用  户  名：" + name + '\n';
        str += "性        别：" + ((sex == null) ? "未输入" : sex) + '\n';
        str += "年        龄：" + ((age.equals("0")) ? "未输入" : age) + '\n';
        str += "身份证号：" + ((idNum == null) ? "未输入" : idNum) + '\n';
        str += "出  生  地：" + ((birthPlace == null) ? "未输入" : birthPlace) + '\n';
        str += "地        址：" + ((address == null) ? "未输入" : address) + '\n';
        str += "职        业：" + ((job == null) ? "未输入" : job) + '\n';
        str += "婚  育  史：" + ((hys == null) ? "未输入" : hys) + '\n';
        str += "过  敏  史：" + ((allergy == null) ? "未输入" : allergy) + '\n';

        return str;
    }

}
