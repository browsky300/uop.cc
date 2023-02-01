package me.alpha432.oyvey.features.modules.client;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.util.ColorUtil;
import me.alpha432.oyvey.OyVey;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import me.alpha432.oyvey.features.modules.client.ClickGui;

public class Dashboard extends Module {

    public enum DashName {Uop, Medication, Viknet, Meowgod, Gothmoney}
    public Setting<DashName> dashname = register(new Setting("Name", DashName.Uop));
    public Setting<Boolean> sync = register(new Setting("Sync", false));
    public Setting<Integer> posX = register(new Setting("X", 2, 0, 1000));
    public Setting<Integer> posY = register(new Setting("Y", 200, 0, 500));

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
            
        String name = "";
        int namecolor = 0;
        switch (dashname.getValue()) {
            case Uop: {
                name = "uop.cc";
                namecolor = ColorUtil.toRGBA(255, 255, 0);
                break;
            }
            case Medication: {
                name = "medication.eu";
                namecolor = ColorUtil.toRGBA(192, 0, 255);
                break;
            }
            case Viknet: {
                name = "viknet.se";
                namecolor = ColorUtil.toRGBA(0, 255, 0);
                break;
            }
            case Meowgod: {
                name = "meowgod.cat";
                namecolor = ColorUtil.toRGBA(255, 128, 255);
                break;
            }
            case Gothmoney: {
                name = "goth.money";
                namecolor = ColorUtil.toRGBA(16, 16, 16);
                break;
            }
        }
        if (sync.getValue()) {
            if ((ClickGui.getInstance()).rainbow.getValue().booleanValue()) {
                if ((ClickGui.getInstance()).rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                    this.renderer.drawString(name, posX.getValue(), posY.getValue(), ColorUtil.rainbow((ClickGui.getInstance()).rainbowHue.getValue()).getRGB(), true);
                } else {
                    int[] arrayOfInt = {1};
                    char[] stringToCharArray = name.toCharArray();
                    float f = 0.0F;
                    for (char c : stringToCharArray) {
                        this.renderer.drawString(String.valueOf(c), posX.getValue() + f, posY.getValue(), ColorUtil.rainbow(arrayOfInt[0] * (ClickGui.getInstance()).rainbowHue.getValue()).getRGB(), true);
                        f += this.renderer.getStringWidth(String.valueOf(c));
                        arrayOfInt[0] = arrayOfInt[0] + 1;
                    }
                }
            } else {
                this.renderer.drawString(name, posX.getValue(), posY.getValue(), ColorUtil.toRGBA((ClickGui.getInstance()).red.getValue(), (ClickGui.getInstance()).green.getValue(), (ClickGui.getInstance()).blue.getValue()), true);
            }
        } else {
            this.renderer.drawString(name, posX.getValue(), posY.getValue(), namecolor, true);
        }
        this.renderer.drawString(totems + " totem" + totemCounterPlural, posX.getValue(), posY.getValue() + 9, ColorUtil.toRGBA(255, 255, 255), true);
        this.renderer.drawString(sets + " set" + setCounterPlural, posX.getValue(), posY.getValue() + 18, ColorUtil.toRGBA(255, 255, 255), true);
    }
} 
