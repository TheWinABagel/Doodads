package dev.bagel.doodads.impl;

import dev.bagel.doodads.api.Doodad;
import dev.bagel.doodads.api.DoodadSlotRef;
import dev.bagel.doodads.api.DoodadType;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Icon;
import net.minecraft.src.Slot;

public final class DoodadSlot extends Slot {
    private final DoodadType doodadType;
    private final DoodadSlotRef slotRef;

    public DoodadSlot(DoodadInventory inventory, DoodadType doodadType, int id, int xPos, int yPos) {
        super(inventory, id, xPos, yPos);
        this.doodadType = doodadType;
        this.slotRef = new DoodadSlotRef(id, doodadType);
    }

    public void test(EntityPlayer player) {
        player.getData(DoodadLoader.DOODADS_DATA).getInventory();
    }

    @Override
    public Icon getBackgroundIconIndex() {
        return doodadType.backgroundIcon();
    }

    public DoodadType getDoodadType() {
        return doodadType;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player) {
        Doodad doodad = getDoodad();

        return doodad != null && doodad.canUnequip(getStack(), player, slotRef);
    }

    public Doodad getDoodad() {
        if (getStack() != null && getStack().getItem() instanceof Doodad doodad) {
            return doodad;
        }
        return null;
    }
}
