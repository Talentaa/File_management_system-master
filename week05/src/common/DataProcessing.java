package common;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public  class DataProcessing {

	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static boolean connectToDatabase=false;
//	String driverName="com.mysql.cj.jdbc.Driver";               // 加载数据库驱动类
//	String url="jdbc:mysql://localhost:3306/document?serverTimezone=UTC";       // 声明数据库的URL
//	String user="root";                                      // 数据库用户
//	String password="123";

	public static void setConnectToDatabase(String driveName,String url,String user,String password)throws SQLException,ClassNotFoundException{

		Class.forName(driveName);
		connection= DriverManager.getConnection(url,user,password);
		connectToDatabase = true;

	}

	public static void disconnectFromDatabase(){
		if (connectToDatabase){
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}finally {
				connectToDatabase = false;
			}
		}
	}

	public static Doc searchDoc(String DocID) throws SQLException{
		Doc temp = null;
		if (!connectToDatabase)
			throw new SQLException("Not connected to Database");

		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from doc_info where Id=' " + DocID + "'";
		resultSet = statement.executeQuery(sql);

		if (resultSet.next()){
			String ID = resultSet.getString("id");
			String creator = resultSet.getString("creator");
			Timestamp timestamp = Timestamp.valueOf(resultSet.getString("timestamp"));
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			temp = new Doc(ID,creator,timestamp,description,filename);
		}
		return  temp;
	}

	public static boolean insertDoc(String fileID, String name, Timestamp timestamp, String fileDescription, String filename) throws SQLException{
		if (!connectToDatabase)
			throw new SQLException("Not connected to Database");
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql = "insert doc_info values('"+fileID+"','"+name+"','"+timestamp+"','"+fileDescription+"','"+filename+"')";
		statement.executeUpdate(sql);
		return true;
	}

	public static Enumeration<Doc> getAllDocs() throws SQLException{
		if (!connectToDatabase)
			throw new SQLException("Not connected to Database");

		Hashtable<String , Doc> docs= new Hashtable<String ,Doc>();

		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);

		String sql = "select * from doc_info";
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()){
			String id = resultSet.getString("id");
			String creator = resultSet.getString("creator");
			Timestamp timestamp = Timestamp.valueOf(resultSet.getString("timestamp"));
			String description = resultSet.getString("description");
			String filename = resultSet.getString("filename");
			docs.put(id , new Doc(id,creator,timestamp,description,filename));
		}

		Enumeration <Doc> e = docs.elements();
		return e;



	}

	public static User searchUser(String username) throws SQLException {
		User temp = null;
		if (!connectToDatabase)
			throw new SQLException("Not connected to Database");

		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from user_info where username=' " + username + "'";
		resultSet = statement.executeQuery(sql);

		if (resultSet.next()){
			String _username = resultSet.getString("username");
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");
			temp = new User(_username, password, role) ;
		}
		return  temp;

	}

	public static User searchUser(String username , String password) throws SQLException {
		User temp = null;
		if (!connectToDatabase)
			throw new SQLException("Not connected to Database");

		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql = "select * from user_info where username='"+username+"'";
		resultSet = statement.executeQuery(sql);

		if (resultSet.next()){
			String _username = resultSet.getString("username");
			String _password = resultSet.getString("password");
			String role = resultSet.getString("role");

			if (!_password.equals(password))
				return null;
			else {
				temp = new User(_username,_password,role);
				return temp;
			}
		}
		return null;

	}

	public static Enumeration<User> getAllUser() throws SQLException{
		Hashtable<String, User> users = new Hashtable<String, User>();

		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from user_info";
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()){
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			String role = resultSet.getString("role");
			users.put(username,new User(username,password,role));
		}
		 Enumeration<User> e = users.elements();
		return  e;

	}

	public static boolean updateUser(String username, String password, String role) throws SQLException{
		if (!connectToDatabase)
			throw new SQLException("Not connected to Database");
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			//		  update user_info set password='123',role='operator' where username='jack';
		String sql = "update user_info set password='"+password+"',role='"+role+"' where username='"+username+"'";
		statement.executeUpdate(sql);
		return true;
	}

	public static boolean insertUser(String userName, String password, String role) throws SQLException{
		if (!connectToDatabase)
			throw new SQLException("Not connected to Database");
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
				//	  insert into user_info(username,password,role) values('tonye','123','operator');
				//		(username,password,role)"+"
		String sql = "insert into user_info values('"+userName+"','"+password+"','"+role+"')";
		//String sql = "insert into user_info values('userName','+password+"',"+role+"')";
		statement.executeUpdate(sql);
		return true;
	}

	public static boolean deleteUser(String userName) throws SQLException{
		if (!connectToDatabase)
			throw new SQLException("Not connected to Database");
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql = "delete from user_info where username='"+userName+"'";
		statement.executeUpdate(sql);
		return true;
	}

}
