package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.event.events.ChatEvent;
import me.alpha432.oyvey.event.events.UpdateWalkingPlayerEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.alpha432.oyvey.features.modules.player.Swing;
import me.alpha432.oyvey.util.Util;
import net.minecraft.util.EnumHand;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.init.Items;

import me.alpha432.oyvey.event.events.MoveEvent;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.entity.MoverType;

@Mixin(value = {EntityPlayerSP.class}, priority = 9998)
public abstract class MixinEntityPlayerSP
        extends AbstractClientPlayer {
    public MixinEntityPlayerSP(Minecraft p_i47378_1_, World p_i47378_2_, NetHandlerPlayClient p_i47378_3_, StatisticsManager p_i47378_4_, RecipeBook p_i47378_5_) {
        super(p_i47378_2_, p_i47378_3_.getGameProfile());
    }

    @Inject(method = {"sendChatMessage"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void sendChatMessage(String message, CallbackInfo callback) {
        ChatEvent chatEvent = new ChatEvent(message);
        MinecraftForge.EVENT_BUS.post(chatEvent);
    }

    @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At(value = "HEAD")})
    private void preMotion(CallbackInfo info) {
        UpdateWalkingPlayerEvent event = new UpdateWalkingPlayerEvent(0);
        MinecraftForge.EVENT_BUS.post(event);
    }

    @Inject(method = {"onUpdateWalkingPlayer"}, at = {@At(value = "RETURN")})
    private void postMotion(CallbackInfo info) {
        UpdateWalkingPlayerEvent event = new UpdateWalkingPlayerEvent(1);
        MinecraftForge.EVENT_BUS.post(event);
    }
    
    @Inject(method = {"swingArm"}, at = { @At("HEAD") }, cancellable = true)
    public void swingArm(EnumHand enumHand, CallbackInfo info) {
        if (Swing.getINSTANCE().isEnabled()) {
            switch ((Swing.getINSTANCE()).swingmode.getValue()) {
                case Mainhand: {
                    super.swingArm(EnumHand.MAIN_HAND);
                    break;
                }
                case Offhand: {
                    super.swingArm(EnumHand.OFF_HAND);
                    break;
                }
                case Offhandc: {
                    if (Util.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || Util.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                        super.swingArm(EnumHand.OFF_HAND);
                    } else {
                        super.swingArm(EnumHand.MAIN_HAND);
                    }
                    break;
                }
                case None: {
                    break;
                }
                case Default: {
                    super.swingArm(enumHand);
                    break;
                }
            }
            Util.mc.getConnection().sendPacket(new CPacketAnimation(enumHand));
            info.cancel();
        }
    }
    
    @Redirect(method={"move"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(AbstractClientPlayer player, MoverType moverType, double x, double y, double z) {
        MoveEvent event = new MoveEvent(0, moverType, x, y, z);
        MinecraftForge.EVENT_BUS.post(event);
        if (!event.isCanceled()) {
            super.move(event.getType(), event.getX(), event.getY(), event.getZ());
        }
    }
}

