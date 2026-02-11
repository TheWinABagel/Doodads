package dev.bagel.doodads;

import api.BTWAddon;
import api.network.CustomPacketHandler;
import dev.bagel.doodads.api.DoodadType;
import dev.bagel.doodads.api.ExampleDoodadItem;
import dev.bagel.doodads.impl.DoodadLoader;
import dev.bagel.doodads.impl.TypeRegistry;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.ResourceLocation;

public class Doodads extends BTWAddon {
    public static final Doodads INSTANCE = new Doodads();

    public Doodads() {
        super();
    }

    @Override
    public void initialize() {
        DoodadLoader.DOODADS_DATA.getClass();
        DoodadLoader.DOODADS_DATA.register();
        TypeRegistry.INSTANCE.registerType(new DoodadType("ring", 0, 2, new ResourceLocation("doodads:icon")));
    }

    @Override
    public void registerPacketHandler(String channel, CustomPacketHandler handler) {

    }
    public static final ExampleDoodadItem item = new ExampleDoodadItem(3232);
    @Override
    public void serverPlayerConnectionInitialized(NetServerHandler serverHandler, EntityPlayerMP playerMP) {
        playerMP.getData(DoodadLoader.DOODADS_DATA).getInventory().setInventorySlotContents(0, new ItemStack(item, 2, 3));
    }
}