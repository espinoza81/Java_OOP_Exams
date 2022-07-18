package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GunRepository implements Repository<Gun> {
    public Collection<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Gun gun) {
        //If the gun is null, throw a NullPointerException with a message: "Cannot add null in Gun
        //Repository".
        // Adds a gun to the collection.
        if(gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_REPOSITORY);
        }

        models.add(gun);
    }

    @Override
    public boolean remove(Gun gun) {
        //Removes a gun from the collection. Returns true if the removal was successful, otherwise - false.
        return models.remove(gun);
    }

    @Override
    public Gun findByName(String name) {
        //Returns the first gun with the given name, if there is such a gun. Otherwise, returns null.
        return models.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }
}