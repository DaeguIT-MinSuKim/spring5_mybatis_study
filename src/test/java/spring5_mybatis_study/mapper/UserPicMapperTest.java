package spring5_mybatis_study.mapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
import spring5_mybatis_study.dto.UserPic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextRoot.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserPicMapperTest {
	protected static final Log log = LogFactory.getLog(UserPicMapperTest.class);
	
	@Autowired
	private UserPicMapper mapper;
		
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	@Test
	public void test1InsertUserPic() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		UserPic userPic = new UserPic();
        userPic.setId(2);
        userPic.setName("LeeYouYong");
        userPic.setBio("put some lengthy bio here");
        userPic.setPic(getPicFile());
        int result = mapper.insertUserPic(userPic);
        Assert.assertSame(1, result);
	}

	private byte[] getPicFile() {
		byte[] pic = null;
		File file = new File(System.getProperty("user.dir") + "\\images\\lyy.jpg");
//		System.out.println(file.getPath());
		try(InputStream is = new FileInputStream(file)){
			pic = new byte[is.available()];
			is.read(pic);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pic;
	}

	@Test
	public void test2GetUserPic() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		//userpic 테이블의 이미지파일을 프로젝트폴더/pics 경로에 로드
		UserPic userPic = mapper.getUserPic(2);
        if (userPic.getPic() != null) {
            File file = getPicFile(userPic);
            log.debug("file path " + file.getAbsolutePath());
        }
        Assert.assertNotNull(userPic);
	}

	private File getPicFile(UserPic userPic) {
        File pics = new File(System.getProperty("user.dir") + "\\pics\\");
        if (!pics.exists()) {
            pics.mkdir();
        }
        
        File file = new File(pics, userPic.getName() + ".jpg");
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(userPic.getPic());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
	}

	@Test
	public void test3DeleteUserPic() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = mapper.deleteUserPic(2);
	       
        Assert.assertEquals(1, res);
	}

}
