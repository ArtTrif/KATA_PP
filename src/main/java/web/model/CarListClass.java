package web.model;

import java.util.ArrayList;
import java.util.List;

public class CarListClass {
    public List<Car> carList() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Porshe", "Yelow", 2022));
        carList.add(new Car("BMW", "Blue", 2021));
        carList.add(new Car("Ferrari", "Red", 1963));
        carList.add(new Car("Mercedes-Benz", "White", 2019));
        carList.add(new Car("Rolls-Royce", "Black", 2020));
        return carList;
    }
}
