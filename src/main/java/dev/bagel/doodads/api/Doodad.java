package dev.bagel.doodads.api;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.src.AttributeModifier;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;

public interface Doodad {
    void onEquip(ItemStack stack, EntityPlayer player, DoodadSlotRef slot);

    void onUnequip(ItemStack stack, EntityPlayer player, DoodadSlotRef slot);

    void onTick(ItemStack stack, EntityPlayer player, DoodadSlotRef slot);

    boolean canEquip(ItemStack stack, EntityPlayer player, DoodadSlotRef slot);

    boolean canUnequip(ItemStack stack, EntityPlayer player, DoodadSlotRef slot);

    default Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack, EntityPlayer player, DoodadSlotRef slot) {
        return HashMultimap.create();
    }
}
