package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.render.PlayerTweaks;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class MixinEntity {
    @Inject(method = "isSneaking", at = @At(value = "HEAD"), cancellable = true)
    public void isSneakingHook(CallbackInfoReturnable<Boolean> info) {
        if (PlayerTweaks.getINSTANCE().isOn() && PlayerTweaks.getINSTANCE().sneak.getValue()) {
            info.setReturnValue(true);
        }
    }
}
