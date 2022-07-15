package aquarium.core;

import aquarium.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private Controller controller;
    private BufferedReader reader;

    public EngineImpl() {
        this.controller = new ControllerImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals("Exit")) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IllegalStateException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }
    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Command command = Command.valueOf(tokens[0]);
        String result = null;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (command) {
            case AddAquarium:
                result = addAquarium(data);
                break;
            case AddDecoration:
                result = addDecoration(data);
                break;
            case InsertDecoration:
                result = insertDecoration(data);
                break;
            case AddFish:
                result = addFish(data);
                break;
            case FeedFish:
                result = feedFish(data);
                break;
            case CalculateValue:
                result = calculateValue(data);
                break;
            case Report:
                result = report();
                break;
            case Exit:
                result = Command.Exit.name();
                break;
        }
        return result;
    }

    private String addAquarium(String[] data) {
        String aquariumType = data[0];
        String aquariumName = data[1];

        return controller.addAquarium(aquariumType, aquariumName);
    }

    private String addDecoration(String[] data) {
        return controller.addDecoration(data[0]);
    }

    private String insertDecoration(String[] data) {
        String aquariumName = data[0];
        String decorationType = data[1];
        return controller.insertDecoration(aquariumName, decorationType);
    }

    private String addFish(String[] data) {
        String aquariumName = data[0];
        String fishType = data[1];
        String fishName = data[2];
        String fishSpecies = data[3];
        double price = Double.parseDouble(data[4]);
        return controller.addFish(aquariumName, fishType, fishName, fishSpecies, price);
    }

    private String feedFish(String[] data) {
        return controller.feedFish(data[0]);
    }

    private String calculateValue(String[] data) {
        return controller.calculateValue(data[0]);
    }

    private String report() {
        return controller.report();
    }
}