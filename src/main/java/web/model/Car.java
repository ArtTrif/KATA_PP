package web.model;

public class Car {
    private String carBrand;
    private String carColor;
    private int yearRelease;

    public Car() {
    }

    public Car(String carBrand, String carColor, int yearRelease) {
        this.carBrand = carBrand;
        this.carColor = carColor;
        this.yearRelease = yearRelease;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }

    @Override
    public String toString() {
        return "\nCar{" +
                "carBrand='" + carBrand + '\'' +
                ", carColor='" + carColor + '\'' +
                ", yearRelease=" + yearRelease +
                '}';
    }
}
