package aquarium.core;

import aquarium.common.ConstantMessages;
import aquarium.common.Constants;
import aquarium.common.ExceptionMessages;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        //Adds an Aquarium.
        //Valid types are: "FreshwaterAquarium" and "SaltwaterAquarium".
        //If the Aquarium type is invalid, you have to
        //throw a NullPointerException with the following message:
        //•	"Invalid aquarium type."

        //If the Aquarium is added successfully, the method should return the following String:
        //•	"Successfully added {aquariumType}."

        Aquarium aquarium = null;

        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;

            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;

            default:
                throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }

        aquariums.add(aquarium);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        //Creates a decoration of the given type and adds it to the DecorationRepository.
        //Valid types are: "Ornament" and "Plant".
        //If the decoration type is invalid, throw an IllegalArgumentException with a message:
        //•	"Invalid decoration type."

        //The method should return the following string if the operation is successful:
        //•	"Successfully added {decorationType}."

        Decoration decoration = null;

        switch (type){
            case "Ornament":
                decoration = new Ornament();
                break;

            case "Plant":
                decoration = new Plant();
                break;

            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }

        decorations.add(decoration);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        //Adds the desired Decoration to the Aquarium with the given name.
        //You have to remove the Decoration from the DecorationRepository if the insert is successful.
        //If there is no such decoration, you have to
        //throw an IllegalArgumentException with the following message:
        //•	"There isn't a decoration of type {decorationType}."

        //If no exceptions are thrown return the String:
        //•	"Successfully added {decorationType} to {aquariumName}."

        Decoration decoration = decorations.findByType(decorationType);

        if(decoration == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }

        Aquarium aquarium = getAquarium(aquariumName);

        aquarium.addDecoration(decoration);
        decorations.remove(decoration);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    private Aquarium getAquarium(String aquariumName) {
        return aquariums.stream().filter(s -> s.getName().equals(aquariumName)).findFirst().orElse(null);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        //Adds the desired Fish to the Aquarium with the given name. Valid Fish types are: "FreshwaterFish", "SaltwaterFish".
        //If the Fish type is invalid, you have to throw an IllegalArgumentException with the following message:
        //•	"Invalid fish type." - if the Fish type is invalid
        //If no errors are thrown, return one of the following strings:
        //•	"Not enough capacity." - if there is not enough capacity to add the Fish in the Aquarium
        //•	"Water not suitable." - if the Fish cannot live in the Aquarium
        //•	"Successfully added {fishType} to {aquariumName}." - if the Fish is added successfully in the Aquarium
        Fish fish = null;

        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;

            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;

            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }

        Aquarium aquarium = getAquarium(aquariumName);

        if(!aquarium.getClass().getSimpleName().substring(0, 5).equals(fishType.substring(0, 5))) {
            return ConstantMessages.WATER_NOT_SUITABLE;
        }

        aquarium.addFish(fish);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        //Feeds all Fish in the Aquarium with the given name.
        //Returns a string with information about how many fish were successfully fed, in the following format:
        //•	"Fish fed: {fedCount}"

        Aquarium aquarium = getAquarium(aquariumName);
        aquarium.feed();

        return String.format(ConstantMessages.FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        //Calculates the value of the Aquarium with the given name. It is calculated by the sum of all Fish and Decorations prices in the Aquarium.
        //Return a string in the following format:
        //•	"The value of Aquarium {aquariumName} is {value}."
        //o	The value should be formatted to the 2nd decimal place!

        Aquarium aquarium = getAquarium(aquariumName);
        double allFishPrice = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        double allDecorationPrice = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();

        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName, allDecorationPrice + allFishPrice);
    }

    @Override
    public String report() {
        //Returns information about each aquarium. You can use the overridden .getInfo Aquarium method.
        //"{aquariumName} ({aquariumType}):
        //Fish: {fishName1} {fishName2} {fishName3} (…) / Fish: none
        //Decorations: {decorationsCount}
        //Comfort: {aquariumComfort}
        //{aquariumName} ({aquariumType}):
        //Fish: {fishName1} {fishName2} {fishName3} (…) / Fish: none
        //Decorations: {decorationsCount}
        //Comfort: {aquariumComfort}
        //(…)"
        //Note: Use \n or System.lineSeparator() for a new line.

        return aquariums.stream().map(Aquarium::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}