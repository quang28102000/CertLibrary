package org.o7planning.sbhibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.o7planning.sbhibernate.dao.InforDAO;
import org.o7planning.sbhibernate.presistence.InforPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InforPageServiceImpl implements InforPageService{

	@Autowired
	private  InforDAO dao;

	@Override
	public List<InforPageDto> getInforDto() {
		// TODO Auto-generated method stub
		return dao.getInforDto();
	}
	
	@Override
	@Transactional
	public InforPageDto updateInfor(InforPageDto inforPageDto) {
		return dao.editInfor(inforPageDto);
	}

	@Override
	@Transactional
	public Integer deleteInforDto(Integer courseID, Integer employeeID) {
		 dao.deleteInfor(courseID, employeeID);
		 return 1;
	}
}
