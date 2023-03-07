package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.util.Timer;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.mojang.realmsclient.gui.ChatFormatting;

public class FCAIALM extends Module {
    private static FCAIALM INSTANCE = new FCAIALM();
    enum FutureVersion {None, AP, One}
    Setting<FutureVersion> futureversion = register(new Setting("Version", FutureVersion.None));
    boolean APstringAttacking = true;
    boolean APhasPlaced = false;
    boolean APhasAttacked = false;
    boolean APpacketAttacking = true;
    boolean APhasAltSilentSwitched = false;
    boolean APnothing = true;
    Timer APtimer = new Timer();
    int heldSlot = 0;

    public FCAIALM() {
        super("FCAIALM", "very cool", Category.COMBAT, true, false, false);
        this.setInstance();
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketHeldItemChange) {
            CPacketHeldItemChange switchPacket = event.getPacket();
            heldSlot = switchPacket.getSlotId();
        }

        if (event.getPacket() instanceof CPacketClickWindow) {
            CPacketClickWindow clickPacket = event.getPacket();
            if (clickPacket.getWindowId() == 0) APhasAltSilentSwitched = true;
        }

        if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            CPacketPlayerTryUseItemOnBlock placePacket = event.getPacket();
            if (placePacket.hand == EnumHand.MAIN_HAND) {
                if (mc.player.inventory.getStackInSlot(heldSlot).getItem() == Items.END_CRYSTAL || APhasAltSilentSwitched) {
                    APhasPlaced = true;
                    APpacketAttacking = false;
                    APhasAltSilentSwitched = false;
                }
            } else {
                if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                    APhasPlaced = true;
                    APpacketAttacking = false;
                    APhasAltSilentSwitched = false;
                }
            }
        }

        if (event.getPacket() instanceof CPacketUseEntity) {
            CPacketUseEntity attackPacket = event.getPacket();
            if (attackPacket.getEntityFromWorld(mc.world) instanceof EntityEnderCrystal) {
                APhasAttacked = true;
                APpacketAttacking = true;
            }
        }

        if (event.getPacket().getClass().getName().contains("net.futureclient.client")) {
            APhasAttacked = true;
            APpacketAttacking = true;
        }
    }

    @Override
    public void onUpdate() {
        if (APtimer.passedMs(250)) {
            if (APhasAttacked && APhasPlaced) {
                APnothing = false;
                APstringAttacking = !APstringAttacking;
            } else if (APhasAttacked || APhasPlaced) {
                APnothing = false;
                APstringAttacking = APpacketAttacking;
            } else {
                APnothing = true;
            }
            APhasPlaced = false;
            APhasAttacked = false;
            APtimer.reset();
        }
    }

    public String getCAString(String original) {
        if (!original.startsWith("AutoCrystal " + ChatFormatting.GRAY + "[")) return original;
        switch (futureversion.getValue()) {
            case None: {
                return "AutoCrystal";
            }
            case AP: {
                if (APnothing) {
                    return "AutoCrystal";
                } else {
                    return "AutoCrystal " + ChatFormatting.GRAY + "[" + ChatFormatting.WHITE + (APstringAttacking ? "Attack" : "Place") + ChatFormatting.GRAY + "]";
                }
            }
            case One: {
                return original.replaceAll("\\,.*]", "]");
            }
            default: {
                return original;
            }
        }
    }


    public static FCAIALM getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new FCAIALM();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

