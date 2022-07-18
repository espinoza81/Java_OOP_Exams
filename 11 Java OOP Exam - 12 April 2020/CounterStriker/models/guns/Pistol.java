package CounterStriker.models.guns;

import CounterStriker.common.Constants;

public class Pistol extends GunImpl {

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
        setBulletsPerShoot(Constants.PISTOL_BULLETS_PER_SHOOT);
    }
}