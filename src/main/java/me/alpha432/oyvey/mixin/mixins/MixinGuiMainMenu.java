package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.client.MainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {GuiMainMenu.class})
public class MixinGuiMainMenu extends GuiScreen {
    @Shadow
    private String splashText;
    
    @Inject(method = {"initGui"}, at = {@At(value = "RETURN")}, cancellable = true)
    public void initGuiHook(CallbackInfo info) {
        if (MainMenu.getINSTANCE().isOn() && MainMenu.getINSTANCE().customsplash.getValue()) {
            this.splashText = MainMenu.getINSTANCE().splashtext.getValue();
        }
    }
}

