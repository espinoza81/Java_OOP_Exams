package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class BaseField implements Field{

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if(name==null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public int sumEnergy() {
        return supplements.stream().mapToInt(Supplement::getEnergy).sum();
    }

    @Override
    public void addPlayer(Player player) {
        //Adds a Player on the Field if there is a capacity for it.
        //If there is not enough capacity to add the Player in the Field throw an IllegalStateException with the following message:
        //"Not enough capacity."

        if(players.size() == capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }

        players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void drag() {
        players.forEach(Player::stimulation);
    }

    @Override
    public String getInfo() {
        //Returns a String with information about the Field in the format below.
        // If the Field doesn't have a player, print "none" instead.
        //"{fieldName} ({fieldType}):
        //Player: {playerName1} {playerName2} {playerName3} (…) / Player: none
        //Supplement: {supplementsCount}
        //Energy: {sumEnergy}"

        return name + " (" + getClass().getSimpleName() + "):" + System.lineSeparator() +
                "Player: " + (players.size() == 0 ? "none" : players.stream().map(Player::getName).collect(Collectors.joining(" "))) + System.lineSeparator() +
                "Supplement: " + supplements.size() + System.lineSeparator() +
                "Energy: " + sumEnergy();
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public Collection<Supplement> getSupplement() {
        return supplements;
    }

    @Override
    public String getName() {
        return name;
    }
}
