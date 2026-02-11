package dev.bagel.doodads.impl;

import api.world.data.DataEntry;
import api.world.data.DataProvider;
import api.world.data.component.DataComponent;
import api.world.data.component.DataComponents;
import dev.bagel.doodads.api.DoodadsApi;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;

public class DoodadLoader {
    private DoodadInventory inventory;
    public static final String DOODADS_NAME = "DoodadsData";
    public static final CustomPlayerDataEntry<DoodadLoader> DOODADS_DATA = new CustomPlayerDataEntry<>(
            DOODADS_NAME,
            (player) -> new DoodadLoader(new DoodadInventory(DoodadsApi.getTotalSlots(), player)),
            false,
            (tag, player) -> new DoodadLoader(tag.getCompoundTag(DOODADS_NAME), player),
            (tag, doodads) -> tag.setTag(DOODADS_NAME, doodads.saveToNBT())
    );


    public DoodadLoader(DoodadInventory inventory) {
        this.inventory = inventory;
    }

    public DoodadLoader(NBTTagCompound tag, EntityPlayer player) {
        this(loadFromNBT(tag, player));
    }

    public static DoodadInventory loadFromNBT(NBTTagCompound tag, EntityPlayer player) {
        return DoodadInventory.loadFromNBT(tag, player);
    }

    public NBTTagCompound saveToNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        inventory.saveToNBT(tag);
        return tag;
    }

    public DoodadInventory getInventory() {
        return inventory;
    }

    public DoodadLoader setInventory(DoodadInventory inventory) {
        this.inventory = inventory;
        return this;
    }
}
