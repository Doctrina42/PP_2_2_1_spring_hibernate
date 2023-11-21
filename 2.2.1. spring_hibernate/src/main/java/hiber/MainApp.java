package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.logging.Logger;

public class MainApp {

   public static void main(String[] args) {
      Logger logger = Logger.getLogger(MainApp.class.getName());
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Nissan", 999);
      Car car2 = new Car("X", 999);
      Car car3 = new Car("F", 888);
      Car car4 = new Car("B", 777);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         logger.info("Id = "+user.getId());
         logger.info("First Name = "+user.getFirstName());
         logger.info("Last Name = "+user.getLastName());
         logger.info("Email = "+user.getEmail());
         logger.info("Car = "+user.getCar().getModel() + " " + user.getCar().getSeries());
      }

      List<User> usersWithCar = userService.getUserByCarDetails("X", 999);

      for (User user : usersWithCar) {
         logger.info("Id = "+user.getId());
         logger.info("First Name = "+user.getFirstName());
         logger.info("Last Name = "+user.getLastName());
         logger.info("Email = "+user.getEmail());
         logger.info("Car = "+user.getCar().getModel() + " " + user.getCar().getSeries());
      }

      context.close();
   }
}