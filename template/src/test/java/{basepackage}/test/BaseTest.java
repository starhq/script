<%include("/java_copyright.include"){}%>
package ${basepackage}.test;

import ${basepackage}.config.PersistenceConfig;
import ${basepackage}.config.WebMVCConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 单元测试基类，自用的
 * 可以自己扩展
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {PersistenceConfig.class, WebMVCConfig.class}, loader = AnnotationConfigWebContextLoader.class)
public class BaseTest {
	
	//自用的，不用管
	protected static int measurements = 100; // 测量次数
    protected static int threads = 10; // 线程数
    protected static int SerialTimes = 1000; // 每个线程执行序列化次数
	
	//加个空方法，不然mvn test不让过的
    @Test
    public void nothing() {

    }
}
