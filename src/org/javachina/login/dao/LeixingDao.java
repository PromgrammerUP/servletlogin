package org.javachina.login.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.javachina.connectionpool.ConnectionPool;
import org.javachina.login.dto.LeixingDto;

public class LeixingDao {
	public ArrayList<LeixingDto> queryAll(){
		ArrayList<LeixingDto> result = new ArrayList<LeixingDto>();
		Connection conn = ConnectionPool.getConnection();
		String sql = "select id,name from leixing order by id";
		Statement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				LeixingDto lx = new LeixingDto(id, name);
				result.add(lx);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stat!=null){
					stat.close();
				}
				if(conn!=null&&!conn.isClosed())
				{
					ConnectionPool.close(conn);
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
		ArrayList<LeixingDto> list = new LeixingDao().queryAll();
		for (LeixingDto leixingDto : list) {
			System.out.println(leixingDto.getId()+":"+leixingDto.getName());
		}
	}
}
