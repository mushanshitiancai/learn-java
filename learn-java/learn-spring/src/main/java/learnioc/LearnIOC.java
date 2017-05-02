package learnioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by mazhibin on 17/3/21
 */
public class LearnIOC {
    public static void main(String[] args) {
        ClassPathResource res = new ClassPathResource("learn-ioc.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);

        TestBean testBean = factory.getBean(TestBean.class);
        System.out.println(testBean.hello());
    }
}
