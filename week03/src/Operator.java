
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Operator extends User {

    public Operator(String name, String password, String role) {
        super(name, password, role);
    }

    public boolean uploadFile()
    {
        Scanner scanner = new Scanner(System.in);

        String fileName , fileID , fileDescription;

        System.out.println("请输入源文件名：");
        fileName = scanner.next();

        System.out.println("请输入档案号：");
        fileID = scanner.next();

        System.out.println("请输入档案描述：");
        fileDescription = scanner.next();


        File uploadDoc = new File(fileName);
        if (uploadDoc.isFile()){

            try {
                if (DataProcessing.insertDoc(fileID,this.getName(), new Timestamp(System.currentTimeMillis()) ,fileDescription,uploadDoc.getName())) {
                    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(uploadDoc));
                    File outputDoc = new File(this.getUploadPath()+uploadDoc.getName());
                    outputDoc.createNewFile();
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputDoc));
                    byte[] bytes = new byte[1024];

                    int length = 0;
                    while(	(	length = inputStream.read(bytes)	) != -1	){
                        outputStream.write(bytes,0,length);
                    }

                    inputStream.close();
                    outputStream.close();

                    return true;


                }else {
                    System.out.printf("文件ID已经存在，请输入新的ID");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            System.out.printf("源文件不存在或者源文件是目录");
            return false;
        }


        return true;

    }



    @Override
    public void showMenu() {

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);


        while(!isExit){
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


                    if (this.uploadFile())
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
                case 3 -> {
                    this.showFileList();
                }
//修改密码
                case 4 -> {
                    System.out.println("请输入口令：");
                    String password = scanner.next();
                    try {
                        this.changeSelfInfo(password);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
//退出
                case 5 -> isExit = true;
                default -> System.out.println("请重新输入");

            }
        }
    }
}

