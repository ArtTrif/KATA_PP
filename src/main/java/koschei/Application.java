package koschei;

import koschei.config.AppConfig;
import koschei.models.Island2;
import koschei.models.Ocean1;
import koschei.models.Wood3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        KoscheiTheDeathless koscheiTheDeathless =
                applicationContext.getBean(KoscheiTheDeathless.class);

        System.out.println(koscheiTheDeathless.getRulesByDeth());


    }
}
