package org.o7planning.sbhibernate.dao;

import java.util.List;

import org.o7planning.sbhibernate.presistence.InforPageDto;

public interface InforDAO {
	
	List<InforPageDto> getInforDto();
	
	List<InforPageDto> getInforName();
	
	InforPageDto editInfor (InforPageDto inforPageDto);

	void deleteInfor (Integer courseID, Integer employeeID);

	void deleteInforobj(Integer courseID, Integer employeeID);

//	void addNewCourse(String tittle, String platform,  String category);
	
//	Integer getLastestCourse();
	
}
