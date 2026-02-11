package net.fabricmc.example.mixin;

import dev.bagel.doodads.impl.DoodadLoader;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerMP.class)
public abstract class EntityPlayerMPMixin extends EntityPlayer {
    public EntityPlayerMPMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void doodad$onUpdate(CallbackInfo ci) {
        this.getData(DoodadLoader.DOODADS_DATA).getInventory().tickInventory();
    }
}
