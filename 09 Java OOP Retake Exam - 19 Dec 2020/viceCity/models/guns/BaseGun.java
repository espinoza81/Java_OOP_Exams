package viceCity.models.guns;

import viceCity.common.ExceptionMessages;

public abstract class BaseGun implements Gun {

    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    private boolean canFire;
    private int bulletsInBarrel;
    private int bulletsPerShoot = 0;

    public BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        setName(name);
        setBulletsPerBarrel(bulletsPerBarrel);
        setTotalBullets(totalBullets);
        this.canFire = true;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }

        this.name = name;
    }

    private void setBulletsPerBarrel(int bulletsPerBarrel) {
        if (bulletsPerBarrel < 0) {
            throw new IllegalArgumentException(ExceptionMessages.BULLETS_LESS_THAN_ZERO);
        }

        this.bulletsPerBarrel = bulletsPerBarrel;
    }

    private void setTotalBullets(int totalBullets) {
        if (totalBullets < 0) {
            throw new IllegalArgumentException(ExceptionMessages.TOTAL_BULLETS_LESS_THAN_ZERO);
        }

        this.totalBullets = totalBullets;
    }

    void setBulletsPerShoot(int bulletsPerShoot) {
        this.bulletsPerShoot = bulletsPerShoot;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return bulletsPerBarrel;
    }

    @Override
    public boolean canFire() {
        //if the gun has enough bullets to take a shoot it can fire.
        return bulletsInBarrel + totalBullets > 0;
    }

    @Override
    public int getTotalBullets() {
        return totalBullets;
    }

    private void reload() {
        int needBulletsForReload = bulletsPerBarrel - bulletsInBarrel;
        int reloadedBullets = Math.min(totalBullets, needBulletsForReload);
        bulletsInBarrel += reloadedBullets;
        totalBullets -= reloadedBullets;
    }

    private boolean canNoReload() {
        return totalBullets == 0;
    }

    private boolean hasNoBulletInBarrel() {
        return bulletsInBarrel == 0;
    }

    @Override
    public int fire() {
        //The Fire method acts differently in all child classes. It shoots bullets and returns the number of bullets that were shot.
        //Here is how it works:
        // Your guns have a barrel, which has a certain capacity for bullets and you shoot a different count of bullets
        //when you pull the trigger.
        // If your barrel becomes empty, you need to reload before you can shoot again.
        // Reloading is done by refilling the whole barrel of the gun (Keep in mind, that every barrel has capacity).
        // The bullets you take for reloading are taken from the gun's total bullets stock.
        //Keep in mind, that every type of gun shoots a different count of bullets when you pull the trigger!

        if (hasNoBulletInBarrel()) {
            if (canNoReload()) {
                return 0;
            }

            reload();
        }

        bulletsInBarrel -= bulletsPerShoot;
        return bulletsPerShoot;
    }
}