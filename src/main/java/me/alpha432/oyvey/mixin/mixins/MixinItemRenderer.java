package me.alpha432.oyvey.mixin.mixins;

import me.alpha432.oyvey.features.modules.client.ClickGui;
import me.alpha432.oyvey.features.modules.render.HandChams;
import me.alpha432.oyvey.features.modules.render.SmallShield;
import me.alpha432.oyvey.features.modules.render.NoSway;
import me.alpha432.oyvey.features.modules.render.FutureVM;
import me.alpha432.oyvey.features.modules.render.OldAnimations;
import me.alpha432.oyvey.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.init.Items;
import net.minecraft.client.entity.EntityPlayerSP;

@Mixin(value = {ItemRenderer.class})
public abstract class MixinItemRenderer {
    @Shadow
    @Final
    public Minecraft mc;
    private boolean injection = true;

    @Shadow
    public abstract void renderItemInFirstPerson(AbstractClientPlayer var1, float var2, float var3, EnumHand var4, float var5, ItemStack var6, float var7);

    @Shadow
    protected abstract void renderArmFirstPerson(float var1, float var2, EnumHandSide var3);

    @Inject(method = {"renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void renderItemInFirstPersonHook(AbstractClientPlayer player, float p_187457_2_, float p_187457_3_, EnumHand hand, float p_187457_5_, ItemStack stack, float p_187457_7_, CallbackInfo info) {
        if (this.injection) {
            info.cancel();
            if (OldAnimations.getINSTANCE().isOn() && OldAnimations.getINSTANCE().mode.getValue() == OldAnimations.Mode.Full) p_187457_7_ = 0;
            if (OldAnimations.getINSTANCE().isOn() && OldAnimations.getINSTANCE().mode.getValue() == OldAnimations.Mode.Good && p_187457_5_ != 0) {
                if (hand == EnumHand.MAIN_HAND) {
                    mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
                    mc.entityRenderer.itemRenderer.itemStackMainHand = mc.player.getHeldItemMainhand();
                } else {
                    mc.entityRenderer.itemRenderer.equippedProgressOffHand = 1.0f;
                    mc.entityRenderer.itemRenderer.itemStackOffHand = mc.player.getHeldItemOffhand();
                }
            }
            SmallShield offset = SmallShield.getINSTANCE();
            float xOffset = 0.0f;
            float yOffset = 0.0f;
            this.injection = false;
            int vmFactor = 1;
            if (FutureVM.getINSTANCE().isOn()) { // this is really stupid and very dumb but it works and i cba to learn how to properly do gl so ¯\_(ツ)_/¯
                if (hand == EnumHand.MAIN_HAND) {
                    GlStateManager.scale(FutureVM.getINSTANCE().ScaleX.getValue(), FutureVM.getINSTANCE().ScaleY.getValue(), FutureVM.getINSTANCE().ScaleZ.getValue());
                    GlStateManager.rotate(FutureVM.getINSTANCE().RotateX.getValue() + 180.0f, 1.0f, 0.0f, 0.0f);
                    GlStateManager.rotate(FutureVM.getINSTANCE().RotateY.getValue() + 180.0f, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(FutureVM.getINSTANCE().RotateZ.getValue() + 180.0f, 0.0f, 0.0f, 1.0f);
                    GlStateManager.translate(FutureVM.getINSTANCE().TranslateX.getValue() * (1 / FutureVM.getINSTANCE().ScaleX.getValue()) * -1, FutureVM.getINSTANCE().TranslateY.getValue() * (1 / FutureVM.getINSTANCE().ScaleY.getValue()), FutureVM.getINSTANCE().TranslateZ.getValue() * (1 / FutureVM.getINSTANCE().ScaleZ.getValue()));
                } else {
                    GlStateManager.translate(FutureVM.getINSTANCE().TranslateX.getValue() * (1 / FutureVM.getINSTANCE().ScaleX.getValue()) * 2, 0, 0);
                }
            }
            
            if (hand == EnumHand.MAIN_HAND) {
                if (offset.isOn()) {
                    xOffset = offset.mainX.getValue().floatValue();
                    yOffset = offset.mainY.getValue().floatValue();
                }
            } else if (offset.isOn()) {
                xOffset = offset.offX.getValue().floatValue();
                yOffset = offset.offY.getValue().floatValue();
            }
            
            if (HandChams.getINSTANCE().isOn() && hand == EnumHand.MAIN_HAND && stack.isEmpty()) {
                if (HandChams.getINSTANCE().mode.getValue().equals(HandChams.RenderMode.WIREFRAME)) {
                    this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
                }
                GlStateManager.pushMatrix();
                if (HandChams.getINSTANCE().mode.getValue().equals(HandChams.RenderMode.WIREFRAME)) {
                    GL11.glPushAttrib(1048575);
                } else {
                    GlStateManager.pushAttrib();
                }
                if (HandChams.getINSTANCE().mode.getValue().equals(HandChams.RenderMode.WIREFRAME)) {
                    GL11.glPolygonMode(1032, 6913);
                }
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                if (HandChams.getINSTANCE().mode.getValue().equals(HandChams.RenderMode.WIREFRAME)) {
                    GL11.glEnable(2848);
                    GL11.glEnable(3042);
                }
                GL11.glColor4f(ClickGui.getInstance().rainbow.getValue() != false ? (float) ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed() / 255.0f : (float) HandChams.getINSTANCE().red.getValue().intValue() / 255.0f, ClickGui.getInstance().rainbow.getValue() != false ? (float) ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen() / 255.0f : (float) HandChams.getINSTANCE().green.getValue().intValue() / 255.0f, ClickGui.getInstance().rainbow.getValue() != false ? (float) ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue() / 255.0f : (float) HandChams.getINSTANCE().blue.getValue().intValue() / 255.0f, (float) HandChams.getINSTANCE().alpha.getValue().intValue() / 255.0f);
                this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
                GlStateManager.popAttrib();
                GlStateManager.popMatrix();
            }
            if (SmallShield.getINSTANCE().isOn() && (!stack.isEmpty || HandChams.getINSTANCE().isOff())) {
                this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
            } else if (!stack.isEmpty || HandChams.getINSTANCE().isOff()) {
                this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
            }
            this.injection = true;
        }
    }
    
    @Inject(method = {"rotateArm"}, at = { @At("HEAD") }, cancellable = true)
    public void rotateArm(final float p_187458_1_, final CallbackInfo info) {
        if (NoSway.getINSTANCE().isOn()) {
            info.cancel();
        }
    }

    @Redirect(method = "updateEquippedItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;getCooledAttackStrength(F)F"))
    public float getAttackStrengthHook(EntityPlayerSP player, float adjustTicks) {
        if (OldAnimations.getINSTANCE().isOn() && OldAnimations.getINSTANCE().mode.getValue() == OldAnimations.Mode.Good) return 1.0F;
        return player.getCooledAttackStrength(adjustTicks);
    }
}

