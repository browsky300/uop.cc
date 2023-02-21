package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.client.FontMod;
import me.alpha432.oyvey.features.modules.combat.AntiUnicode;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.alpha432.oyvey.OyVey;

@Mixin(value = {FontRenderer.class})
public abstract class MixinFontRenderer {
    @Inject(method={"drawString(Ljava/lang/String;FFIZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    public void drawString(String text, float x, float y, int color, boolean dropShadow, CallbackInfoReturnable<Integer> ci) {
        if (FontMod.getInstance().isOn() && FontMod.getInstance().minecraft.getValue()) {
            ci.setReturnValue((int) OyVey.textManager.drawString(text, x, y, color, dropShadow));
        }
    }
    
    @Inject(method={"getStringWidth(Ljava/lang/String;)I"}, at={@At(value="HEAD")}, cancellable=true)
    public void getStringWidth(String text, CallbackInfoReturnable<Integer> ci) {
        if (FontMod.getInstance().isOn() && FontMod.getInstance().minecraft.getValue()) {
            ci.setReturnValue(OyVey.textManager.getStringWidth(text));
        }
    }
    
    @Inject(method = "renderUnicodeChar", at = @At(value = "HEAD"), cancellable = true)
    private void renderUnicodeChar(char ch, boolean italic, CallbackInfoReturnable<Float> info) {
        if (AntiUnicode.getINSTANCE().isOn()) info.setReturnValue(0.0f);
    }
}

