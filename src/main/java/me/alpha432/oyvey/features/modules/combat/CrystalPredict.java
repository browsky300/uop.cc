package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.event.events.PacketEvent;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CrystalPredict extends Module {

    public Setting<Boolean> holding = this.register(new Setting<Boolean>("Holding", Boolean.valueOf(true), "only works when youre holding a crystal"));
    public Setting<Integer> offset = this.register(new Setting<Integer>("Offset", 1, 1, 20));
    public Setting<Integer> amount = this.register(new Setting<Integer>("Amount", 1, 1, 20));

    public CrystalPredict() {
        super("CrystalPredict", "fast crystal", Category.COMBAT, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        if (!(event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock)) return;
        
        int id = 0;
        CPacketPlayerTryUseItemOnBlock packet = event.getPacket();
        
        if (!(mc.world.getBlockState(packet.position).getBlock() == Blocks.BEDROCK || mc.world.getBlockState(packet.position).getBlock() == Blocks.OBSIDIAN)) return;
        
        if (holding.getValue() && !(mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL)) return;
        
        for (Entity entity : mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal) {
                if (entity.entityId > id) id = entity.entityId;
            }
        }
        
        for (int i = offset.getValue(); i < offset.getValue() + amount.getValue(); i++) {
            CPacketUseEntity attackpacket = new CPacketUseEntity();
            attackpacket.entityId = id + i;
            attackpacket.action = CPacketUseEntity.Action.ATTACK;
            mc.player.connection.sendPacket(attackpacket);
        }
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(offset.getValue()) + ", " + String.valueOf(amount.getValue());
    }
} 
