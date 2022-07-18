package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.PlayerImpl;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        //Adds a Gun and adds it to the GunRepository. Valid types are "Pistol" and "Rifle".
        //If the Gun type is invalid, you have to throw an IllegalArgumentException with the following message:
        // "Invalid gun type."
        //If the Gun is added successfully, the method should return the following String:
        // "Successfully added gun {gunName}."

        Gun gun = null;

        switch (type) {
            case "Pistol":
                gun = new Pistol(name, bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name, bulletsCount);
                break;
            default:
                return ExceptionMessages.INVALID_GUN_TYPE;
        }

        guns.add(gun);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        //Creates a Player of the given type and adds it to the PlayerRepository. Valid types are: "Terrorist" and
        //"CounterTerrorist".
        //If the gun is not found throw NullPointerException with a message:
        // "Gun cannot be found!"
        //If the player type is invalid, throw an IllegalArgumentException with a message:
        // "Invalid player type!"
        //The method should return the following String if the operation is successful:
        // "Successfully added player {playerUsername}."

        Gun gun = guns.findByName(gunName);
        if(gun == null) {
            return ExceptionMessages.GUN_CANNOT_BE_FOUND;
        }

        Player player = null;

        switch (type) {
            case "Terrorist":
                player = new Terrorist(username, health, armor, gun);
                break;
            case "CounterTerrorist":
                player = new CounterTerrorist(username, health, armor, gun);
                break;
            default:
                return ExceptionMessages.INVALID_PLAYER_TYPE;
        }

        players.add(player);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        //The game starts with all players that are alive! Returns the result from the start() method.

        List<Player> livePlayers = players.getModels().stream().filter(Player::isAlive).collect(Collectors.toList());

        return field.start(livePlayers);
    }

    @Override
    public String report() {
        //Returns information about each player separated with a new line. Order them by type alphabetically, then by health
        //descending, then by username alphabetically. You can use the overridden .toString() Player method.
        //"{player type}: {player username}
        //--Health: {player health}
        //--Armor: {player armor}
        //--Gun: {player gun name}"
        //Note: Use System.lineSeparator() for a new line and don't forget to trim if you use StringBuilder.

        return getCounterTerrorist("CounterTerrorist") +

                System.lineSeparator() +

                getCounterTerrorist("Terrorist");
    }

    private String getCounterTerrorist(String playerType) {
        return players.getModels().stream().filter(s -> s.getClass().getSimpleName().equals(playerType)).
                sorted(Comparator.comparing(Player::getHealth).reversed().
                        thenComparing(Player::getUsername)).
                map(Player::toString).
                collect(Collectors.joining(System.lineSeparator()));
    }
}