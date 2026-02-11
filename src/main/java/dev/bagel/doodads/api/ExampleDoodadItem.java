package dev.bagel.doodads.api;

import net.minecraft.src.*;

public class ExampleDoodadItem extends Item implements Doodad {
    public ExampleDoodadItem(int id) {
        super(id);
    }

    @Override
    public void onEquip(ItemStack stack, EntityPlayer player, DoodadSlotRef slot) {
        player.addChatMessage("Equipped doodad " + stack + " in slot " + slot);
    }

    @Override
    public void onUnequip(ItemStack stack, EntityPlayer player, DoodadSlotRef slot) {
        player.addChatMessage("Unequipped doodad " + stack + " from slot " + slot);
    }

    @Override
    public void onTick(ItemStack stack, EntityPlayer player, DoodadSlotRef slot) {
        player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 10, 2));
    }

    @Override
    public boolean canEquip(ItemStack stack, EntityPlayer player, DoodadSlotRef slot) {
        player.addChatMessage("Checking if doodad can be equipped: " + stack + " in slot " + slot);
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack stack, EntityPlayer player, DoodadSlotRef slot) {
        player.addChatMessage("Checking if doodad can be unequipped: " + stack + " in slot " + slot);
        return true;
    }
}
