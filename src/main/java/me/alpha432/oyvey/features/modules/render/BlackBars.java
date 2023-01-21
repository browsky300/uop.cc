package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.util.RenderUtil;

public class BlackBars extends Module {

    public BlackBars() {
        super("BlackBars", "draws black bars", Category.RENDER, true, false, false);
    }

    public void onRender2D(Render2DEvent event) {
        if (fullNullCheck()) return;
        float width = this.renderer.scaledWidth;
        float height = this.renderer.scaledHeight;
        float barWidth = width / 8;
        RenderUtil.drawRect(0, 0, barWidth, height + 1, 4278190080L);
        RenderUtil.drawRect(width - barWidth, 0, width, height + 1, 4278190080L);
        //RenderUtil.drawCircle(1000, 100, 20); // how to obliterate your game in one line of code simple tutorial 2023
    }
} 
