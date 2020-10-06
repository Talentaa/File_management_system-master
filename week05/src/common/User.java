package common;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;


public class User {
	private String name;
	private String password;
	private String role;
	private String uploadPath = "E:\\Projects\\IdeaProjects\\File_management_system-master\\week04\\uploadfiles\\";
	private String downloadPath = "E:\\Projects\\IdeaProjects\\File_management_system-master\\week04\\downloadfiles\\";

	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}

	
	public boolean downloadFile(String id) {

		Doc downloadDoc = null;
		try {
			downloadDoc = DataProcessing.searchDoc(id);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

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

	public boolean uploadFile(String fileName ,String fileID ,String fileDescription)
	{

		File uploadDoc = new File(fileName);
		if (uploadDoc.isFile()){

			try {
				if (DataProcessing.insertDoc(fileID,this.getName(), new Timestamp(System.currentTimeMillis()) ,fileDescription,uploadDoc.getName())) {
					BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(uploadDoc));
					File outputDoc = new File(uploadPath+uploadDoc.getName());
					outputDoc.createNewFile();
					BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputDoc));
					byte[] bytes = new byte[1024];

					int length = 0;
					while(	(	length = inputStream.read(bytes)	) != -1	){
						outputStream.write(bytes,0,length);
					}

					inputStream.close();
					outputStream.close();

				}else {

					return false;
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else {
			return false;
		}

		return true;

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

	public String getTitle() {
		if (role.equals("operator"))
			return "档案录入人员界面";
		else if (role.equals("browser"))
			return "档案浏览员界面";
		else
			return "系统管理员界面";
	}
}
