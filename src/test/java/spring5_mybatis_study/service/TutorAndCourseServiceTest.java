package spring5_mybatis_study.service;

import java.util.Date;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring5_mybatis_study.config.ContextRoot;
import spring5_mybatis_study.dto.Address;
import spring5_mybatis_study.dto.Course;
import spring5_mybatis_study.dto.PhoneNumber;
import spring5_mybatis_study.dto.Tutor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextRoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TutorAndCourseServiceTest {
    protected static final Log log = LogFactory.getLog(TutorAndCourseServiceTest.class);

    @After
    public void tearDown() throws Exception {
        System.out.println();
    }

    @Autowired
    private TutorAndCourseService service;

    @Test(expected = DuplicateKeyException.class)
    public void test01TrJoinTutorAndCourse_FailTutor() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        
        Address address = new Address();
        address.setAddrId(2);
        PhoneNumber phone = new PhoneNumber("010-2222-2222");
        
        //1번 교수는 존재
        Tutor tutor = new Tutor(1, "mskim", "net94@naver.com", phone, address);
    
        Course course = new Course(8, "Python", "Programming", new Date(), new Date(), 4);    
        service.trJoinTutorAndCourse(tutor, course);
    }

    @Test(expected = DuplicateKeyException.class)
    public void test02TrJoinTutorAndCourse_FailCourse() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Address address = new Address();
        address.setAddrId(2);
        PhoneNumber phone = new PhoneNumber("010-2222-2222");
        
        Tutor tutor = new Tutor(5, "mskim", "net94@naver.com", phone, address);
        //2번 과목 존재, 해당 교수번호를가진 교수가 존재하지 않음
        Course course = new Course(2, "Python", "Programming", new Date(), new Date(), 100);
    
        service.trJoinTutorAndCourse(tutor, course);

    }
    
    @Test
    public void test03TrJoinTutorAndCourse_Success() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        Address address = new Address();
        address.setAddrId(2);
        PhoneNumber phone = new PhoneNumber("010-2222-2222");
        
        Tutor tutor = new Tutor(5, "mskim", "net94@naver.com", phone, address);
        Course course = new Course(8, "Python", "Programming", new Date(), new Date(), 5);
    
        service.trJoinTutorAndCourse(tutor, course);
    }

    @Test(expected=RuntimeException.class)
    public void testTrRemoveTutorAndCourse_FailTutor() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        service.trRemoveTutorAndCourse(10, 8);//10번교수 미존재, 8번 과목 존재
    }
    
    @Test(expected=RuntimeException.class)
    public void testTrRemoveTutorAndCourse_FailCourse() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        service.trRemoveTutorAndCourse(5, 10);//5번 교수 존재, 10번 과목 없음
    }
    
    @Test
    public void testTrRemoveTutorAndCourse_Success() {
        log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
        service.trRemoveTutorAndCourse(5, 8);

    }

}
