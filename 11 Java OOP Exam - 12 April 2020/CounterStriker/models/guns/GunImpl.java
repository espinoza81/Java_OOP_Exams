package CounterStriker.models.guns;

import CounterStriker.common.ExceptionMessages;

public abstract class GunImpl implements Gun {
    private String name;
    private int bulletsCount;
    private int bulletsPerShoot;

    public GunImpl(String name, int bulletsCount) {
        setName(name);
        setBulletsCount(bulletsCount);
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_NAME);
        }

        this.name = name;
    }

    private void setBulletsCount(int bulletsCount) {
        if(bulletsCount < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_BULLETS_COUNT);
        }

        this.bulletsCount = bulletsCount;
    }

    void setBulletsPerShoot(int bulletsPerShoot) {
        this.bulletsPerShoot = bulletsPerShoot;
    }

    private boolean haveNoBullets() {
        return bulletsCount < bulletsPerShoot;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBulletsCount() {
        return bulletsCount;
    }

    @Override
    public int fire() {
        //The fire() method returns the number of bullets fired. Pistol can fire only 1 bullet and the Rifle only 10 at once, not
        //more, not less. If there are not enough bullets, the method should return 0.

        if(haveNoBullets()){
            return 0;
        }

        bulletsCount -= bulletsPerShoot;

        return bulletsPerShoot;
    }
}