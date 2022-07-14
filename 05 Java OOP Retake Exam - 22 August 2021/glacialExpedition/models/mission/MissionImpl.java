package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.ArrayDeque;
import java.util.Collection;

public class MissionImpl implements Mission {
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        ArrayDeque<String> exhibits = new ArrayDeque<>(state.getExhibits());
        for (Explorer explorer : explorers) {
            while (explorer.canSearch() && !exhibits.isEmpty()) {
                String tempExhibits = exhibits.poll();
                explorer.search();
                explorer.getSuitcase().getExhibits().add(tempExhibits);
                state.getExhibits().remove(tempExhibits);
            }
            if(exhibits.isEmpty()) break;
        }
        //Here is how the explore method works:
        //•	Explorers cannot go on expeditions if their energy is below 0.
        //•	They leave the station and start collecting exhibits one by one.
        //•	If they find an exhibit, their energy is decreased.
        //•	They add the exhibit to their carton. The exhibit should then be removed from the state.
        //•	Explorers cannot continue collecting exhibits if their energy drops to 0.
        //o	If their energy drops to 0, the next explorer starts exploring.
    }
}
