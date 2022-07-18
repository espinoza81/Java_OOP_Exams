package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private Gun gun;

    public PlayerImpl(String username, int health, int armor, Gun gun) {
        setUsername(username);
        setHealth(health);
        setArmor(armor);
        setGun(gun);
    }

    private void setUsername(String username) {
        if(username == null || username.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }

        this.username = username;
    }

    private void setHealth(int health) {
        if(health < 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }

        this.health = health;
    }

    private void setArmor(int armor) {

        if(armor < 0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }

        this.armor = armor;
    }

    private void setGun(Gun gun) {
        if(gun == null){
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }

        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    @Override
    public boolean isAlive() {
        // isAlive - boolean
        //o If the health is above zero.
        return health > 0;
    }

    @Override
    public void takeDamage(int points) {
        //The takeDamage() method decreases the Player's armor and health. First, you need to reduce the armor. If the
        //armor reaches 0, transfer the damage to health points. If the health points are less than or equal to zero, the player
        //is dead.
        if(getArmor() > 0) {
            armor -= points;
            if(armor < 0) armor = 0;
            return;
        }

        if(isAlive()){
            health -= points;
            if(health < 0) health = 0;
        }
    }

    @Override
    public String toString() {
        //"{player type}: {player username}
        //--Health: {player health}
        //--Armor: {player armor}
        //--Gun: {player gun name}";

        return String.format(OutputMessages.PLAYER_INFO, getClass().getSimpleName(), getUsername()) + System.lineSeparator() +
                String.format(OutputMessages.PLAYER_HEALTH, getHealth()) + System.lineSeparator() +
                String.format(OutputMessages.PLAYER_ARMOR, getArmor()) + System.lineSeparator() +
                String.format(OutputMessages.PLAYER_GUN, getGun().getName());
    }
}