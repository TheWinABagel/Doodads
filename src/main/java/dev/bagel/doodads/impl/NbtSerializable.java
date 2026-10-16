package dev.bagel.doodads.impl;

import net.minecraft.src.NBTTagCompound;

public interface NbtSerializable<T> {
    T loadFromNbt(NBTTagCompound tag);

    NBTTagCompound saveToNbt(T serializable);
}
