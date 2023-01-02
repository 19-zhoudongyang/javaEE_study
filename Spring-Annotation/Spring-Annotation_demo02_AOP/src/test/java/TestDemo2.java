import com.zhou.aop.MathCalculator;
import com.zhou.mvc.config1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.junit.Test;

public class TestDemo2 {
    @Test
    public void testAop() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(config1.class);
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);

        }
        MathCalculator mathCalculator = annotationConfigApplicationContext.getBean(MathCalculator.class);
        System.out.println(mathCalculator.div(2, 2));
    }
}
