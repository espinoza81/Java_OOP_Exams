package fairyShop.models.instruments;

import fairyShop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument {
    private int	power;

    public InstrumentImpl(int power) {
        setPower(power);
    }

    private void setPower(int power) {
        if(power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }

        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void use() {
        power -= 10;

        if (power < 0) {
            power = 0;
        }
    }

    @Override
    public boolean isBroken() {
        return power <= 0;
    }
}