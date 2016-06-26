package org.javachina.login.service;

import java.util.ArrayList;

import org.javachina.login.dao.LeixingDao;
import org.javachina.login.dto.LeixingDto;

public class LeixingService {
	public ArrayList<LeixingDto> getAll(){
		ArrayList<LeixingDto> result = new ArrayList<LeixingDto>();
		result = new LeixingDao().queryAll();
		return result;
	}
}
