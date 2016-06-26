package org.javachina.login.service;

import java.util.ArrayList;

import org.javachina.login.dao.ShangpinDao;
import org.javachina.login.dto.ShangpinDto;

/*
 * 商品类服务，本类提供商品类的增删改查服务
 */
public class ShangpinService {
	/*
	 * 查询所有商品服务
	 */
	public  ArrayList<ShangpinDto> getAllShangpin(){
		ShangpinDao dao = new ShangpinDao();
		return dao.queryAll();
	}
	/*
	 * 增加一个商品
	 */
	public boolean addShangpin(ShangpinDto shangpin){
		ShangpinDao dao = new ShangpinDao();
		int i = dao.insert(shangpin);
		if(i==0){
			return false;
		}
		return true;
	}
	public ShangpinDto getShangpin(int shangpinId){
		ShangpinDto result = null;
		ShangpinDao dao = new ShangpinDao();
		result = dao.queryById(shangpinId);
		return result;
	}
	public boolean deleteShangpin(int shangpinId){
		boolean result = false;
		ShangpinDao dao = new ShangpinDao();
		int i = dao.deleteById(shangpinId);
		if(i!=0){
			result =true;
		}
		return result;
	}
	public boolean updateShangpin(ShangpinDto dto){
		boolean result = false;
		ShangpinDao dao = new ShangpinDao();
		int i = dao.update(dto);
		if(i!=0){
			result = true;
		}
		return result;
	}
}
