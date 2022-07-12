package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private FoodRepository foodRepository;
    private Collection<Area> areas;

    public ControllerImpl() {
        this.foodRepository = new FoodRepositoryImpl();
        this.areas = new ArrayList<>();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        
        Area area = null;

        switch (areaType) {
            case "WaterArea":
                area = new WaterArea(areaName);
                break;
            case "LandArea":
                area = new LandArea(areaName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }

        areas.add(area);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {

        Food food = null;

        switch (foodType) {
            case "Vegetable":
                food = new Vegetable();
                break;
            case "Meat":
                food = new Meat();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }

        foodRepository.add(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {

        Food food = foodRepository.findByType(foodType);

        if (food == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));

        } else {
            Area area = getArea(areaName);
            area.addFood(food);
            foodRepository.remove(food);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
        }
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {

        Animal animal = null;

        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, kind, price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }

        Area area = getArea(areaName);

        if ((animal instanceof AquaticAnimal && area instanceof LandArea) ||
                (animal instanceof TerrestrialAnimal && area instanceof WaterArea)) {
            return ConstantMessages.AREA_NOT_SUITABLE;
        }

        area.addAnimal(animal);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = getArea(areaName);

        area.feed();

        return String.format(ConstantMessages.ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = getArea(areaName);

        double allKg = area.getAnimals().stream().mapToDouble(Animal::getKg).sum();

        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, allKg);
    }

    @Override
    public String getStatistics() {
        return areas.stream().map(Area::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }

    private Area getArea(String areaName) {
        return areas.stream().filter(s -> s.getName().equals(areaName)).findFirst().orElse(null);
    }
}
