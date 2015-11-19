package sqlcmd.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Hunky on 18.11.2015.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
        LabRat rat = (LabRat) context.getBean("rat");
        rat.sayHi();
        System.out.println(rat.getName());
    }
}
