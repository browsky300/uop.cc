package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.BlockUtil;
import me.alpha432.oyvey.util.RenderUtil;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.awt.*;

public class HoleESP
        extends Module {
    private final Setting<Integer> range = this.register(new Setting<Integer>("Range", 10, 0, 25));
    private final Setting<Float> height = this.register(new Setting<Float>("Height", Float.valueOf(0.0f), Float.valueOf(-1.0f), Float.valueOf(1.0f)));
    private final Setting<Integer> alpha = this.register(new Setting<Integer>("Alpha", 128, 0, 255));
    private final Setting<Integer> lineAlpha = this.register(new Setting<Integer>("LineAlpha", 255, 0, 255));
    private final Setting<Float> lineWidth = this.register(new Setting<Float>("LineWidth", Float.valueOf(1.2f), Float.valueOf(0.1f), Float.valueOf(5.0f)));
    public Setting<Boolean> crossed = this.register(new Setting<Boolean>("Crossed", true));
    private final Setting<Integer> Bred = this.register(new Setting<Integer>("BedrockRed", 0, 0, 255));
    private final Setting<Integer> Bgreen = this.register(new Setting<Integer>("BedrockGreen", 255, 0, 255));
    private final Setting<Integer> Bblue = this.register(new Setting<Integer>("BedrockBlue", 0, 0, 255));
    private final Setting<Integer> Ored = this.register(new Setting<Integer>("ObbyRed", 255, 0, 255));
    private final Setting<Integer> Ogreen = this.register(new Setting<Integer>("ObbyGreen", 0, 0, 255));
    private final Setting<Integer> Oblue = this.register(new Setting<Integer>("ObbyBlue", 0, 0, 255));
    public int count = 0;
    
    public HoleESP() {
        super("HoleESP", "browsky hole esp attempt :D", Module.Category.RENDER, false, false, false);
    }
    
    @Override
    public void onRender3D(Render3DEvent event) {
        count = 0;
        assert (mc.renderViewEntity != null);
        Vec3i playerPos = new Vec3i(mc.renderViewEntity.posX, mc.renderViewEntity.posY, mc.renderViewEntity.posZ);
        for (int x = playerPos.getX() - this.range.getValue(); x < playerPos.getX() + this.range.getValue(); ++x) {
            for (int z = playerPos.getZ() - this.range.getValue(); z < playerPos.getZ() + this.range.getValue(); ++z) {
                for (int y = playerPos.getY() + this.range.getValue(); y > playerPos.getY() - this.range.getValue(); --y) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (!mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR) || !mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) || !mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR))
                        continue;
                    if (mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK && mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK && mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK && mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK) {
                        drawHole(true, pos);
                        continue;
                    }
                    if (!BlockUtil.isBlockUnSafe(mc.world.getBlockState(pos.down()).getBlock()) || !BlockUtil.isBlockUnSafe(mc.world.getBlockState(pos.east()).getBlock()) || !BlockUtil.isBlockUnSafe(mc.world.getBlockState(pos.west()).getBlock()) || !BlockUtil.isBlockUnSafe(mc.world.getBlockState(pos.south()).getBlock()) || !BlockUtil.isBlockUnSafe(mc.world.getBlockState(pos.north()).getBlock()))
                        continue;
                    drawHole(false, pos);
                }
            }
        }
    }
    
    public void drawHole(boolean isHoleSafe, BlockPos pos) {
        count++;
        double x1 = pos.getX();
        double y1 = pos.getY();
        double z1 = pos.getZ();
        double x2 = x1 + 1;
        double y2 = y1 + height.getValue();
        double z2 = z1 + 1;
        
        float red = Ored.getValue() / 255.0f;
        float green = Ogreen.getValue() / 255.0f;
        float blue = Oblue.getValue() / 255.0f;
        float alpha2 = alpha.getValue() / 255.0f; // quality variable naming
        float lineAlpha2 = lineAlpha.getValue() / 255.0f;
        
        if (isHoleSafe) {
            red = Bred.getValue() / 255.0f;
            green = Bgreen.getValue() / 255.0f;
            blue = Bblue.getValue() / 255.0f;
        }
        
        /*if (grow.getValue() && mc.player.getDistance(x1 + 0.5, y1, z1 + 0.5) > range.getValue() - 1) {
            double shrinkAmount = range.getValue() - mc.player.getDistance(x1, y1, z1);
            if (shrinkAmount < 0) shrinkAmount = 0;
            if (shrinkAmount > 1) shrinkAmount = 1;
            x1 -= shrinkAmount / 2;
            //y1 += shrinkAmount * height.getValue();
            z1 -= shrinkAmount / 2;
            x2 += shrinkAmount / 2;
            //y2 -= shrinkAmount * height.getValue();
            z2 += shrinkAmount / 2;
        }*/ // we will not touch this
            
        
        AxisAlignedBB bb = new AxisAlignedBB(x1 - mc.getRenderManager().viewerPosX, y1 - mc.getRenderManager().viewerPosY, z1 - mc.getRenderManager().viewerPosZ, x2 - mc.getRenderManager().viewerPosX, y2 - mc.getRenderManager().viewerPosY, z2 - mc.getRenderManager().viewerPosZ);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(lineWidth.getValue());
        RenderGlobal.renderFilledBox(bb, red, green, blue, alpha2);
        
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
        
        if (crossed.getValue()) { // i know this is more than you actually need but its 3 am and i cant be asked to figure out what the minimum amount of lines is
            bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            
            bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            
            bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            
            bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            
            bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, lineAlpha2).endVertex();
            
            bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, lineAlpha2).endVertex();
            
        }
        tessellator.draw();
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(count);
    }
}

