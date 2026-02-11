package dev.bagel.doodads.api;

import dev.bagel.doodads.impl.TypeRegistry;

public class DoodadsApi {
    public static int getTotalSlots() {
        return TypeRegistry.INSTANCE.getLoadedTypes().values().stream().mapToInt(DoodadType::getAmount).sum();
    }
}
