package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class ControllerImpl implements Controller {
    private Collection<Computer> computers;
    private Collection<Peripheral> peripherals;
    private Collection<Component> components;

    public ControllerImpl() {
        this.computers = new ArrayList<>();
        this.peripherals = new ArrayList<>();
        this.components = new ArrayList<>();
    }

    //NOTE: For each command, except for
    //"addComputer" and "buyBest",
    //you must check if a computer, with that id, exists in the collection of the computer.
    //If it doesn't, throw an IllegalArgumentException with the message
    //"Computer with this id does not exist.".

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        //Creates a computer with the correct type and adds it to the collection of computers.
        //If a computer, with the same id, already exists in the collection of the computer,
        //throw an IllegalArgumentException with the message
        //"Computer with this id already exists."
        //If the computer type is invalid,
        //throw an IllegalArgumentException with the message
        //"Computer type is invalid."
        //If it's successful, returns "Computer with id {id} added successfully.".

        Computer computer = getComputer(id);

        if(computer != null){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
        }

        switch (computerType) {
            case "DesktopComputer":
                computer = new DesktopComputer(id, manufacturer, model, price);
                break;

            case "Laptop":
                computer = new Laptop(id, manufacturer, model, price);
                break;

            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

        computers.add(computer);

        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }

    private Computer getComputer(int id) {
        return computers.stream().
                filter(s -> s.getId() == id).findFirst().
                orElse(null);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        //Creates a peripheral, with the correct type, and adds it to the computer with that id,
        //then adds it to the collection of peripherals in the controller.
        //If a peripheral, with the same id, already exists in the peripherals collection,
        //it throws an IllegalArgumentException with the message "Peripheral with this id already exists."
        //If the peripheral type is invalid,
        //throw an IllegalArgumentException with the message "Peripheral type is invalid."
        //If it's successful, it returns
        //"Peripheral {peripheral type} with id {peripheral id} added successfully in computer with id {computer id}."
        //you must check if a computer, with that id, exists in the collection of the computer.
        //If it doesn't, throw an IllegalArgumentException with the message
        //"Computer with this id does not exist.".

        Computer computer = getComputer(computerId);

        nonExistingComputer(computer);

        if(peripherals.stream().anyMatch(s -> s.getId() == id)){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }

        Peripheral peripheral = null;

        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }

        computer.addPeripheral(peripheral);
        peripherals.add(peripheral);

        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    private void nonExistingComputer(Computer computer) {
        if(computer == null ){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        //Removes a peripheral, with the given type from the computer with that id,
        //then removes the peripheral from the collection of peripherals.
        //If it's successful, it returns "Successfully removed {peripheral type} with id { peripheral id}.".

        //you must check if a computer, with that id, exists in the collection of the computer.
        //If it doesn't, throw an IllegalArgumentException with the message
        //"Computer with this id does not exist.".

        Computer computer = getComputer(computerId);

        nonExistingComputer(computer);

        Peripheral peripheral = computer.removePeripheral(peripheralType);
        peripherals.remove(peripheral);

        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        //Creates a component with the correct type and adds it to the computer with that id,
        //then adds it to the collection of components in the controller.
        //If a component, with the same id, already exists in the components collection,
        //throws an IllegalArgumentException with the message "Component with this id already exists."
        //If the component type is invalid, throw an IllegalArgumentException with the message "Component type is invalid."
        //If it's successful, returns "Component {component type} with id {component id} added successfully in computer with id {computer id}.".
        //you must check if a computer, with that id, exists in the collection of the computer.
        //If it doesn't, throw an IllegalArgumentException with the message
        //"Computer with this id does not exist.".

        Computer computer = getComputer(computerId);

        nonExistingComputer(computer);

        if(components.stream().anyMatch(s -> s.getId() == id)){
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }

        Component component = null;

        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        computer.addComponent(component);
        components.add(component);

        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        //Removes a component, with the given type from the computer with that id, then removes the component from the collection of components.
        //If it's successful, it returns "Successfully removed {component type} with id {component id}.".

        //you must check if a computer, with that id, exists in the collection of the computer.
        //If it doesn't, throw an IllegalArgumentException with the message
        //"Computer with this id does not exist.".

        Computer computer = getComputer(computerId);

        nonExistingComputer(computer);

        Component component = computer.removeComponent(componentType);
        components.remove(component);

        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        //Removes a computer, with the given id, from the collection of computers.
        //If it's successful, it returns toString method on the removed computer.

        //you must check if a computer, with that id, exists in the collection of the computer.
        //If it doesn't, throw an IllegalArgumentException with the message
        //"Computer with this id does not exist.".

        Computer computer = getComputer(id);

        nonExistingComputer(computer);

        computers.remove(computer);

        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        //Removes the computer with the highest overall performance and with a price, less or equal to the budget,
        //from the collection of computers.
        //If there are not any computers in the collection or the budget is insufficient for any computer,
        //throw an IllegalArgumentException with the message "Can't buy a computer with a budget of ${budget}."
        //If it's successful, it returns toString method on the removed computer.

        Computer computer = computers.stream().
                filter(s -> s.getPrice() <= budget).
                max(Comparator.comparingDouble(Computer::getOverallPerformance)).
                orElse(null);

        if(computer == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        }

        computers.remove(computer);

        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        //If it's successful, it returns toString method on the computer with the given id.

        //you must check if a computer, with that id, exists in the collection of the computer.
        //If it doesn't, throw an IllegalArgumentException with the message
        //"Computer with this id does not exist.".

        Computer computer = getComputer(id);

        nonExistingComputer(computer);

        return computer.toString();
    }
}