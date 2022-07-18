package fairyShop.repositories;

import fairyShop.models.helpers.BaseHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class HelperRepository implements Repository<BaseHelper> {
    private Collection<BaseHelper> helpers;

    public HelperRepository() {
        this.helpers = new ArrayList<>();
    }

    @Override
    public Collection<BaseHelper> getModels() {
        return Collections.unmodifiableCollection(helpers);
    }

    @Override
    public void add(BaseHelper helper) {
        helpers.add(helper);
    }

    @Override
    public boolean remove(BaseHelper helper) {
        return helpers.remove(helper);
    }

    @Override
    public BaseHelper findByName(String name) {
        return helpers.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return getModels().stream().map(BaseHelper::getInfo).collect(Collectors.joining(System.lineSeparator()));
    }
}