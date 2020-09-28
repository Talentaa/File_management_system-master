import java.util.Scanner;

public class Browser extends User {

    public Browser(String name, String password, String role) {
        super(name, password, role);
    }


    @Override
    public void showMenu() {

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while(!isExit){
            System.out.println("****欢迎进入档案浏览人员菜单****");
            System.out.println("\t1.下载文档");
            System.out.println("\t2.文件列表");
            System.out.println("\t3.修改密码");
            System.out.println("\t4.退出");
            System.out.println("****************************");
            System.out.println("请输入菜单：");

            int nextInt = scanner.nextInt();

            switch (nextInt) {
                case 1 -> {
                    System.out.println("请输入档案号：");
                    String fileName = scanner.next();
                    this.downloadFile(fileName);
                }
                case 2 -> {
                    this.showFileList();
                }
                case 3 -> {
                    System.out.println("请输入：");
                    String password = scanner.next();
                    this.setPassword(password);
                }
                case 4 -> isExit =true;
                default -> System.out.println("请重新输入");

            }
        }
    }

}
