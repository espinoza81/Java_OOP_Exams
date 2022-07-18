package viceCity.models.guns;

import viceCity.common.Constants;

public class Pistol extends BaseGun {

    public Pistol(String name) {
        super(name, Constants.PISTOL_BULLETS_PER_BARREL, Constants.PISTOL_TOTAL_BULLETS);
        setBulletsPerShoot(Constants.PISTOL_BULLETS_PER_SHOOT);
    }
}