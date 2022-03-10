package org.o7planning.sbhibernate.service;

import java.util.List;

import org.o7planning.sbhibernate.presistence.InforPageDto;

public interface InforPageService {

	List<InforPageDto> getInforDto();
	
	InforPageDto updateInfor(InforPageDto inforPageDto);
	
	Integer deleteInforDto(Integer courseID, Integer employeeID);
	
}
