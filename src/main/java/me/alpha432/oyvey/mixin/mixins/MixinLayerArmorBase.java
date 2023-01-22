package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.render.PlayerTweaks;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {LayerArmorBase.class})
public class MixinLayerArmorBase {
    @Inject(method = {"doRenderLayer"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo info) {
        if (entitylivingbaseIn instanceof EntityPlayer && PlayerTweaks.getINSTANCE().isOn() && PlayerTweaks.getINSTANCE().noarmor.getValue()) {
            info.cancel();
        }
    }
}
