package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.OyVey;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.alpha432.oyvey.features.modules.render.RusherCapes;
import java.util.UUID;
import javax.annotation.Nullable;
import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.modules.render.PlayerTweaks;

@Mixin(value={AbstractClientPlayer.class})
public abstract class MixinAbstractClientPlayer {
    @Shadow
    @Nullable
    protected abstract NetworkPlayerInfo getPlayerInfo();
    
    @Inject(method={"getLocationSkin()Lnet/minecraft/util/ResourceLocation;"}, at={@At(value="HEAD")}, cancellable=true)
    public void getLocationSkin(CallbackInfoReturnable<ResourceLocation> ci) {
        if (PlayerTweaks.getINSTANCE().isOn()) {
            switch (PlayerTweaks.getINSTANCE().skin.getValue()) {
                case Alex: {
                    ci.setReturnValue(new ResourceLocation("textures/entity/alex.png"));
                    break;
                }
                case Steve: {
                    ci.setReturnValue(new ResourceLocation("textures/entity/steve.png"));
                    break;
                }
                case Thunder: {
                    ci.setReturnValue(new ResourceLocation("textures/thunderskin.png"));
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    @Inject(method={"getLocationCape"}, at={@At(value="HEAD")}, cancellable=true)
    public void getLocationCape(CallbackInfoReturnable<ResourceLocation> ci) {
        if (PlayerTweaks.getINSTANCE().isOn() && PlayerTweaks.getINSTANCE().nocape.getValue()) {
            ci.cancel();
        }
        if (RusherCapes.getINSTANCE().isOn()) {
            NetworkPlayerInfo info = this.getPlayerInfo();
            UUID uuid = null;
            if (info != null) {
                uuid = this.getPlayerInfo().getGameProfile().getId();
            }
            String capeID = "none";
            if (uuid != null) {
                capeID = RusherCapes.getCape(uuid);
            }
            if (capeID != "none") {
                ci.setReturnValue(new ResourceLocation("textures/rushercapes/" + capeID + ".png"));
            }
        }
    }
}
