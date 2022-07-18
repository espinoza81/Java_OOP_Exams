package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PlayerRepository implements Repository<Player> {
    public Collection<Player> models;

    public PlayerRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Player player) {
        //If the player is null, throw a NullPointerException with a message: "Cannot add null in Player
        //Repository".
        // Adds a player to the collection.
        if(player == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
        }

        models.add(player);
    }

    @Override
    public boolean remove(Player player) {
        //Removes a player from the collection. Returns true if the removal was successful, otherwise - false.
        return models.remove(player);
    }

    @Override
    public Player findByName(String name) {
        //Returns the first player with the given username, if there is such a player. Otherwise, returns null
        return models.stream().filter(s -> s.getUsername().equals(name)).findFirst().orElse(null);
    }
}