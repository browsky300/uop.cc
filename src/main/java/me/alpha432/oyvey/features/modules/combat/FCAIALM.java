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
    enum CAMode {None, AP, One, Default}
    enum SurroundMode {AntiDie, AutoObsidian, Surround, Default}
    Setting<CAMode> camode = register(new Setting("CA", CAMode.Default));
    Setting<SurroundMode> surroundmode = register(new Setting("Surround", SurroundMode.Default));
    Setting<Boolean> doWatermark = register(new Setting("Watermark", false));
    Setting<String> cWatermark = register(new Setting("W", "Future v2.11.1", v -> doWatermark.getValue()));
    Setting<Boolean> noinfo = register(new Setting("NoInfo", false));
    boolean APstringAttacking = true;
    boolean APhasPlaced = false;
    boolean APhasAttacked = false;
    boolean APpacketAttacking = true;
    boolean APnothing = true;
    Timer APtimer = new Timer();

    public FCAIALM() {
        super("FCAIALM", "very cool", Category.COMBAT, true, false, false);
        this.setInstance();
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {

        if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            CPacketPlayerTryUseItemOnBlock placePacket = event.getPacket();
            if (placePacket.hand == EnumHand.MAIN_HAND) {
                if (mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
                    APhasPlaced = true;
                    APpacketAttacking = false;
                }
            } else {
                if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                    APhasPlaced = true;
                    APpacketAttacking = false;
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

    public String replaceWithAliases(String text) {
        if (!isOn()) return text;

        if (text.contains(" " + ChatFormatting.GRAY + "[") && noinfo.getValue()) {
            return text.replaceAll(" " + ChatFormatting.GRAY + "\\[.*]", "");
        }

        if (text.startsWith("AutoCrystal " + ChatFormatting.GRAY + "[")) {

            switch (camode.getValue()) {
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
                    return text.replaceAll("\\,.*]", "]");
                }
                default: {
                    return text;
                }
            }
        }

        if (text.startsWith("FeetTrap " + ChatFormatting.GRAY + "[")) {
            switch (surroundmode.getValue()) {
                case AntiDie: {
                    return "AntiDie";
                }
                case AutoObsidian: {
                    return "AutoObsidian";
                }
                case Surround: {
                    return "Surround";
                }
                default: {
                    return text;
                }
            }
        }

        if (text.startsWith("Future v") && doWatermark.getValue()) {
            return cWatermark.getValue();
        }
        
        return text;
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

