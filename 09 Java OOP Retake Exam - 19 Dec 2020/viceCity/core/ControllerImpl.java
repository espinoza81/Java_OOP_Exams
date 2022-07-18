package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.common.Constants;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.ArrayDeque;

public class ControllerImpl implements Controller {
    private ArrayDeque<Player> civilPlayers;
    private GunRepository gunRepository;
    private MainPlayer mainPlayer;

    public ControllerImpl() {
        this.civilPlayers = new ArrayDeque<>();
        this.gunRepository = new GunRepository();
        this.mainPlayer = new MainPlayer();
    }

    @Override
    public String addPlayer(String name) {
        //Creates a civil player with the given name.
        //The method should return the following message:
        // "Successfully added civil player: {player name}!"
        Player civilPlayer = new CivilPlayer(name);
        civilPlayers.offer(civilPlayer);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        //Creates a gun with the provided type and name.
        //If the gun type is invalid, the method should return the following message:
        // "Invalid gun type!"
        //If the gun type is correct, keep the gun and return the following message:
        // "Successfully added {gun name} of type: {gun type}"

        Gun gun = null;

        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }

        gunRepository.add(gun);

        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        //Parameters
        // name – String (player's name)
        //Functionality
        //Adds the first added gun to the player's gun repository.
        // If there are no guns in the queue, return the following message:
        //"There are no guns in the queue!"
        // If the name of the player is "Vercetti", you need to add the gun to the main player's repository and
        //return the following message:
        //"Successfully added {gun name} to the Main Player: Tommy Vercetti"
        // If you receive a name that doesn't exist, you should return the following message:
        //"Civil player with that name doesn't exists!"
        // If everything is successful, you should add the gun to the player's repository and return the following
        //message:
        //"Successfully added {gun name} to the Civil Player: {player name}"
        if(gunRepository.getModels().isEmpty()) {
            return  ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }



        if("Vercetti".equals(name)){
            Gun gun = gunRepository.getModels().poll();
            mainPlayer.getGunRepository().add(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
        }

        Player civilPlayer = civilPlayers.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
        if(civilPlayer == null) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }
        Gun gun = gunRepository.getModels().poll();
        civilPlayer.getGunRepository().add(gun);

        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), civilPlayer.getName());
    }

    @Override
    public String fight() {
        //When the fight command is called, the action happens. You should start the action between the main player and all
        //the civil players. When a civil player is killed, it should be removed from the repository.
        // If the main player still has all of his points and no one is dead or harmed by the civil players, you should
        //return the following messages:
        //"Everything is okay!"
        // If any of the players have different life points, you should return the following message:
        //"A fight happened:
        //Tommy live points: {main player life points}!
        //Tommy has killed: {dead civil players} players!
        //Left Civil Players: {civil players count}!"

        int civilPlayersLifeBeforeFight = civilPlayers.stream().mapToInt(Player::getLifePoints).sum();

        GangNeighbourhood fightArena = new GangNeighbourhood();
        fightArena.action(mainPlayer, civilPlayers);

        int civilPlayersLifeAfterFight = civilPlayers.stream().mapToInt(Player::getLifePoints).sum();

        if(mainPlayer.getLifePoints() == Constants.MAIN_PLAYER_LIFE_POINTS
                && civilPlayersLifeBeforeFight == civilPlayersLifeAfterFight) {
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        } else {
            return ConstantMessages.FIGHT_HAPPENED + System.lineSeparator() +
                    String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()) + System.lineSeparator() +
                    String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, fightArena.getDeadCivilPlayer()) + System.lineSeparator() +
                    String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.size());
        }
    }
}