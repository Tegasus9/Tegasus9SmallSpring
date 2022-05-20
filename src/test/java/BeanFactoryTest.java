import com.tegasus9.spring.BeanDefinition;
import com.tegasus9.spring.BeanFactory;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
@Log4j2
public class BeanFactoryTest {
    @Test
    public void beanFactoryTest(){
        //初始化beanFactory
        BeanFactory beanFactory = new BeanFactory();
        //注入bean
        beanFactory.registerBean("userService",new BeanDefinition(new UserService()));
        //getBean
        UserService userService = (UserService)beanFactory.getBean("userService");
        //使用
        userService.systemOut();
        log.info("log4j2引用成功");
    }

}
