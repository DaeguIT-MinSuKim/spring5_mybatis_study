package spring5_mybatis_study.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring5_mybatis_study.config.ContextRoot;
import spring5_mybatis_study.dto.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseMapperTest {
	protected static final Log log = LogFactory.getLog(CourseMapperTest.class);

	@Autowired
	private CourseMapper mapper;
		
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Test
	public void test01SelectCoursesByCondition() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tutorId", 1);
           
        List<Course> list = mapper.selectCoursesByCondition(map);
        Assert.assertNotNull(list);
        list.stream().forEach(s->log.debug(s.toString()));
	}

	@Test
    public void test02SelectCoursesByCondition() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courseName", "%Java%");
        List<Course> list = mapper.selectCoursesByCondition(map);
        Assert.assertNotNull(list);
        list.stream().forEach(s->log.debug(s.toString()));
    }

    @Test
    public void test03SelectCoursesByCondition() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

        GregorianCalendar cal = new GregorianCalendar(2013, 1, 1);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startDate", cal.getTime());
        List<Course> list = mapper.selectCoursesByCondition(map);
        Assert.assertNotNull(list);
        list.stream().forEach(s->log.debug(s.toString()));
    }
    
	@Test 
    public void test04SelectCaseCourses() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchBy", "Tutor");
        map.put("tutorId", 1);
        List<Course> list = mapper.selectCaseCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(s->log.debug(s.toString()));
        System.out.println();
        
        map.replace("searchBy", "CourseName");
        map.remove("tutorId");
        map.put("courseName", "%Java%");
        list = mapper.selectCaseCourses(map);
        Assert.assertNotNull(list);
        list.stream().forEach(s->log.debug(s.toString()));
    }

	@Test
	public void test05SelectWhereCourses() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    List<Course> list = mapper.selectWhereCourses(map);
	    Assert.assertNotNull(list);
	    list.stream().forEach(s->log.debug(s.toString()));
	    System.out.println();
	    
	    map.put("tutorId", 1);
	    list = mapper.selectWhereCourses(map);
	    list.stream().forEach(s->log.debug(s.toString()));
	    System.out.println();
	    
	    map.put("courseName", "%Java%");
	    list = mapper.selectWhereCourses(map);
	    list.stream().forEach(s->log.debug(s.toString()));
	    System.out.println();
	    
	    map.clear();
	    map.put("endDate", new Date());
	    list = mapper.selectWhereCourses(map);
	    list.stream().forEach(s->log.debug(s.toString()));      
	}
	
	@Test
	public void test06SelectTrimCourses() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	    Map<String, Object> map = new HashMap<String, Object>();
	    List<Course> list = mapper.selectTrimCourses(map);
	    Assert.assertNotNull(list);
	    list.stream().forEach(s->log.debug(s.toString()));      
	    System.out.println();
	    
	    map.put("tutorId", 1); 
	    list = mapper.selectTrimCourses(map);
	    Assert.assertNotNull(list);
	    list.stream().forEach(s->log.debug(s.toString()));
	    System.out.println();
	    
	    map.clear();
	    map.put("courseName", "%Java%");
	    list = mapper.selectTrimCourses(map);
	    Assert.assertNotNull(list);
	    list.stream().forEach(s->log.debug(s.toString()));
	    System.out.println();
	    
	    map.put("tutorId", 1);
	    list = mapper.selectTrimCourses(map);
	    Assert.assertNotNull(list);
	    list.stream().forEach(s->log.debug(s.toString()));
	    
	}

	@Test
	public void test07SelectCoursesForeachByTutors() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	    List<Integer> tutorIds = new ArrayList<Integer>();
	    tutorIds.add(1);
	    tutorIds.add(2);
	        
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("tutorIds", tutorIds);
	        
	    List<Course> list = mapper.selectCoursesForeachByTutors(map);
	    Assert.assertNotNull(list);
	    list.stream().forEach(s->log.debug(s.toString()));
	}
	
	@Test
	public void test08insertCourses() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	    List<Course> tutors = new ArrayList<Course>();
	    tutors.add(new Course(4, "mysql", "database", new Date(), new Date(), 3));
	    tutors.add(new Course(5, "mssql", "database", new Date(), new Date(), 3));
	    tutors.add(new Course(6, "mariaDb", "database", new Date(), new Date(), 4));
	        
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("tutors", tutors);
	        
	    int res = mapper.insertCourses(map);
	    Assert.assertEquals(3, res);
	}

	@Test
	public void test10DeleteCourses() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	    List<Integer> courseIds = Arrays.asList(4, 5, 6);
	        
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("courseIds", courseIds);
	        
	    int res = mapper.deleteCourses(map);
	    Assert.assertEquals(3, res);
	}

	@Test
	public void test09UpdateCourses() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	    Course course = new Course();
	    course.setCourseId(4);
	    course.setDescription("Oracle");
	    course.setName("데이터베이스");
	    
	    int res = mapper.updateCourse(course);
	    Assert.assertEquals(1, res);
	}
	
	@Test
	public void test10UpdateCourses() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	    List<Course> tutors = new ArrayList<Course>();
        Course course = new Course(7, "OracleXE", "database", new Date(), new Date(), 2);
        tutors.add(course);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tutors", tutors);
        mapper.insertCourses(map);
            
        Course uc = new Course();
        uc.setCourseId(7);
        uc.setDescription("공짜 데이터베이스");
        uc.setName("공짜디비");
        int res = mapper.updateCourse(uc);
        Assert.assertEquals(1, res);
        System.out.println();
        
        map.clear();
        map.put("tutorId", 2); 
        List<Course>list = mapper.selectTrimCourses(map);
        Assert.assertNotNull(list);
        list.forEach(c -> log.debug(c.toString())); 
        System.out.println();
        
        List<Integer> courseIds = Arrays.asList(7);
        map.clear();
        map = new HashMap<String, Object>();
        map.put("courseIds", courseIds);
            
        mapper.deleteCourses(map);

	}

	@Test
	public void test12insertCourse() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	    Course course = new Course(7, "oracle", "database", new Date(), new Date(), 4);
	    int res = mapper.insertCourse(course);
	    Assert.assertEquals(1, res);
	}

	@Test
	public void test13SelectCourseById() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");

	    Course course = mapper.selectCourseById(7);
		log.debug(course.toString());
		Assert.assertNotNull(course);
	}
	
	@Test
	public void test14DeleteCourse() {
	    log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
	    
	    int res = mapper.deleteCourse(7);
	    Assert.assertEquals(1, res);
	}
}
