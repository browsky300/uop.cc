package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.client.FontMod;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.alpha432.oyvey.OyVey;


@Mixin(value = {FontRenderer.class})
public abstract class MixinFontRenderer {
    @Inject(method={"drawString(Ljava/lang/String;FFIZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    public void drawString(String text, float x, float y, int color, boolean dropShadow, CallbackInfoReturnable<Integer> ci) {
        if (FontMod.getInstance().isOn() && FontMod.getInstance().minecraft.getValue()) {
            OyVey.textManager.drawString(text, x, y, color, dropShadow);
            ci.setReturnValue((int)x);
        }
    }
}

