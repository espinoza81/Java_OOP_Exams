package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car {
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    public BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if(model == null || model.trim().isEmpty() || model.length() < 4) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL, model, 4));
        }
        this.model = model;
    }

    private void setHorsePower(int horsePower) {
        if(("MuscleCar".equals(getClass().getSimpleName()) && (horsePower < 400 || horsePower > 600)) ||
                ("SportsCar".equals(getClass().getSimpleName()) && (horsePower < 250 || horsePower > 450))) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        this.horsePower = horsePower;
    }

    @Override
    public final String getModel() {
        return model;
    }

    @Override
    public final int getHorsePower() {
        return horsePower;
    }

    @Override
    public final double getCubicCentimeters() {
        return cubicCentimeters;
    }

    @Override
    public final double calculateRacePoints(int laps) {
        return cubicCentimeters / horsePower * laps;
    }
}
