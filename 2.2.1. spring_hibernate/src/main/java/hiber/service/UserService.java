package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void add(Car car);
    List<User> listUsers();
    List<Car> listCars();
    public User getUserOwnsCar (String model, int service);
}
