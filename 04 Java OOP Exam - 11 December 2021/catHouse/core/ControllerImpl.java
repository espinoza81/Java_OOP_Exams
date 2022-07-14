package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        //Creates and adds a House to the houses’ collection. Valid types are: "ShortHouse" and "LongHouse".
        //If the House type is invalid, you have to throw a NullPointerException with the following message:
        //•	"Invalid house type."
        //If the House is added successfully, the method should return the following String:
        //•	"{houseType} is successfully added."

        House house = null;

        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }

        houses.add(house);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        //Creates a toy of the given type and adds it to the ToyRepository.
        //Valid types are: "Ball" and "Mouse".
        //If the toy type is invalid, throw an IllegalArgumentException with a message:
        //•	"Invalid toy type."
        //The method should return the following string if the operation is successful:
        //•	"{toyType} is successfully added."

        Toy toy = null;

        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }

        toys.buyToy(toy);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        //Adds (buys) the desired Toy to the House with the given name.
        //You have to remove the Toy from the ToyRepository if the insert is successful.
        //If there is no such toy, you have to throw an IllegalArgumentException with the following message:
        //•	"Toy of type {toyType} is missing."
        //If no exceptions are thrown, return the String:
        //•	"Successfully added {toyType} to {houseName}."

        Toy toy = toys.findFirst(toyType);
        if(toy == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }

        House house = getHouse(houseName);
        house.buyToy(toy);
        toys.removeToy(toy);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    private House getHouse(String houseName) {
        return houses.stream().filter(s -> s.getName().equals(houseName)).findFirst().orElse(null);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        //Creates and adds the desired Cat to the House with the given name.
        //Valid Cat types are: "ShorthairCat", "LonghairCat".
        //Note: The method must first check whether the cat type is valid.
        //If the Cat type is invalid, you have to throw an IllegalArgumentException with the following message:
        //•	"Invalid cat type."

        //If no errors are thrown, return one of the following strings:
        //•	"Unsuitable house." - if the given Cat cannot live in the House.
        //•	"Successfully added {catType} to {houseName}." - if the Cat is added successfully in the House.

        Cat cat = null;

        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }

        House house = getHouse(houseName);

        if((house.getClass().getSimpleName().equals("ShortHouse") && catType.equals("LonghairCat")) ||
                (house.getClass().getSimpleName().equals("LongHouse") && catType.equals("ShorthairCat"))) {
            return ConstantMessages.UNSUITABLE_HOUSE;
        }

        house.addCat(cat);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
    }

    @Override
    public String feedingCat(String houseName) {
        //Feeds all Cat in the House with the given name.
        //Returns a string with information about how many cats were successfully fed, in the following format:
        //•	"Feeding a cat: {fedCount}"

        House house = getHouse(houseName);
        house.feeding();
        int catCount = house.getCats().size();

        return String.format(ConstantMessages.FEEDING_CAT, catCount);
    }

    @Override
    public String sumOfAll(String houseName) {
        //Calculates the value of the House with the given name.
        //It is calculated by the sum of all Cat’s and Toy’s prices in the House.
        //Return a string in the following format:
        //•	"The value of House {houseName} is {value}."
        //o	The value should be formatted to the 2nd decimal place!

        House house = getHouse(houseName);

        double allCatPrice = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double allToyPrice = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double value = allCatPrice + allToyPrice;

        return String.format(ConstantMessages.VALUE_HOUSE, houseName, value);
    }

    @Override
    public String getStatistics() {
        //Returns information about each house. You can use House's getStatistics method to implement the current functionality.
        //"{houseName} {houseType}:
        //Cats: {catName1} {catName2} {catName3} ... / Cats: none
        //Toys: {toysCount} Softness: {sumSoftness}"
        //"{houseName} {houseType}:
        //Cats: {catName1} {catName2} {catName3} ... / Cats: none
        //Toys: {toysCount} Softness: {sumSoftness}"
        //..."

        return houses.stream().map(House::getStatistics).collect(Collectors.joining(System.lineSeparator()));
    }
}
