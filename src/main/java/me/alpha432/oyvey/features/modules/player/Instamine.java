package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.event.events.BlockEvent;
import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.util.BlockUtil;
import me.alpha432.oyvey.util.RenderUtil;
import me.alpha432.oyvey.util.Timer;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class Instamine extends Module {
    private BlockPos renderBlock;
    private BlockPos lastBlock;
    private boolean packetCancel;
    private Timer breakTimer;
    private EnumFacing direction;
    private boolean broke;
    private Setting<Integer> delay;
    private Setting<Boolean> onlyPickaxe;
    private final Setting<Integer> red = this.register(new Setting<Integer>("Red", 93, 0, 255));
    private final Setting<Integer> green = this.register(new Setting<Integer>("Green", 2, 0, 255));
    private final Setting<Integer> blue = this.register(new Setting<Integer>("Blue", 198, 0, 255));
    private final Setting<Integer> alpha = this.register(new Setting<Integer>("Alpha", 120, 0, 255));
    private final Setting<Integer> outlineAlpha = this.register(new Setting<Integer>("OutlineAlpha", 150, 0, 255));
    private final Setting<Float> outlineWidth = this.register(new Setting<Float>("OutlineWidth", Float.valueOf(1.2f), Float.valueOf(0.1f), Float.valueOf(5.0f)));

    public Instamine() {
        super("Instamine", "mine but instant", Category.PLAYER, true, false, false);
        this.breakTimer = new Timer();
        this.packetCancel = false;
        this.broke = false;
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", 50, 0, 500));
        this.onlyPickaxe = (Setting<Boolean>)this.register(new Setting("OnlyPickaxe", true));
    }

    @SubscribeEvent
    @Override
    public void onRender3D(Render3DEvent event) {
        if (this.renderBlock != null) {
            Color color = new Color(red.getValue(), green.getValue(), blue.getValue(), outlineAlpha.getValue());
            RenderUtil.drawBoxESP(this.renderBlock, color, false, color, outlineWidth.getValue(), true, true, alpha.getValue(), false);
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
                if (this.onlyPickaxe.getValue() && Instamine.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() != Items.DIAMOND_PICKAXE) {
                    return;
                }
                Instamine.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.renderBlock, this.direction));
                return;
            }
        }
        try {
            Instamine.mc.playerController.blockHitDelay = 0;
        } catch (Exception ignored) {

        }
    }

    @SubscribeEvent
    public void onBlockEvent(BlockEvent event) {
        if (fullNullCheck()) {
            return;
        }
        if (event.getStage() == 3 && Instamine.mc.playerController.curBlockDamageMP > 0.1f) {
            Instamine.mc.playerController.isHittingBlock = true;
        }
        if (event.getStage() == 4 && BlockUtil.canBreak(event.pos)) {
            Instamine.mc.playerController.isHittingBlock = false;
            if (this.canBreak(event.pos)) {
                if (this.lastBlock == null || event.pos.getX() != this.lastBlock.getX() || event.pos.getY() != this.lastBlock.getY() || event.pos.getZ() != this.lastBlock.getZ()) {
                    this.packetCancel = false;
                    Instamine.mc.player.swingArm(EnumHand.MAIN_HAND);
                    Instamine.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, event.pos, event.facing));
                    this.packetCancel = true;
                }
                else {
                    this.packetCancel = true;
                }
                Instamine.mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, event.pos, event.facing));
                this.renderBlock = event.pos;
                this.lastBlock = event.pos;
                this.direction = event.facing;
                event.setCanceled(true);
            }
        }
    }

    private boolean canBreak(BlockPos pos) {
        IBlockState blockState = Instamine.mc.world.getBlockState(pos);
        Block block = blockState.getBlock();
        return block.getBlockHardness(blockState, Instamine.mc.world, pos) != -1.0f;
    }
    
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(delay.getValue());
    }
}

