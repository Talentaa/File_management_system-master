import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        String tip_system = "档案系统";
        String tip_menu = "请选择菜单：";
        String tip_exit = "系统退出，谢谢使用！";
        String infos = "****欢迎进入"+tip_system+"****\n \t"+
                "1.登录\n  \t2.退出\n"+
                "****************************";

        String name , password;



        Scanner scanner = new Scanner(System.in);

        String input = null;
        while (true)
        {
            System.out.println(infos);
            System.out.print(tip_menu);

            input = scanner.next().trim();
            if(!(input).matches("[12]")){
                System.err.print(tip_menu);
            }else{
                int nextInt = Integer.parseInt(input);
                switch (nextInt) {
                    case 1 -> {
                        System.out.println("请输入用户名");
                        name = scanner.next();
                        System.out.println("请输入口令");
                        password = scanner.next();
                        User user = null;
                        try {
                            user = DataProcessing.search(name, password);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        if (user == null)
                            System.out.println("用户名口令错误");
                        else
                            user.showMenu();
                    }
                    case 2 -> {
                        System.out.println(tip_exit);
                        System.exit(0);
                    }
                }

            }

        }
    }
}

