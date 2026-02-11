package dev.bagel.doodads.impl;

import dev.bagel.doodads.api.DoodadType;

import java.util.*;

public class TypeRegistry {
    public static final TypeRegistry INSTANCE = new TypeRegistry();
    private final Map<String, DoodadType> loadedTypes = new HashMap<>();

    public void registerType(DoodadType typeToRegister) {
        loadedTypes.putIfAbsent(typeToRegister.getName(), typeToRegister);
    }


    public Map<String, DoodadType> getLoadedTypes() {
        return loadedTypes;
    }
}
