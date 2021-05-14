package spring5_mybatis_study.mapper;

import spring5_mybatis_study.dto.CourseStat;

public interface CourseStatMapper {
	/* procedure */
	
	CourseStat getCourseCountByTutor(int param);

}
