package fairyShop.models.helpers;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.instruments.Instrument;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseHelper implements Helper {
    private String name;
    private int energy;
    private Collection<Instrument> instruments;
    private int workEnergy;

    public BaseHelper(String name, int energy) {
        setName(name);
        this.energy = energy;
        this.instruments = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    void setWorkEnergy(int workEnergy) {
        this.workEnergy = workEnergy;
    }

    @Override
    public void work() {
        energy -= workEnergy;

        if (energy < 0) {
            energy = 0;
        }
    }

    @Override
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return energy > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return instruments;
    }

    public String getInfo() {
        //"Name: {helperName1}"
        //"Energy: {helperEnergy1}"
        //"Instruments: {countInstruments} not broken left"
        return String.format(ConstantMessages.HELPER_NAME_INFO, name) + System.lineSeparator() +
                String.format(ConstantMessages.HELPER_ENERGY_INFO, energy) + System.lineSeparator() +
                String.format(ConstantMessages.HELPER_INSTRUMENTS_NOT_BROKEN_INFO, (instruments.size() - getBrokenInstrument()));
    }

    public long getBrokenInstrument() {
        return instruments.stream().filter(Instrument::isBroken).count();
    }
}