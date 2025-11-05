package de.mark225.bluebridge.core.addon;

import de.mark225.bluebridge.core.region.RegionSnapshot;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

public class ActiveAddonEventHandler {
    private static final CopyOnWriteArrayList<RegionSnapshot> addedOrUpdated = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<RegionSnapshot> deleted = new CopyOnWriteArrayList<>();

    public static void addOrUpdate(RegionSnapshot region) {
        addedOrUpdated.add(region);
    }

    public static void delete(RegionSnapshot region) {
        deleted.add(region);
    }

    public static void resetLists() {
        addedOrUpdated.clear();
        deleted.clear();
    }

    public static void collectAndReset(BiConsumer<List<RegionSnapshot>, List<RegionSnapshot>> callback) {
        callback.accept(addedOrUpdated, deleted);
        resetLists();
    }

}
