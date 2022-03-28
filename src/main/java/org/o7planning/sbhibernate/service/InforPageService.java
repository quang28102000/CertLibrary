package org.o7planning.sbhibernate.service;

import java.util.List;

import org.o7planning.sbhibernate.presistence.InforPageDto;

public interface InforPageService {

	List<InforPageDto> getInforDto();
	
	List<InforPageDto> getInforDtoName();
	
	InforPageDto updateInfor(InforPageDto inforPageDto);
	
	Integer deleteInforDto(Integer courseID, Integer employeeID);

	int deleteInforobj(Integer courseId, Integer employeeId);
	
}
