import java.util.Scanner;

public class Operator extends User {

    public Operator(String name, String password, String role) {
        super(name, password, role);
    }

    public boolean uploadFile(String fileName , String fileNumber , String fileDescription)
    {
        System.out.println("上传文件");
        return true;

    }



    @Override
    public void showMenu() {


        Scanner scanner = new Scanner(System.in);


        while(true){
            System.out.println("****欢迎进入档案录入人员菜单****");
            System.out.println("\t1.上传文件");
            System.out.println("\t2.下载文档");
            System.out.println("\t3.文件列表");
            System.out.println("\t4.修改密码");
            System.out.println("\t5.退出");
            System.out.println("****************************");
            System.out.println("请输入菜单：");

            int nextInt = scanner.nextInt();

            switch (nextInt) {
//上传文件
                case 1 -> {
                    String fileName , fileNumber , fileDescription;

                    System.out.println("请输入源文件名：");
                    fileName = scanner.next();

                    System.out.println("请输入档案号：");
                    fileNumber = scanner.next();

                    System.out.println("请输入档案描述：");
                    fileDescription = scanner.next();

                    if (this.uploadFile(fileName , fileNumber , fileDescription))
                        System.out.println("上传成功");
                    else
                        System.out.println("上传失败");


                }
//下载文档
                case 2 -> {
                    System.out.println("请输入档案号：");
                    String fileNumber = scanner.next();
                    if(this.downloadFile(fileNumber))
                        System.out.println("下载成功");
                    else
                        System.out.println("下载失败");
                }
//文件列表
                case 3 -> this.showFileList();
//修改密码
                case 4 -> {
                    System.out.println("请输入口令：");
                    String password = scanner.next();
                    this.changeSelfInfo(password);
                }
//退出
                case 5 -> this.exitSystem();
                default -> System.out.println("请重新输入");

            }
        }
    }
}

