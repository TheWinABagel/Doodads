package net.fabricmc.example.mixin;

import dev.bagel.doodads.api.DoodadType;
import dev.bagel.doodads.impl.TypeRegistry;
import net.minecraft.src.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureMap.class)
public abstract class TextureMapMixin {

    @Inject(method = "registerIcons", at = @At("TAIL"))
    private void doodads$registerIconsToItemAtlas(CallbackInfo ci) {
        TypeRegistry.INSTANCE.getLoadedTypes().values().forEach(type -> type.registerIcon((TextureMap) (Object) this));
    }
}
