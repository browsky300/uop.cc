package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.event.events.BlockEvent;
import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.util.BlockUtil;
import me.alpha432.oyvey.util.RenderUtil;
import me.alpha432.oyvey.util.InventoryUtil;
import me.alpha432.oyvey.util.Timer;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class Instamine extends Module {
    private BlockPos renderBlock;
    private BlockPos lastBlock;
    private boolean packetCancel = false;
    private Timer breakTimer = new Timer();
    private EnumFacing direction;
    private boolean broke = false;
    private final Setting<Boolean> silent = this.register(new Setting("Silent", false));
    private final Setting<Boolean> whileEating = this.register(new Setting("WhileEating", false));
    private final Setting<Integer> delay = this.register(new Setting("Delay", 50, 0, 500));
    private final Setting<Integer> red = this.register(new Setting<Integer>("Red", 93, 0, 255));
    private final Setting<Integer> green = this.register(new Setting<Integer>("Green", 2, 0, 255));
    private final Setting<Integer> blue = this.register(new Setting<Integer>("Blue", 198, 0, 255));
    private final Setting<Integer> alpha = this.register(new Setting<Integer>("Alpha", 120, 0, 255));
    private final Setting<Integer> outlineAlpha = this.register(new Setting<Integer>("OutlineAlpha", 150, 0, 255));

    public Instamine() {
        super("Instamine", "mine but instant", Category.PLAYER, true, false, false);
    }

    @SubscribeEvent
    @Override
    public void onRender3D(Render3DEvent event) {
        if (this.renderBlock != null) {
            Color color = new Color(red.getValue(), green.getValue(), blue.getValue(), outlineAlpha.getValue());
            RenderUtil.drawBoxESP(this.renderBlock, color, false, color, 1.2f, true, true, alpha.getValue(), false);
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketPlayerDigging) {
            CPacketPlayerDigging digPacket = (CPacketPlayerDigging)event.getPacket();
            if (digPacket.getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK && this.packetCancel) {
                event.setCanceled(true);
            }
        }
    }

    @Override
    public void onTick() {
        if (this.renderBlock != null) {
            if (this.breakTimer.passedMs(this.delay.getValue())) {
                this.breakTimer.reset();
                if (mc.world.getBlockState(renderBlock).getBlock() == Blocks.AIR) return;
                if (mc.player.isHandActive() && !whileEating.getValue()) return;
                if (mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() != Items.DIAMOND_PICKAXE) {
                    if (silent.getValue()) {
                        int pickSlot = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                        if (pickSlot == -1) return;
                        mc.player.connection.sendPacket(new CPacketHeldItemChange(pickSlot));
                    } else {
                        return;
                    }
                }
                mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.renderBlock, this.direction));
                if (silent.getValue()) mc.player.connection.sendPacket(new CPacketHeldItemChange(mc.player.inventory.currentItem));
                return;
            }
        }
        try {
            mc.playerController.blockHitDelay = 0;
        } catch (Exception ignored) {

        }
    }

    @SubscribeEvent
    public void onBlockEvent(BlockEvent event) {
        if (fullNullCheck()) {
            return;
        }
        if (event.getStage() == 3 && mc.playerController.curBlockDamageMP > 0.1f) {
            mc.playerController.isHittingBlock = true;
        }
        if (event.getStage() == 4 && BlockUtil.canBreak(event.pos)) {
            mc.playerController.isHittingBlock = false;
            if (this.canBreak(event.pos)) {
                if (this.lastBlock == null || event.pos.getX() != this.lastBlock.getX() || event.pos.getY() != this.lastBlock.getY() || event.pos.getZ() != this.lastBlock.getZ()) {
                    this.packetCancel = false;
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                    mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, event.pos, event.facing));
                    this.packetCancel = true;
                }
                else {
                    this.packetCancel = true;
                }
                mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, event.pos, event.facing));
                this.renderBlock = event.pos;
                this.lastBlock = event.pos;
                this.direction = event.facing;
                event.setCanceled(true);
            }
        }
    }

    private boolean canBreak(BlockPos pos) {
        IBlockState blockState = mc.world.getBlockState(pos);
        Block block = blockState.getBlock();
        return block.getBlockHardness(blockState, mc.world, pos) != -1.0f;
    }
}

