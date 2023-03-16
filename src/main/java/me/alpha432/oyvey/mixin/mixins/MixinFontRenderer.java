package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.client.FontMod;
import me.alpha432.oyvey.features.modules.combat.AntiUnicode;
import me.alpha432.oyvey.features.modules.combat.FCAIALM;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;

@Mixin(value = {FontRenderer.class})
public abstract class MixinFontRenderer {
    
    @Shadow
    protected abstract int getCharWidth(char v1);

    @Inject(method={"drawString(Ljava/lang/String;FFIZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    public void drawString(String text, float x, float y, int color, boolean dropShadow, CallbackInfoReturnable<Integer> ci) {
        if (FontMod.getInstance().isOn() && FontMod.getInstance().minecraft.getValue()) {
            ci.setReturnValue((int) OyVey.textManager.drawString(text, x, y, color, dropShadow));
        }
    }
    
    @Inject(method = "renderUnicodeChar", at = @At(value = "HEAD"), cancellable = true)
    private void renderUnicodeChar(char ch, boolean italic, CallbackInfoReturnable<Float> info) {
        if (AntiUnicode.getINSTANCE().isOn()) info.setReturnValue(0.0f);
    }

    @ModifyArg(method = {"renderString(Ljava/lang/String;FFIZ)I"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;renderStringAtPos(Ljava/lang/String;Z)V"), index = 0)
    public String doAliasing(String text) {
        return FCAIALM.getINSTANCE().replaceWithAliases(text);
    }

    @Overwrite
    public int getStringWidth(String text) {
        if (FontMod.getInstance().isOn() && FontMod.getInstance().minecraft.getValue()) {
            return OyVey.textManager.getStringWidth(text);
        }
        text = FCAIALM.getINSTANCE().replaceWithAliases(text);
        if (text == null) {
            return 0;
        } else {
            int i = 0;
            boolean flag = false;
            for(int j = 0; j < text.length(); ++j) {
                    char c0 = text.charAt(j);
                    int k = this.getCharWidth(c0);
                    if (k < 0 && j < text.length() - 1) {
                        ++j;
                        c0 = text.charAt(j);
                        if (c0 != 'l' && c0 != 'L') {
                            if (c0 == 'r' || c0 == 'R') {
                                flag = false;
                            }
                        } else {
                            flag = true;
                        }

                    k = 0;
                    }

                i += k;
                if (flag && k > 0) {
                    ++i;
                }
            }
            return i;
        }
    }
}

