package org.javachina.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.javachina.connectionpool.ConnectionPool;

public class LoginUserDao {
	public String queryPwdById(String userId){
		String result=null;
		Connection conn = ConnectionPool.getConnection();
		String sql = "select password from loginuser where userid=?";
		PreparedStatement pstm=null;
		ResultSet set =null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			set = pstm.executeQuery();
			if(set.next()){
				result = set.getString("password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null){
					pstm.close();
				}
				if(set!=null){
					set.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionPool.close(conn);
		}
		return result;
	}
}
