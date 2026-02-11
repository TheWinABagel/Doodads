package dev.bagel.doodads.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public final class DoodadType {
    private final String name;
    private final int order;
    private final int amount;
    private final ResourceLocation iconLocation;
    private Icon icon;

    public DoodadType(String name, int order, int amount, ResourceLocation iconLocation) {
        this.name = name;
        this.order = order;
        this.amount = amount;
        this.iconLocation = iconLocation;
    }

    public boolean isBeingUsed() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public Icon backgroundIcon() {
        return icon;
    }

    public void registerIcon(IconRegister iconRegister) {
        iconRegister.registerIcon(iconLocation.toString());
    }

    public void write(NBTTagCompound tag) {

    }

    public static DoodadType read(NBTTagCompound tag) {
        return null;
    }

    public int getOrder() {
        return order;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
