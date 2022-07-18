package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayDeque;

public class GangNeighbourhood implements Neighbourhood {
    int deadCivilPlayer;


    public int getDeadCivilPlayer() {
        return deadCivilPlayer;
    }

    @Override
    public void action(Player mainPlayer, ArrayDeque<Player> civilPlayers) {
        //That's the most interesting method.
        //The main player starts shooting at all the civil players. When he starts shooting at a civil player, the following rules
        //apply:

        // He takes a gun from his guns.
        // Every time he shoots, he takes life pointsfrom the civil player, which are equal to the bullets that the current
        //gun shoots when the trigger is pulled.
        // If the barrel of his gun becomes empty, he reloads from his bullets stock and continues shooting with the
        //current gun, until he uses all of its bullets.
        // If his gun runs out of total bullets, he takes the next gun he has and continues shooting.
        // He shoots at the current civil player until he/she is alive.
        // If the civil player dies, he starts shooting at the next one.
        // The main player stops shooting only if he runs out of guns or until all the civil players are dead.
        //Note: IF the main player doesn't have guns when the fight starts, the action still happens.
        //The civil players (the ones that have stayed alive after the main player's attack) attack second. They start shooting
        //at him one after another and the following rules apply:
        // A civil player takes one of his guns and starts shooting at the main player.
        // Every time he shoots, he takes life pointsfrom the main player, which are equal to the bullets that the current
        //gun shoots when the trigger is pulled.
        // If the barrel of his gun becomes empty, he reloads from his bullets stock and continues shooting with the
        //current gun, until he uses all of its bullets.
        // If his current gun runs out of all its bullets, he takes the next gun he has and continues shooting.
        // If a civil player runs out of guns, the next civil player begins shooting.
        // If the main player dies, the whole action ends.
        mainPlayerShoot(mainPlayer, civilPlayers);

        civilPlayersShoot(mainPlayer, civilPlayers);
    }

    private void mainPlayerShoot(Player mainPlayer, ArrayDeque<Player> civilPlayers) {
        for (Gun gun : mainPlayer.getGunRepository().getModels()) {

            while (gun.canFire() && !civilPlayers.isEmpty()) {
                Player civilPlayer = civilPlayers.peek();

                while (civilPlayer.isAlive() && gun.canFire()) {
                    civilPlayer.takeLifePoints(gun.fire());
                }
                if(!civilPlayer.isAlive()) {
                    civilPlayers.poll();
                    deadCivilPlayer++;
                }
            }

            if (civilPlayers.isEmpty()) {
                break;
            }
        }
    }

    private void civilPlayersShoot(Player mainPlayer, ArrayDeque<Player> civilPlayers) {
        if (!civilPlayers.isEmpty()) {
            for (Player civilPlayer : civilPlayers) {

                for (Gun model : civilPlayer.getGunRepository().getModels()) {

                    while (mainPlayer.isAlive() && model.canFire()) {
                            mainPlayer.takeLifePoints(model.fire());

                    }
                }
            }
        }
    }
}