package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.helpers.BaseHelper;
import fairyShop.models.helpers.Happy;
import fairyShop.models.helpers.Helper;
import fairyShop.models.helpers.Sleepy;
import fairyShop.models.instruments.Instrument;
import fairyShop.models.instruments.InstrumentImpl;
import fairyShop.models.presents.Present;
import fairyShop.models.presents.PresentImpl;
import fairyShop.models.shop.ShopImpl;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

public class ControllerImpl implements Controller {
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        //Creates a helper with the given name of the given type and adds it to the corresponding repository.
        //If the helper is invalid, throw an IllegalArgumentException with a message:
        //"Helper type doesn't exist!"
        //The method should return the following message:
        //"Successfully added {helperType} named {helperName}!"

        BaseHelper helper = null;

        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }

        helperRepository.add(helper);

        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        //Creates an instrument with the given power and adds it to the collection of the helper.
        //If the helper doesn't exist, throw an IllegalArgumentException with a message:
        //"The helper you want to add an instrument to doesn't exist!"
        //The method should return the following message:
        //"Successfully added instrument with power {instrumentPower} to helper {helperName}!"

        Helper helper = helperRepository.findByName(helperName);

        if(helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        //Creates a present with the provided name and required energy and adds it to the corresponding repository.
        //The method should return the following message:
        //•	"Successfully added Present: {presentName}!"

        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        //When the craft command is called, the action happens.
        //You should start crafting the given present, by assigning helpers which are almost ready:
        //•	The helpers that you should select are the ones with energy above 50 units.
        //•	The suitable ones start working on the given present.
        //•	If no helpers are ready, throw IllegalArgumentException with the following message:
        //"There is no helper ready to start crafting!"

        //•	After the work is done, you must return the following message,
        //reporting whether the present is done and how many instruments have been broken in the process:
        //"Present {presentName} is {done/not done}. {countBrokenInstruments} instrument/s have been broken while working on it!"
        //Note: The name of the present you receive will always be a valid one.

        BaseHelper helper = helperRepository.getModels().stream().filter(s -> s.getEnergy() > 50).findFirst().orElse(null);
        if(helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        Present present = presentRepository.findByName(presentName);

        ShopImpl shop = new ShopImpl();
        shop.craft(present, helper);

        String done = present.isDone() ? "done" : "not done";

        return String.format(ConstantMessages.PRESENT_DONE + ConstantMessages.COUNT_BROKEN_INSTRUMENTS, presentName, done, helper.getBrokenInstrument());
    }

    @Override
    public String report() {

        return presentRepository.getModels().stream().filter(Present::isDone).count() + " presents are done!" + System.lineSeparator() +
                "Helpers info:" + ((helperRepository.getModels().size() == 0) ? "" : System.lineSeparator() +
                helperRepository.toString());
        //"{countCraftedPresents} presents are done!"
        //"Helpers info:"
        //"Name: {helperName1}"
        //"Energy: {helperEnergy1}"
        //"Instruments: {countInstruments} not broken left"
        //…
        //"Name: {helperNameN}"
        //"Energy: {helperEnergyN}"
        //"Instruments: {countInstruments} not broken left";
    }
}