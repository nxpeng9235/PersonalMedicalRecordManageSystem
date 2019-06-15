package actor;

public class User {
    
    protected String id;
    protected String name;
    protected String password;
    
    public void setID(int n) {
        id = String.valueOf(n);
    }

    public String getID() {
        return id;
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String s) {
        password = s;
    }

    public String getPassword() {
        return password;
    }

}