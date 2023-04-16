package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.client.ClickGui;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.util.ColorUtil;
import me.alpha432.oyvey.OyVey;
import com.mojang.realmsclient.gui.ChatFormatting;

public class ConnectionInfo extends Module {
    public enum RenderMode {Up, Down}
    public Setting<RenderMode> rendermode = register(new Setting("Render", RenderMode.Up));

    public ConnectionInfo() {
        super("ConnectionInfo", "connection info", Category.HUD, true, false, false);
    }

    public void onRender2D(Render2DEvent event) {
        int color = (ClickGui.getInstance().rainbow.getValue() ? ColorUtil.toRGBA(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue())) : ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue()));
        String msString = ChatFormatting.WHITE + "" + OyVey.serverManager.getPing() + ChatFormatting.RESET + " ms";
        String tpsString = ChatFormatting.WHITE + "" + (int) Math.round(OyVey.serverManager.getTPS()) + ChatFormatting.RESET + " tps";
        String lagString = (OyVey.serverManager.isLagging() ? (ChatFormatting.WHITE + "" + Math.floor(OyVey.serverManager.serverRespondingTime() / 100.0f) / 10.0f + "s" +  ChatFormatting.RESET + " lag") : "");
        this.renderer.drawString(msString, this.renderer.scaledWidth - 2 - this.renderer.getStringWidth(msString), (rendermode.getValue() == RenderMode.Up ? 2 : this.renderer.scaledHeight - 10), color, true);
        this.renderer.drawString(tpsString, this.renderer.scaledWidth - 2 - this.renderer.getStringWidth(tpsString), (rendermode.getValue() == RenderMode.Up ? 11 : this.renderer.scaledHeight - 19), color, true);
        this.renderer.drawString(lagString, this.renderer.scaledWidth - 2 - this.renderer.getStringWidth(lagString), (rendermode.getValue() == RenderMode.Up ? 20 : this.renderer.scaledHeight - 28), color, true);
    }
} 
