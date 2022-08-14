package football.entities.player;

public class Women extends BasePlayer{
    //I can only play on ArtificialTurf!
    private static final double KG = 60.0;
    private static final int STRENGTH = 115;


    public Women(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(KG);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + STRENGTH);
    }
}