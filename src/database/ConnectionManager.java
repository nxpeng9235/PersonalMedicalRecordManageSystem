package database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionManager {

	public ConnectionManager() {
	}
	
	public static Connection getConn() {
		//String driver = "com.mysql.jdbc.Driver";
		String driver = "com.mysql.cj.jdbc.Driver";
		//String url = "jdbc:mysql://localhost:3306/pmrms";
		String url = "jdbc:mysql://cdb-obje6der.gz.tencentcdb.com:10141/pmrms?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
		String username = "root";
		String password = "SQLpencil123";
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, username, password);
			//System.out.println("Succeed in loading SQL Driver");
		} catch (ClassNotFoundException e) {
			//System.out.println("Fail to find Class!");
			e.printStackTrace();
		} catch (SQLException e) {
			//System.out.println("Fail to find SQL Driver!");
			e.printStackTrace();
		}
		return conn;
	}
	

	public static void closeConn(Connection conn){
		if (conn != null) { //连接是否有效
			try {
				if (!conn.isClosed()) { //连接是否已经关闭
					conn.close(); //关闭数据库连接
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}

	// 测试数据库连接
	public static void main(String[] args) {	
		Connection conn = ConnectionManager.getConn();//获得数据库连接
		if (conn == null) {
			JOptionPane.showMessageDialog(null, "遗憾，数据库连接失败！");
		}
		else {
			JOptionPane.showMessageDialog(null, "恭喜，数据库连接成功！");
		}
		ConnectionManager.closeConn(conn); //关闭数据库连接
	}

}
