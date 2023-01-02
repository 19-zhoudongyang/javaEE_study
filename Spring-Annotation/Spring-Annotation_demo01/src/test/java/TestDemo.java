import com.zhou.mvc.config.SpringConfig;
import com.zhou.mvc.config.SpringConfig2;
import com.zhou.mvc.config.SpringConfig3_Profile;
import com.zhou.mvc.pojo.Dog;
import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDemo {
    @Test
    public void testComponentScan() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println(definitionName);
        }
    }

    @Test
    public void testComponentScan1() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println(definitionName);
        }
    }

    @Test
    public void testComponentScan2() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig2.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println(definitionName);
        }
    }

    @Test
    public void testGetFactoryBean() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig2.class);
        Object bean = annotationConfigApplicationContext.getBean("myFactoryBean");
        System.out.println(bean);
    }

    //    @Test
//    public void testBeanInitAndDestroy() {
//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig2.class);
//        Object demo03 = annotationConfigApplicationContext.getBean("Demo03");
//        System.out.println(demo03);
//        annotationConfigApplicationContext.close();
//    }
    @Test
    public void testBeanInitAndDestroy1() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig2.class);
        Object demo05 = annotationConfigApplicationContext.getBean("testBeanDemo05");
        System.out.println(demo05);
        annotationConfigApplicationContext.close();
    }

    @Test
    public void testPojoValue() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SpringConfig2.class);
        Dog dog = (Dog) annotationConfigApplicationContext.getBean("dog");
        System.out.println(dog);

    }

    @Test
    public void testProfileTest() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        //设置运行环境，可设置多个
        annotationConfigApplicationContext.getEnvironment().setActiveProfiles("test", "dev");
        //注册配置类到IOC容器中
        annotationConfigApplicationContext.register(SpringConfig3_Profile.class);
        //启动刷新IOC容器
        annotationConfigApplicationContext.refresh();
        //获取IOC容器中所有bean名称
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
