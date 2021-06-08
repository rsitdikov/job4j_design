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
        int index = searchIndex(id);
        boolean rsl = index >= 0;
        if (rsl) {
            mem.set(index, model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        int index = searchIndex(id);
        boolean rsl = index >= 0;
        if (rsl) {
            mem.remove(index);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        int index = searchIndex(id);
        if (index >= 0) {
            rsl = mem.get(index);
        }
        return rsl;
    }

    private int searchIndex(String id) {
        int rsl = -1;
        for (int index = 0; index < mem.size(); index++) {
            if (mem.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }
}