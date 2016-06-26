package org.javachina.login.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.catalina.storeconfig.IStoreConfig;
import org.javachina.connectionpool.ConnectionPool;
import org.javachina.login.dto.ShangpinDto;

public class ShangpinDao {
	public ArrayList<ShangpinDto> queryAll(){
		ArrayList<ShangpinDto> result = new ArrayList<ShangpinDto>();
		Connection conn = ConnectionPool.getConnection();
		Statement stat = null;
		ResultSet rs = null;
		try {
			 stat = conn.createStatement();
			 String sql ="select sp.id,sp.name,sp.price,sp.address,sp.inputdate"
			 		+ ",sp.leixingid,lx.name lxName,sp.description from shangpin sp,leixing lx "
			 		+ "where sp.leixingid = lx.id order by sp.id";
			 rs = stat.executeQuery(sql);
			 while(rs.next()){
				 int id = rs.getInt(1);
				 String name = rs.getString(2);
				 double price = rs.getDouble(3);
				 String address = rs.getString(4);
				 Date inputDate = rs.getDate(5);
				 int lxId = rs.getInt(6);
				 String lxName = rs.getString(7);
				 String description = rs.getString(8);
				 ShangpinDto dto = new ShangpinDto();
				 dto.setId(id);
				 dto.setName(name);
				 dto.setPrice(price);
				 dto.setAddress(address);
				 dto.setInputDate(inputDate);
				 dto.setLeixingId(lxId);
				 dto.setDescription(description);
				 dto.setLeixingName(lxName);
				 result.add(dto);
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
				if(conn!=null&&!conn.isClosed()){
					ConnectionPool.close(conn);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
public int insert(ShangpinDto dto){
	int result=0;
	Connection conn = null;
	conn = ConnectionPool.getConnection();
	String sql = "insert into shangpin values(seq_shangpin.nextval,?,?,?,?,?,?)";
	PreparedStatement psmt = null;
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, dto.getName());
		psmt.setDouble(2, dto.getPrice());
		psmt.setString(3, dto.getAddress());
		psmt.setDate(4, dto.getInputDate());
		psmt.setInt(5, dto.getLeixingId());
		psmt.setString(6,dto.getDescription());
		result = psmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null&&!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return result;
}

public ShangpinDto queryById(int spid){
	ShangpinDto result = null;
	Connection conn = ConnectionPool.getConnection();
	Statement stat = null;
	ResultSet rs = null;
	try {
		stat = conn.createStatement();
		String sql ="select sp.id,sp.name,sp.price,sp.address,sp.inputdate"
		 		+ ",sp.leixingid,lx.name lxName,sp.description from shangpin sp,leixing lx "
		 		+ "where lx.id=sp.leixingid and sp.id="+spid+"";
		rs = stat.executeQuery(sql);
		while(rs.next()){
			int id = rs.getInt(1);
			 String name = rs.getString(2);
			 double price = rs.getDouble(3);
			 String address = rs.getString(4);
			 Date inputDate = rs.getDate(5);
			 int lxId = rs.getInt(6);
			 String lxName = rs.getString(7);
			 String description = rs.getString(8);
			 result= new ShangpinDto();
			 result.setId(id);
			 result.setName(name);
			 result.setPrice(price);
			 result.setAddress(address);
			 result.setInputDate(inputDate);
			 result.setLeixingId(lxId);
			 result.setDescription(description);
			 result.setLeixingName(lxName);
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
			if(conn!=null&&!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return result;
}

public int deleteById(int id){
	int result = 0;
	Connection conn = ConnectionPool.getConnection();
	Statement stat = null;
	try {
		stat = conn.createStatement();
		String sql = "delete shangpin where id="+id+"";
		result = stat.executeUpdate(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			if(stat!=null){
				stat.close();
			}
			if(conn!=null&&!conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return result;
}

public int update(ShangpinDto dto){
	int result = 0;
	int id = dto.getId();
	String name = dto.getName();
	double price = dto.getPrice();
	String address = dto.getAddress();
	Date inputDate = dto.getInputDate();
	int typeID = dto.getLeixingId();
	String description =  dto.getDescription();
	
	Connection conn = ConnectionPool.getConnection();
	PreparedStatement psmt = null;
	String sql = "update shangpin set name=?,price=?,address=?,inputdate=?,leixingId=?,description=? where id=?";
	try {
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, name);
		psmt.setDouble(2, price);
		psmt.setString(3, address);
		psmt.setDate(4, inputDate);
		psmt.setInt(5, typeID);
		psmt.setString(6, description);
		psmt.setInt(7, id);
		result = psmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
	
}
	public static void main(String[] args) {
		ArrayList<ShangpinDto> list = new ShangpinDao().queryAll();
		for (ShangpinDto dto : list) {
			System.out.println(dto.getId()+":"+dto.getName()+":"+dto.getPrice()
			+":"+dto.getAddress()+":"+dto.getLeixingName());
		}
	}
}
