package com.mushan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
//        App.reexecute();
    	App.preparedStatement();
    }
    
    public static void normal() throws ClassNotFoundException, SQLException{
    	Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://192.168.33.10:3306/jdbc_test";
		String user="mushan";//用户名
		String pwd="111";//密码
		Connection con=DriverManager.getConnection(url,user,pwd);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("show tables;");//查询,返回结果集
		while(rs.next()){
			System.out.println(rs.getString(1));
		}
//		
//		rs = stmt.executeQuery("select * from user");//查询,返回结果集
//		while(rs.next()){
//			System.out.print(rs.getString(1)+" ");
//			System.out.println(rs.getString(2));
//		}
//		System.out.println("---");
//		
//		int num = stmt.executeUpdate("insert into user (name) values ('fuck')");
//		System.out.println(num);
//		System.out.println("---");
//		
//		boolean is = stmt.execute("select * from user");
//		is = stmt.execute("insert into user (name) values ('fuck')");
//		if(is){
//			
//		}
		
		rs = stmt.getResultSet();
		while(rs.next()){
			System.out.print(rs.getString(1)+" ");
			System.out.println(rs.getString(2));
		}
		System.out.println("---");
		
		
		App.close(con, stmt, rs);
    }
    
    public static void reexecute() throws ClassNotFoundException, SQLException{
    	Class.forName("com.mysql.jdbc.Driver");
    	String url = "jdbc:mysql://192.168.33.10:3306/jdbc_test?user=mushan&password=111";
    	Connection con = DriverManager.getConnection(url);
    	Statement stmt = con.createStatement();
    	
    	ResultSet rs = stmt.executeQuery("show tables");
    	while(rs.next()){
    		System.out.println(rs.getString(1));
    	}
    	
    	ResultSet rs2 = stmt.executeQuery("select * from user");
    	while(rs2.next()){
    		System.out.println(rs2.getString(1));
    	}
    	
    	
//    	while(rs.next()){
//    		System.out.println(rs.getString(1));
//    	}
    }
    
    public static void preparedStatement() throws ClassNotFoundException, SQLException{
    	Class.forName("com.mysql.jdbc.Driver");
    	String url = "jdbc:mysql://192.168.33.10:3306/jdbc_test?user=mushan&password=111";
    	Connection con = DriverManager.getConnection(url);
    	PreparedStatement stmt = con.prepareStatement("select * from user where id=?");
    	
    	stmt.setInt(1, 2);
    	ResultSet rs = stmt.executeQuery();
    	while(rs.next()){
    		System.out.println(rs.getString(2));
    	}
    	App.close(con, stmt, rs);
    }
    
    
    public static void close(Connection con,Statement stmt,ResultSet rs){
    	if(rs != null){
    		try {
				rs.close();      // 关闭结果集
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(stmt != null){
					try {
						stmt.close();    // 关闭
						stmt.close();
						stmt.close();
						stmt.close();
						stmt.close();
					}catch (SQLException e){
						e.printStackTrace();
					} finally {
						if(con != null){
							try {
								con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
    	}
    }
}
