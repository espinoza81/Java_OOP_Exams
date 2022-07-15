package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int exploredPlanetsCount;


    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.exploredPlanetsCount = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        //Creates an astronaut with the given name of the given type.
        //If the astronaut is invalid, throw an IllegalArgumentException with a message:
        //"Astronaut type doesn't exists!"
        //The method should return the following message:
        //"Successfully added {astronautType}: {astronautName}!"

        Astronaut astronaut = null;

        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }

        astronautRepository.add(astronaut);

        return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        //Creates a planet with the provided items and name.
        //The method should return the following message:
        //•	"Successfully added Planet: {planetName}!"

        Planet planet = new PlanetImpl(planetName);

        planet.getItems().addAll(Arrays.asList(items));

        planetRepository.add(planet)
        ;
        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        //Retires the astronaut from the space station by removing it from its repository.
        //If an astronaut with that name doesn't exist, throw IllegalArgumentException with the following message:
        //•	"Astronaut {astronautName} doesn't exists!"
        // If an astronaut is successfully retired, remove it from the repository and return the following message:
        //•	"Astronaut {astronautName} was retired!"

        if(!astronautRepository.remove(astronautRepository.findByName(astronautName))) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        //When the explore command is called, the action happens.
        //You should start exploring the given planet, by sending the astronauts that are most suitable for the mission:
        //•	You call each of the astronauts and pick only the ones that have oxygen above 60 units.
        //•	You send suitable astronauts on a mission to explore the planet.
        //•	If you don't have any suitable astronauts, throw IllegalArgumentException with the following message: "You need at least one astronaut to explore the planet!"

        //•	After a mission, you must return the following message, with the name of the explored planet and the count of the astronauts that had given their lives for the mission:
        //"Planet: {planetName} was explored! Exploration finished with {deadAstronauts} dead astronauts!"

        Collection<Astronaut> astronauts = astronautRepository.getModels().stream().filter(s -> s.getOxygen() > 60).collect(Collectors.toList());

        if(astronauts.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Planet planet = planetRepository.findByName(planetName);

        Mission mission = new MissionImpl();
        mission.explore(planet, astronauts);
        exploredPlanetsCount++;

        long deadAstronauts = astronauts.stream().filter(s -> !s.canBreath()).count();

        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, deadAstronauts);
    }

    @Override
    public String report() {
        //Returns the information about the astronauts. If any of them doesn't have bag items, print "none" instead:
        //"{exploredPlanetsCount} planets were explored!
        //Astronauts info:
        //Name: {astronautName One}
        //Oxygen: {astronautOxygen One}
        //Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ none}
        //…
        //Name: {astronautName N}
        //Oxygen: {astronautOxygen N}
        //Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ none}"
        return String.format(ConstantMessages.REPORT_PLANET_EXPLORED, exploredPlanetsCount) + System.lineSeparator() +
                ConstantMessages.REPORT_ASTRONAUT_INFO + System.lineSeparator() +
                astronautRepository.getModels().stream().map(Object::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}