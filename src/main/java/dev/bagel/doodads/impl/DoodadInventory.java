package dev.bagel.doodads.impl;

import dev.bagel.doodads.api.Doodad;
import dev.bagel.doodads.api.DoodadSlotRef;
import dev.bagel.doodads.api.DoodadType;
import dev.bagel.doodads.api.DoodadsApi;
import net.minecraft.src.*;

public class DoodadInventory implements IInventory {

    private final int invSize;
    private final ItemStack[] items;
    private final EntityPlayer player;

    public DoodadInventory(int invSize, EntityPlayer player) {
        this.invSize = invSize;
        this.items = new ItemStack[invSize];
        this.player = player;
    }

    protected DoodadInventory(int invSize, ItemStack[] items, EntityPlayer player) {
        this.invSize = invSize;
        this.items = new ItemStack[invSize];
        for (int i = 0, itemsLength = items.length; i < itemsLength; i++) {
            this.items[i] = items[i];
        }
        this.player = player;
    }

    @Override
    public int getSizeInventory() {
        return invSize;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        if (i > invSize || i < 0)
            return null;
        return items[i];
    }

    public Doodad getDoodadInSlot(int i) {
        if (getStackInSlot(i) == null || !(getStackInSlot(i).getItem() instanceof Doodad doodad))
            return null;
        return doodad;
    }

    @Override
    public ItemStack decrStackSize(int idx, int amount) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return getStackInSlot(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        items[i] = itemStack;
        Doodad doodad = getDoodad(itemStack);
        if (doodad != null) {
            doodad.onEquip(itemStack, player, new DoodadSlotRef(i, TypeRegistry.INSTANCE.getLoadedTypes().get("ring")));
        }
    }

    @Override
    public String getInvName() {
        return "Doodads Inventory";
    }

    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return itemStack.getItem() instanceof Doodad;
    }

    protected Doodad getDoodad(ItemStack stack) {
        if (stack != null && stack.getItem() instanceof Doodad doodad) {
            return doodad;
        }
        return null;
    }

    public static DoodadInventory loadFromNBT(NBTTagCompound tag, EntityPlayer player) {
        int invSize = Math.max(tag.getByte("InvSize"), DoodadsApi.getTotalSlots());
        ItemStack[] items = new ItemStack[invSize];
        NBTTagList nbttaglist = tag.getTagList("Items");
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j < 0 || j >= items.length) continue;
            items[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }
        return new DoodadInventory(invSize, items, player);
    }

    public void saveToNBT(NBTTagCompound tag) {
        tag.setByte("InvSize", (byte) invSize);
        NBTTagList list = new NBTTagList("Items");
        for (byte i = 0; i < items.length; i++) {
            ItemStack stack = items[i];
            if (stack == null) continue;
            NBTTagCompound newTag = new NBTTagCompound();
            newTag.setByte("Slot", i);
            stack.writeToNBT(newTag);
            list.appendTag(newTag);
        }
        tag.setTag("Items", list);
    }

    public void tickInventory() {
        for (int i = 0; i < items.length; i++) {
            ItemStack stack = items[i];
            Doodad doodad = getDoodad(stack);
            if (doodad != null) {
                doodad.onTick(stack, player, new DoodadSlotRef(i, TypeRegistry.INSTANCE.getLoadedTypes().get("ring")));
            }
        }
    }
}
