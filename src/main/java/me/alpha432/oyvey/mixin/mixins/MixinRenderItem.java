package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.OyVey;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import me.alpha432.oyvey.features.modules.render.ItemScale;
import me.alpha432.oyvey.features.modules.render.GlintTweaks;

import java.awt.Color;

@Mixin(value={RenderItem.class})
public class MixinRenderItem {

    @Inject(method={"renderItemModel"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift=At.Shift.BEFORE)})
    private void renderItemModel(ItemStack stack, IBakedModel bakedModel, ItemCameraTransforms.TransformType transform, boolean leftHanded, CallbackInfo ci) {
        if (ItemScale.getINSTANCE().isOn()) {
            GlStateManager.scale(ItemScale.getINSTANCE().X.getValue(), ItemScale.getINSTANCE().Y.getValue(), ItemScale.getINSTANCE().Z.getValue());
        }
    }
    
    @ModifyArg(method = {"renderEffect"}, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/RenderItem.renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"), index = 1)
    private int glintColor(int color) {
        if (GlintTweaks.getINSTANCE().isOn() && GlintTweaks.getINSTANCE().color.getValue()) {
            return new Color(GlintTweaks.getINSTANCE().red.getValue(), GlintTweaks.getINSTANCE().green.getValue(), GlintTweaks.getINSTANCE().blue.getValue()).getRGB();
        }
        return color;
    }
    
    @ModifyArg(method = {"renderEffect"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V"), index = 0)
    private float glintSpeed(float f) {
        if (GlintTweaks.getINSTANCE().isOn()) {
            double speed = GlintTweaks.getINSTANCE().speed.getValue();
            if (f > 0) {
                return (float)(Minecraft.getSystemTime() * speed % 3000L) / 3000.0F / 8.0F;
            } else {
                return (float)(Minecraft.getSystemTime() * speed % 4873L) / 4873.0F / 8.0F * -1;
            }
        }
        return f;
    }
}
