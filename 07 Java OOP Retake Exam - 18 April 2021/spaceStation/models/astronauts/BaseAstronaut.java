package spaceStation.models.astronauts;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

public abstract class BaseAstronaut implements Astronaut {
    private String name;
    private double oxygen;
    private Bag bag;

    public BaseAstronaut(String name, double oxygen) {
        setName(name);
        setOxygen(oxygen);
        this.bag = new Backpack();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    void setOxygen(double oxygen) {
        if(oxygen < 0){
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }

        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    @Override
    public boolean canBreath() {
        return oxygen > 0;
    }

    @Override
    public Bag getBag() {
        return bag;
    }

    @Override
    public void breath() {
        oxygen -= oxygen >= 10 ? 10 : 0;
        //The breath() method decreases astronauts' oxygen. Keep in mind that some types of Astronaut can implement the method differently.
        //•	The method decreases the astronauts' oxygen by 10 units.
        //•	Astronaut's oxygen should not drop below zero.
    }

    @Override
    public String toString() {
        //Name: {astronautName One}
        //Oxygen: {astronautOxygen One}
        //Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ none}
        return String.format(ConstantMessages.REPORT_ASTRONAUT_NAME, name) + System.lineSeparator() +
                String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, oxygen) + System.lineSeparator() +
                String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS,
                        (bag.getItems().size() == 0) ? "none" : String.join(", ", bag.getItems()));
    }
}