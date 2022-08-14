package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;

import football.entities.field.Field;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private SupplementRepository supplement;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        //Adds a Field. Valid types are: "ArtificialTurf" and "NaturalGrass".
        //If the Field type is invalid, you have to throw a NullPointerException with the following message:
        //"Invalid field type."
        //If the Field is added successfully, the method should return the following String:
        //"Successfully added {fieldType}."

        Field field = null;

        switch (fieldType){
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                break;
            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }

        fields.add(field);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        //Creates a supplement of the given type and adds it to the SupplementRepository.
        // Valid types are "Powdered" and "Liquid". If the supplement type is invalid,
        // throw an IllegalArgumentException with a message:
        //"Invalid supplement type."
        //The method should return the following String if the operation is successful:
        //"Successfully added {supplementType}."
        Supplement newSupplement = null;

        switch (type){
            case "Powdered":
                newSupplement = new Powdered();
                break;
            case "Liquid":
                newSupplement = new Liquid();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        supplement.add(newSupplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        //Adds the desired Supplement to the Field with the given name.
        //You have to remove the Supplement from the SupplementRepository if the insert is successful.
        //If there is no such supplement, you have to throw an IllegalArgumentException with the following message:
        //"There isn't a Supplement of type {supplementType}."
        //If no exceptions are thrown return the String:
        //"Successfully added {supplementType} to {fieldName}."

        Supplement supplementToAdd = supplement.findByType(supplementType);

        if(supplementToAdd==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }

        Field field = fields.stream().filter(s -> s.getName().equals(fieldName)).findAny().get();

        field.addSupplement(supplementToAdd);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        //Check if the player type is valid. Valid Player types are: "Men", "Women".
        //Adds the desired Player to the Field with the given name.
        //If the Player type is invalid, you have to throw an IllegalArgumentException with the following message:
        //"Invalid player type." - if the Player type is invalid.
        //If no errors are thrown, return one of the following strings:
        //"The pavement of the terrain is not suitable." - if the Player cannot play in the Field
        //"Successfully added {playerType} to {fieldName}." - if the Player is added successfully in the Field
        Player player = null;

        switch (playerType){
            case "Men":
                player = new Men(playerName, nationality, strength);
                break;
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        Field field = fields.stream().filter(s -> s.getName().equals(fieldName)).findAny().get();

        if((player.getClass().getSimpleName().equals("Men") && field.getClass().getSimpleName().equals("ArtificialTurf")) ||
                (player.getClass().getSimpleName().equals("Women") && field.getClass().getSimpleName().equals("NaturalGrass"))) {
            return ConstantMessages.FIELD_NOT_SUITABLE;
        }

        field.addPlayer(player);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        //Drag all Player in the Field with the given name.
        //Returns a string with information about how many players were successfully dragged in the following format:
        //"Player drag: {dragCount}"
        Field field = fields.stream().filter(s -> s.getName().equals(fieldName)).findAny().get();

        field.drag();

        int count = field.getPlayers().size();

        return String.format(ConstantMessages.PLAYER_DRAG, count);
    }

    @Override
    public String calculateStrength(String fieldName) {
        //Calculates the value of the Field with the given name.
        //It is calculated by the sum of all Players's strengths in the Field.
        //Return a string in the following format:
        //"The strength of Field {fieldName} is {value}."

        Field field = fields.stream().filter(s -> s.getName().equals(fieldName)).findAny().get();

        int sumStrength = field.getPlayers().stream().mapToInt(Player::getStrength).sum();

        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, sumStrength);
    }

    @Override
    public String getStatistics() {
        return fields.stream().map(Field::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}