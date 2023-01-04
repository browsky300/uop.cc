package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.event.events.Render2DEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Dashboard extends Module {

    public Dashboard() {
        super("Dashboard", "Displays some info for PvP", Category.CLIENT, true, false, false);
    }

    public void onRender2D(Render2DEvent event) {
        if (fullNullCheck()) return;
        
        int totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> (itemStack.getItem() == Items.TOTEM_OF_UNDYING)).mapToInt(ItemStack::getCount).sum();
        if (mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING)
            totems += mc.player.getHeldItemOffhand().getCount();
        String totemCounterPlural = "";
        if (totems != 1)
            totemCounterPlural = "s";
        
        int sets = 0;
        sets += mc.player.inventory.mainInventory.stream().filter(itemStack -> (itemStack.getItem() == Items.DIAMOND_HELMET || itemStack.getItem() == Items.DIAMOND_CHESTPLATE || itemStack.getItem() == Items.DIAMOND_LEGGINGS || itemStack.getItem() == Items.DIAMOND_BOOTS)).mapToInt(ItemStack::getCount).sum();
        sets += mc.player.inventory.armorInventory.stream().filter(itemStack -> (itemStack.getItem() == Items.DIAMOND_HELMET || itemStack.getItem() == Items.DIAMOND_CHESTPLATE || itemStack.getItem() == Items.DIAMOND_LEGGINGS || itemStack.getItem() == Items.DIAMOND_BOOTS)).mapToInt(ItemStack::getCount).sum();
        sets /= 4;
        String setCounterPlural = "";
        if (sets != 1)
            setCounterPlural = "s";
        
        this.renderer.drawString("uop.cc", 2, 200, 16776960, true);
        this.renderer.drawString(totems + " totem" + totemCounterPlural, 2, 209, 16777215, true);
        this.renderer.drawString(sets + " set" + setCounterPlural, 2, 218, 16777215, true);
    }
} 
