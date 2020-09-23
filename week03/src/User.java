import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;


public abstract class User {
	private String name;
	private String password;
	private String role;
	private String uploadPath = "E:\\Projects\\IdeaProjects\\File_management_system-master\\week03\\uploadfiles\\";
	private String downloadPath = "E:\\Projects\\IdeaProjects\\File_management_system-master\\week03\\downloadfiles\\";

	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String password) throws SQLException{
		//写用户信息到存储
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
			System.out.println("修改成功");
			return true;
		}else
			return false;
	}
	
	public boolean downloadFile(String id) {

		Doc downloadDoc = DataProcessing.searchDoc(id);

		if (downloadDoc == null )
			return false;
		else
		{
			File inputFile = new File(uploadPath + downloadDoc.getFilename());

			try {

				BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));
				File outputFile = new File(downloadPath + downloadDoc.getFilename());
				outputFile.createNewFile();
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

				byte[] bytes = new byte[1024];

				int length = 0;
				while(	(	length = inputStream.read(bytes)	) != -1	){
					outputStream.write(bytes,0,length);
				}

				inputStream.close();
				outputStream.close();

				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return false;

	}
	
	public void showFileList(){
		System.out.println("文件列表：");
		for (Enumeration<Doc> doc = DataProcessing.getAllDocs();doc.hasMoreElements();){
			Doc temp = doc.nextElement();
			System.out.println("ID:" + temp.getID() + "\tCreator:" +temp.getCreator()+ "\tTime:" + temp.getTimestamp()+
					"\tFilename:" + temp.getFilename()+ "\tDescription:" + temp.getDescription());
		}


	}
	
	public abstract void showMenu();
	
	public void exitSystem(){
		System.out.println("系统退出, 谢谢使用 ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public String getDownloadPath() {
		return downloadPath;
	}
}
