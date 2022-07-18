package CounterStriker.models.guns;

import CounterStriker.common.Constants;

public class Rifle extends GunImpl {
    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
        setBulletsPerShoot(Constants.RIFLE_BULLETS_PER_SHOOT);
    }
}