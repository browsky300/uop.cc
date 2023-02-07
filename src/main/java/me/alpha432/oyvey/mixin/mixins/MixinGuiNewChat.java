package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.misc.ChatModifier;
import me.alpha432.oyvey.util.RenderUtil;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = {GuiNewChat.class})
public class MixinGuiNewChat
        extends Gui {
    @Redirect(method = {"drawChat"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    private void drawRectHook(int left, int top, int right, int bottom, int color) {
        ChatModifier cmod = ChatModifier.getInstance();
        RenderUtil.drawGradientRect(left, top, right, bottom, cmod.isOn() && cmod.background.getValue() == ChatModifier.Background.None ? 0 : color, cmod.isOn() && cmod.background.getValue() != ChatModifier.Background.Default ? 0 : color, false);
    }

    @Redirect(method = {"setChatLine"}, at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 0))
    public int drawnChatLinesSize(List<ChatLine> list) {
        return ChatModifier.getInstance().isOn() && ChatModifier.getInstance().infinite.getValue() != false ? -2147483647 : list.size();
    }

    @Redirect(method = {"setChatLine"}, at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 2))
    public int chatLinesSize(List<ChatLine> list) {
        return ChatModifier.getInstance().isOn() && ChatModifier.getInstance().infinite.getValue() != false ? -2147483647 : list.size();
    }
}

