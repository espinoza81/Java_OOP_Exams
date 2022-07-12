package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseArea implements Area {
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    public int sumCalories() {
        return foods.stream().mapToInt(Food::getCalories).sum();
    }

    public void addAnimal(Animal animal) {
        if (capacity > animals.size()) {
            this.animals.add(animal);
        } else {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
    }

    public void removeAnimal(Animal animal){
        animals.remove(animal);
    }

    public void addFood(Food food){
        foods.add(food);
    }

    public void feed() {
        animals.forEach(Animal::eat);
    }

    public String getInfo() {
        return name + " (" + getClass().getSimpleName() + "):" + System.lineSeparator() +
                "Animals: " + (animals.size() == 0 ? "none" : animals.stream().map(Animal::getName).collect(Collectors.joining(" "))) + System.lineSeparator() +
                "Foods: " + foods.size() + System.lineSeparator() +
                "Calories: " + sumCalories();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Food> getFoods() {
        return foods;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return animals;
    }
}
