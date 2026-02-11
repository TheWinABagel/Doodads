package net.fabricmc.example.mixin;

import api.world.data.DataEntry;
import api.world.data.DataSyncManager;
import dev.bagel.doodads.impl.CustomPlayerDataEntry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DataSyncManager.class)
public class DataSyncManagerMixin {
    @Inject(method = "syncPlayerDataToPlayer", at = @At(value = "INVOKE", target = "Ljava/util/function/BiConsumer;accept(Ljava/lang/Object;Ljava/lang/Object;)V", shift = At.Shift.AFTER))
    private static <T> void doodads$onSync(EntityPlayerMP player, EntityPlayer owner, DataEntry.PlayerDataEntry<T> entry, CallbackInfo ci) {
//        entry.addComponent()
//        ((ExtendedDefaultStep) entry).getFunction().apply(owner);
        if (entry instanceof CustomPlayerDataEntry<T> custom) {
            custom.defaultFunction.apply(owner);
        }
    }
}
