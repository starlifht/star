package com.test.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;

public class doSQL {
	static Connection conn=null;
	private static  void getConn(){
		String url =Params.url;
		String user = Params.user;
		String password = Params.password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void insert(String v1,String v2,String v3) throws Exception {
		getConn();
		String reg = "insert into record (time,v1,v2,v3) values(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(reg);
			pstmt.setString(4, v3);
			pstmt.setString(3, v2);
			pstmt.setString(2, v1);
			pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
		
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	}
	public static void getuserinfo() throws SQLException{
		String reg = "select * from user_info";
		PreparedStatement pstmt = conn.prepareStatement(reg);
		ResultSet rs=pstmt.executeQuery();
		 while(rs.next()) {
			    
       
            String  name = rs.getString("name");
    
            String  pwd = rs.getString("pwd");

             // 输出结果
             System.out.println(name +pwd);
            }

            rs.close();
            conn.close();
	}
	public static void main(String[] args) throws Exception {
		 double size =(double)132554654/1024/1024;
    	 DecimalFormat df = new DecimalFormat("0,000"); 
    	 //String fileSize=df.format(size);
      System.out.print(df.format(1858431454));
      doSQL.getConn();
      doSQL.getuserinfo();
	}

}
