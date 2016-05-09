import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://192.168.1.38:3309/test";
		String user="production";//用户名
		String pwd="BEEQXXTGICARSCLU";//密码
		Connection con=DriverManager.getConnection(url,user,pwd);
		Statement stmt = con.createStatement();
//		stmt.executeUpdate();//增、删、改操作
		ResultSet rs = stmt.executeQuery("show tables;");//查询,返回结果集
		while(rs.next()){
			System.out.println(rs.getString(1));
		}
	}

}
