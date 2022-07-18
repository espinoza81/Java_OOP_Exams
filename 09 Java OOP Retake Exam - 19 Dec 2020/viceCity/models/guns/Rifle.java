package viceCity.models.guns;

import viceCity.common.Constants;

public class Rifle extends BaseGun {

    public Rifle(String name) {
        super(name, Constants.RIFLE_BULLETS_PER_BARREL, Constants.RIFLE_TOTAL_BULLETS);
        setBulletsPerShoot(Constants.RIFLE_BULLETS_PER_SHOOT);
    }
}