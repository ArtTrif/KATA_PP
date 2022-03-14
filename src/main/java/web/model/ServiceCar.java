package web.model;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceCar {
    public List<Car> carList(int count){
        CarListClass carListClass = new CarListClass();
        return carListClass.carList().stream().limit(count).collect(Collectors.toList());
    }
}
