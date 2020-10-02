package common;

public class Administrator extends User {
    Administrator(String name, String password, String role) {
        super(name, password, role);
    }


    public String  getTitle(){
        return "系统管理员界面";
    }
    }
