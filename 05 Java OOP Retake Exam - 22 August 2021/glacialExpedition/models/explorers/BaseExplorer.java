package glacialExpedition.models.explorers;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import java.util.stream.Collectors;

public abstract class BaseExplorer implements Explorer {

    private String name;
    private double energy;
    private Suitcase suitcase;

    public BaseExplorer(String name, double energy) {
        setName(name);
        setEnergy(energy);
        this.suitcase = new Carton();
    }

    void setEnergy(double energy) {
        if(energy < 0) {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO);
        }

        this.energy = energy;
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public boolean canSearch() {
        //The canSearch() method returns boolean. Tell us if the energy is more than zero.

        return energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return suitcase;
    }

    @Override
    public void search() {
        //The search() method decreases the explorer's energy. Keep in mind that some Explorer types can implement the method differently.
        //•	The method decreases the explorer's energy by 15 units.
        //•	The energy value should not drop below zero.
        //•	Set the value to be zero if the energy value goes below zero.
        energy -= 15;

        if(energy < 0) {
            setEnergy(0);
        }
    }

    @Override
    public String toString(){
        //Name: {explorerName}
        //Energy: {explorerName}
        //Suitcase exhibits: {suitcaseExhibits1, suitcaseExhibits2, suitcaseExhibits3, …, suitcaseExhibits n}"
        return String.format(ConstantMessages.FINAL_EXPLORER_NAME, name) + System.lineSeparator() +
                String.format(ConstantMessages.FINAL_EXPLORER_ENERGY, energy) + System.lineSeparator() +
                String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS,
                        (suitcase.getExhibits().size() == 0 ?
                                "None" :
                                String.join(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, suitcase.getExhibits())));
    }
}
