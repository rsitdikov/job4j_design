package generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T old = findById(id);
        boolean rsl = old != null;
        if (rsl) {
            mem.set(mem.indexOf(old), model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        T removable = findById(id);
        boolean rsl = removable != null;
        if (rsl) {
            mem.remove(removable);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        for (T item : mem) {
            if (item.getId().equals(id)) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }
}