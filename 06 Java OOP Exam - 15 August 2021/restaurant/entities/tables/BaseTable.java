package restaurant.entities.tables;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {
    private Collection<HealthyFood> healthyFood; //accessible only by the base class
    private Collection<Beverages> beverages; //accessible only by the base class
    private int number; //the table number
    private int size; //the table size
    private int numberOfPeople; //the counter of people who want a table
    private double pricePerPerson; //the price per person for the table
    private boolean isReservedTable; //returns true if the table is reserved, otherwise false
    private double allPeople; //calculates the price for all people


    public BaseTable(int number, int size, double pricePerPerson) {
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.isReservedTable = false;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if(numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }

        this.numberOfPeople = numberOfPeople;
    }

    private void setSize(int size) {
        if(size < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }

        this.size = size;
    }

    private void setAllPeople() {
        this.allPeople = allPeople();
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return numberOfPeople * pricePerPerson;
    }

    @Override
    public void reserve(int numberOfPeople) {
        //Reserves the table with the counter of people given.
        setNumberOfPeople(numberOfPeople);
        setAllPeople();
        isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        //Orders the provided healthy food (think of a way to collect all the healthy food which is ordered).
        healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        //Orders the provided beverages (think of a way to collect all the beverages which are ordered).
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        //Returns the bill for all orders.
        double healthyFoodBill = healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double beveragesBill = beverages.stream().mapToDouble(s -> s.getPrice()*s.getCounter()).sum();
        return healthyFoodBill + beveragesBill + allPeople();
    }

    @Override
    public void clear() {
        //Removes all the ordered drinks and food and finally frees the table, the table is not reserved, sets the count of people and price to 0.
        healthyFood.clear();
        beverages.clear();
        isReservedTable = false;
        numberOfPeople = 0;
        allPeople = 0;
    }

    @Override
    public String tableInformation() {
        return "Table - " + number + System.lineSeparator() +
                "Size - " + size + System.lineSeparator() +
                "Type - " + getClass().getSimpleName() + System.lineSeparator() +
                "All price - " + pricePerPerson;
    }
}