package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public int calculateComfort() {
        //Returns the sum of each decoration’s comfort in the Aquarium
        return decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFish(Fish fish) {
        //Adds a Fish in the Aquarium if there is a capacity for it.
        //if there is not enough capacity to add the Fish to the Aquarium throw an IllegalStateException with the following message:
        //•	"Not enough capacity."

        if(this.fish.size() == capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }

        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        //Removes a Fish from the Aquarium.
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        //Adds a Decoration in the Aquarium.
        decorations.add(decoration);
    }

    @Override
    public void feed() {
        //The feed() method feeds all fishes in the aquarium.
        fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        //Returns a String with information about the Aquarium in the format below. If the Aquarium doesn't have fish, print "none" instead.
        //"{aquariumName} ({aquariumType}):
        //Fish: {fishName1} {fishName2} {fishName3} (…) / Fish: none
        //Decorations: {decorationsCount}
        //Comfort: {aquariumComfort}"
        return String.format(ConstantMessages.AQUARIUM_INFO, name, getClass().getSimpleName()) + System.lineSeparator() +
                String.format(ConstantMessages.FISH_INFO,
                        (fish.size() == 0 ?
                                "none" :
                                fish.stream().map(Fish::getName).collect(Collectors.joining(" ")))) + System.lineSeparator() +
                String.format(ConstantMessages.DECORATIONS_INFO, decorations.size()) + System.lineSeparator() +
                String.format(ConstantMessages.COMFORT_INFO, calculateComfort());
    }

    @Override
    public Collection<Fish> getFish() {
        return fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return decorations;
    }
}