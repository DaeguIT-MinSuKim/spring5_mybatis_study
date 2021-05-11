package spring5_mybatis_study.mapper;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring5_mybatis_study.config.ContextRoot;
import spring5_mybatis_study.dto.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
public class StudentMapperTest {
	protected static final Log log = LogFactory.getLog(StudentMapperTest.class);

	@Autowired
	private StudentMapper mapper;
		
	@Test
	public void testSelectStudentById() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

		Student student = new Student();
		student.setStudId(1);
		Student selectStudent = mapper.selectStudentById(student);
		
		log.debug(selectStudent.toString());
		Assert.assertNotNull(selectStudent);
	}

}
