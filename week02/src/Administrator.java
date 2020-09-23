import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User {
    Administrator(String name, String password, String role) {
        super(name, password, role);
    }


    public boolean changeUserInfo()    //修改用户
    {
        String name , password , role;
        Scanner scanner = new Scanner(System.in);

        System.out.println("修改用户");

        System.out.println("请输入用户名：");
        name = scanner.next();

        System.out.println("请输入口令：");
        password = scanner.next();

        System.out.println("请输入角色：");
        role = scanner.next();


        try {
            if(DataProcessing.update(name , password , role))
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    public boolean delUser()           //删除用户
    {
        String name ;

        Scanner scanner = new Scanner(System.in);

        System.out.println("删除用户");

        System.out.println("请输入用户名：");
        name = scanner.next();

        try {
            if (DataProcessing.delete(name))
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    public boolean addUser()        //添加用户
    {
        String name , password , role;

        Scanner scanner = new Scanner(System.in);

        System.out.println("添加用户");

        System.out.println("请输入用户名：");
        name = scanner.next();

        System.out.println("请输入口令：");
        password = scanner.next();

        System.out.println("请输入角色：");
        role = scanner.next();

        try {
            if (DataProcessing.insert(name,password,role))
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

    public void listUser()          //列出用户列表
    {
        System.out.println("列出用户");

        try {
            for(Enumeration<User> user = DataProcessing.getAllUser();user.hasMoreElements();){

                User temp = user.nextElement();
                System.out.println( "Name:"+temp.getName() + "\t" +
                        "Password:" + temp.getPassword() + "\t" +
                        "Role:"+temp.getRole());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void showMenu() {

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {

            System.out.println("****欢迎进入系统管理人员菜单****");
            System.out.println("\t1.修改用户");
            System.out.println("\t2.删除用户");
            System.out.println("\t3.添加用户");
            System.out.println("\t4.用户列表");
            System.out.println("\t5.下载文档");
            System.out.println("\t6.文件列表");
            System.out.println("\t7.修改密码");
            System.out.println("\t8.退出");
            System.out.println("****************************");
            System.out.println("请输入菜单：");

            int nextInt = scanner.nextInt();

            switch (nextInt) {
                case 1 -> {
                    if(this.changeUserInfo())
                        System.out.println("修改成功");
                    else
                        System.out.println("修改失败");
                }
                case 2 -> {
                    if(this.delUser())
                        System.out.println("修改成功");
                    else
                        System.out.println("不存在该用户，无法删除");
                }

                case 3 -> {
                    if(this.addUser())
                        System.out.println("修改成功");
                    else
                        System.out.println("用户名已存在，请输入新用户名");
                }

                case 4 -> this.listUser();
                case 5 -> {
                    System.out.println("请输入：");
                    String fileName = scanner.next();
                    try {
                        this.downloadFile(fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case 6 -> {
                    try {
                        this.showFileList();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                case 7 -> {
                    System.out.println("请输入：");
                    String password = scanner.next();
                    this.setPassword(password);
                }
                case 8 -> isExit = true;
                default -> System.out.println("请重新输入");

            }
        }

    }
}
