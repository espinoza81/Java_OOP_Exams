package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.ArrayDeque;
import java.util.Collection;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        ArrayDeque<String> items = new ArrayDeque<>(planet.getItems());
        for (Astronaut astronaut : astronauts) {
            while (!items.isEmpty()){
                String tempItem = items.poll();
                astronaut.getBag().getItems().add(tempItem);
                planet.getItems().remove(tempItem);
                astronaut.breath();
                if(!astronaut.canBreath()) break;
            }
        }
        //•	The astronauts start going out into open space one by one. They can't go if they don't have any oxygen left.
        //•	An astronaut lands on a planet and starts collecting its items one by one.
        //•	He finds an item and he takes a breath.
        //•	He adds the item to his backpack and respectively the item must be removed from the planet.
        //•	Astronauts can't keep collecting items if their oxygen becomes 0.
        //•	If it becomes 0, the next astronaut starts exploring.
    }
}