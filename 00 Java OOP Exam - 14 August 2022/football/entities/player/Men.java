package football.entities.player;

public class Men extends BasePlayer{
    //I can only play on NaturalGrass!
    private static final double KG = 85.50;
    private static final int STRENGTH = 145;


    public Men(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(KG);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + STRENGTH);
    }
}