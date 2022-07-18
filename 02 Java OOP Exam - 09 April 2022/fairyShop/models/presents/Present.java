package fairyShop.models.presents;

public interface Present {
    String getName();

    int getEnergyRequired();

    boolean isDone();

    void getCrafted();
}