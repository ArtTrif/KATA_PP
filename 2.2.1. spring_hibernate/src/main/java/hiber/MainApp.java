package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      User user1 = new User("Добрыня", "Никитич", "DobNick@mail.ru");
      User user2 = new User("Илия", "Муромец", "IlMur@mail.ru");
      User user3 = new User("Алёша", "Попович", "AlPop@mail.ru");
      User user4 = new User("Никита", "Кожемяка", "IvVas@mail.ru");

      Car car1 = new Car("Toyota", 2020);
      Car car2 = new Car("Lada", 2022);
      Car car3 = new Car("Hyundai", 2016);
      Car car4 = new Car("Mazda", 2019);

      UserService userService = context.getBean(UserService.class);

      car1.setUser(user1);
      userService.add(user1);
      userService.add(car1);

      car2.setUser(user2);
      userService.add(user2);
      userService.add(car2);

      car3.setUser(user3);
      userService.add(user3);
      userService.add(car3);

      car4.setUser(user4);
      userService.add(user4);
      userService.add(car4);

      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println("================================");
      
      try {
         System.out.println(userService.getUserOwnsCar(car3.getModel(), car3.getService() ));
      } catch (Exception e) {
         System.out.println("нет пользователя с такой машиной");
      }

      context.close();
   }
}
