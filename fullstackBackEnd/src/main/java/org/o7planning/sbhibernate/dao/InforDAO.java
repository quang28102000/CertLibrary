package org.o7planning.sbhibernate.dao;

import java.util.List;

import org.o7planning.sbhibernate.presistence.InforPageDto;

public interface InforDAO {
	
	List<InforPageDto> getInforDto();
	
	void addNewCourse(String tittle, String platform,  String category);
	
	Integer getLastestCourse();
	
	void addCourseLength(String id, String time);

}
