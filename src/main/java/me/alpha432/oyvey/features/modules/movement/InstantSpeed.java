package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.event.events.MoveEvent;
import me.alpha432.oyvey.util.EntityUtil;
import net.minecraft.util.MovementInput;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InstantSpeed extends Module {
    public final Setting<Boolean> noBedrock = this.register(new Setting<Boolean>("NoBedrock", false));

    public InstantSpeed() {
        super("InstantSpeed", "Makes you faster", Module.Category.MOVEMENT, true, false, false);
    }

    @SubscribeEvent
    public void onMoveEvent(MoveEvent event) {
        if (mc.player.posY < 7 && noBedrock.getValue()) return;
        if (!(event.getStage() != 0 || mc.player.isSneaking() || mc.player.isInWater() || mc.player.isInLava() || mc.player.isElytraFlying() || mc.player.movementInput.moveForward == 0.0f && mc.player.movementInput.moveStrafe == 0.0f)) {
            MovementInput movementInput = mc.player.movementInput;
            float moveForward = movementInput.moveForward;
            float moveStrafe = movementInput.moveStrafe;
            float rotationYaw = mc.player.rotationYaw;
            if ((double) moveForward == 0.0 && (double) moveStrafe == 0.0) {
                event.setX(0.0);
                event.setZ(0.0);
            } else {
                if ((double) moveForward != 0.0) {
                    if ((double) moveStrafe > 0.0) {
                        rotationYaw += (float) ((double) moveForward > 0.0 ? -45 : 45);
                    } else if ((double) moveStrafe < 0.0) {
                        rotationYaw += (float) ((double) moveForward > 0.0 ? 45 : -45);
                    }
                    moveStrafe = 0.0f;
                    float f = moveForward == 0.0f ? moveForward : (moveForward = (double) moveForward > 0.0 ? 1.0f : -1.0f);
                }
                moveStrafe = moveStrafe == 0.0f ? moveStrafe : ((double) moveStrafe > 0.0 ? 1.0f : -1.0f);
                event.setX((double) moveForward * EntityUtil.getMaxSpeed() * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + (double) moveStrafe * EntityUtil.getMaxSpeed() * Math.sin(Math.toRadians(rotationYaw + 90.0f)));
                event.setZ((double) moveForward * EntityUtil.getMaxSpeed() * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - (double) moveStrafe * EntityUtil.getMaxSpeed() * Math.cos(Math.toRadians(rotationYaw + 90.0f)));
            }
        }
    }
}

