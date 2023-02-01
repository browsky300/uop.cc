package me.alpha432.oyvey.mixin.mixins;

import com.google.common.base.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import me.alpha432.oyvey.features.modules.player.NoTrace;
import me.alpha432.oyvey.features.modules.render.AspectRatio;
import org.lwjgl.util.glu.Project;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = {EntityRenderer.class})
public class MixinEntityRenderer {
    @Redirect(method = {"getMouseOver"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List<Entity> getEntitiesInAABBexcluding(WorldClient worldClient, Entity entityIn, AxisAlignedBB boundingBox, Predicate predicate) {
        if (NoTrace.getINSTANCE().isOn()) {
            switch (NoTrace.getINSTANCE().notracemode.getValue()) {
                case Pickaxe: {
                    if (Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                        return new ArrayList<Entity>();
                    }
                    break;
                }
                case Gap: {
                    if (Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemPickaxe || Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
                        return new ArrayList<Entity>();
                    }
                    break;
                }
                case Notcrystal: {
                    if (!(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL)) {
                        return new ArrayList<Entity>();
                    }
                    break;
                }
                case All: {
                    return new ArrayList<Entity>();
                }
            }
        }
        return worldClient.getEntitiesInAABBexcluding(entityIn, boundingBox, predicate);
    }
    
    @Redirect(method = "setupCameraTransform", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onSetupCameraTransform(float fovy, float aspect, float zNear, float zFar) {
        Project.gluPerspective(fovy, AspectRatio.getINSTANCE().isOn() ? AspectRatio.getINSTANCE().ratio.getValue() : aspect, zNear, zFar);
    }

    @Redirect(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderWorldPass(float fovy, float aspect, float zNear, float zFar) {
        Project.gluPerspective(fovy, AspectRatio.getINSTANCE().isOn() ? AspectRatio.getINSTANCE().ratio.getValue() : aspect, zNear, zFar);
    }

    @Redirect(method = "renderCloudsCheck", at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderCloudsCheck(float fovy, float aspect, float zNear, float zFar) {
        Project.gluPerspective(fovy, AspectRatio.getINSTANCE().isOn() ? AspectRatio.getINSTANCE().ratio.getValue() : aspect, zNear, zFar);
    }
}

