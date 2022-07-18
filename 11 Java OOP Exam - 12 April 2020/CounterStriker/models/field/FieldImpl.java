package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {
        //Separates the players into two types - Terrorist and Counter Terrorist. The game continues until one of the teams is
        //completely dead (all players have 0 health). The terrorists attack first and after that the counter terrorists. The
        //attack happens like that: Each live terrorist shoots on each live counter terrorist once and inflicts damage equal to
        //the bullets fired and after that each live counter terrorist shoots on each live terrorist.
        //If Terrorists win return "Terrorist wins!" otherwise return "Counter Terrorist wins!"

        List<Player> terrorists = players.stream().filter(s -> s.getClass().getSimpleName().equals("Terrorist")).collect(Collectors.toList());
        List<Player> counterTerrorists = players.stream().filter(s -> s.getClass().getSimpleName().equals("CounterTerrorist")).collect(Collectors.toList());
        
        while (isLive(terrorists) && isLive(counterTerrorists)) {
            attack(terrorists, counterTerrorists);

            attack(counterTerrorists, terrorists);
        }

        if(isLive(terrorists)) {
            return OutputMessages.TERRORIST_WINS;
        }

        return OutputMessages.COUNTER_TERRORIST_WINS;
    }

    private boolean isLive(List<Player> players) {
        return players.stream().mapToInt(Player::getHealth).sum() > 0;
    }

    private void attack(List<Player> attackingPlayers, List<Player> attackedPlayers) {
        for (Player attackingPlayer : attackingPlayers) {
            if (attackingPlayer.isAlive()) {
                for (Player attackedPlayer : attackedPlayers) {
                    if (attackedPlayer.isAlive()) {
                        int damagePoints = attackingPlayer.getGun().fire();
                        attackedPlayer.takeDamage(damagePoints);
                    }
                }
            }
        }
    }
}