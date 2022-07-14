package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploreStateCount;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        //Creates an explorer with the given name of the given type and saves it in the repository.
        //If the type is invalid, throw an IllegalArgumentException with the following message:
        //"Explorer type doesn't exists."
        //Otherwise, the method should return the following message:
        //•	"Added {type}: {explorerName}."

        Explorer explorer = null;

        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }

        explorerRepository.add(explorer);

        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        //Creates a state with the provided exhibits and name and save it in the repository.
        //The method should return the following message:
        //•	"Added state: {stateName}."

        State state = new StateImpl(stateName);
        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }
        stateRepository.add(state);

        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        //Retires the explorer from Antarctica by removing them from the repository.
        //If an explorer with that name doesn’t exist, throw IllegalArgumentException with the following message:
        //•	"Explorer {explorerName} doesn't exists."
        // If an explorer is successfully retired, remove them from the repository and return the following message:
        //•	"Explorer {explorerName} has retired!"

        Explorer explorer = explorerRepository.byName(explorerName);

        if(explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }

        explorerRepository.remove(explorer);

        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        //When the explore command is called, the action happens.
        //You should start exploring the given state by sending the explorers that are most suitable for the mission:
        //•	You call each of the explorers and pick only the ones that have energy above 50 units.
        //•	If you don't have any suitable explorers,
        //throw an IllegalArgumentException with the following message:
        //"You must have at least one explorer to explore the state."

        //•	After a mission, you must return the following message with the name of the explored state
        //and the count of the explorers that had retired on the mission:
        //"The state {stateName} was explored. {retiredExplorers} researchers have retired on this mission."

        List<Explorer> explorers = explorerRepository.getCollection().stream().filter(s -> s.getEnergy() > 50).collect(Collectors.toList());

        if(explorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(state, explorers);
        exploreStateCount ++;
        long retiredExplorers = explorers.stream().filter(s -> !s.canSearch()).count();

        return String.format(ConstantMessages.STATE_EXPLORER, stateName, retiredExplorers);
    }

    @Override
    public String finalResult() {
        //Returns the information about the explorers in the following format:
        //•	If the explorers don't have any suitcase exhibits, print "None" in their place.
        //"{exploredStatesCount} states were explored.
        //Information for the explorers:
        //Name: {explorerName}
        //Energy: {explorerName}
        //Suitcase exhibits: {suitcaseExhibits1, suitcaseExhibits2, suitcaseExhibits3, …, suitcaseExhibits n}"
        //…
        //Name: {explorerName}
        //Energy: {explorerName}
        //Suitcase exhibits: {suitcaseExhibits1, suitcaseExhibits2, suitcaseExhibits3, …, suitcaseExhibits n}"

        return String.format(ConstantMessages.FINAL_STATE_EXPLORED, exploreStateCount) + System.lineSeparator() +
                ConstantMessages.FINAL_EXPLORER_INFO + System.lineSeparator() +
                explorerRepository.getCollection().stream().map(Explorer::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
