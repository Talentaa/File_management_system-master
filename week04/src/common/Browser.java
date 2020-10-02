package common;
public class Browser extends User {

    public Browser(String name, String password, String role) {
        super(name, password, role);
    }

    @Override
    public String getTitle() {
        return "档案浏览员界面";
    }


}
