package spring5_mybatis_study.mapper;

import spring5_mybatis_study.dto.Tutor;

public interface TutorMapper {
	Tutor selectTutorByTutorId(Tutor tutor);
	
	/* Transaction */
	Tutor selectTutorById(Tutor tutor);
	int insertTutor(Tutor tutor);
	int deleteTutor(int tutorId);

}
