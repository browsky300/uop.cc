package me.alpha432.oyvey.mixin.mixins;

import com.google.common.base.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import me.alpha432.oyvey.features.modules.misc.NoTrace;

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
}

