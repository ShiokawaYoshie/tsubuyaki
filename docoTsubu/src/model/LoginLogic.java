package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginLogic {
	private String userName;
	private String userPassword;

	public void getInfo(User user, LoginLogic ll) {
		Connection con =null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		GetProperties gp = new GetProperties();
		String[] info = gp.getProperties();
		String url = info[0];
		String dbUser=info[1];
		String password =info[2] ;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,dbUser,password);
			while(rs == null) {

				pst = con.prepareStatement("select * from userinfo where username = ?");
				pst.setString(1, user.getName());
				rs = pst.executeQuery();

				if(rs.next() == false) {
					pst = con.prepareStatement("insert into userinfo values (?,?)");
					pst.setString(1, user.getName());
					pst.setString(2, user.getPass());
					pst.executeUpdate();
					rs = null;
				}
			}

//			if(rs.next()) {
			ll.setUserName(rs.getString("username"));
			ll.setUserPassword(rs.getString("password"));
//			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			if(pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}

	}

	public boolean execute(User user) {
		LoginLogic ll = new LoginLogic();
		ll.getInfo(user, ll);
//		System.out.println(ll.getUserName());
//		System.out.println(ll.getUserPassword());

		if(user.getName().equals(ll.getUserName()) && user.getPass().equals(ll.getUserPassword())){
			return true;
		}else {
			return false;
		}



	}
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return this.userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
