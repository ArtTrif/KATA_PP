package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.model.CarListClass;
import web.model.ServiceCar;

import java.util.List;

@Controller
public class CarController {
    @GetMapping("/cars")
    public String viewCar (@RequestParam (value = "count", defaultValue = "5") int count, Model model ) {
        ServiceCar serviceCar = new ServiceCar();
        List<Car> carListView = serviceCar.carList(count);
        model.addAttribute("carListCount", carListView);
        return "cars";
    }

}
