package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.render.DebugCrosshair;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame {
    
    @Inject(method = "renderAttackIndicator", at = @At(value = "HEAD"), cancellable = true)
    public void renderAttackIndicatorHook(CallbackInfo info) {
        if (DebugCrosshair.getINSTANCE().isOn()) info.cancel();
    }
}