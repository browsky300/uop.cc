package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.command.Command;

public class DirectionModifier extends Module {
    
    
    enum YawMode {Off, Spin, N, NE, E, SE, S, SW, W, NW};
    enum PitchMode {Off, Doom, Goldeneye};
    Setting<YawMode> yawmode = register(new Setting("Yaw", YawMode.Off));
    Setting<PitchMode> pitchmode = register(new Setting("Pitch", PitchMode.Off));

    public DirectionModifier() {
        super("DirectionModifier", "changes yaw and pitch", Module.Category.PLAYER, false, false, false);
    }
    
    public void onUpdate() {
        switch (yawmode.getValue()) {
            case N: {
                mc.player.rotationYaw = 180.0f;
                break;
            }
            case NE: {
                mc.player.rotationYaw = -135.0f;
                break;
            }
            case E: {
                mc.player.rotationYaw = -90.0f;
                break;
            }
            case SE: {
                mc.player.rotationYaw = -45.0f;
                break;
            }
            case S: {
                mc.player.rotationYaw = 0.0f;
                break;
            }
            case SW: {
                mc.player.rotationYaw = 45.0f;
                break;
            }
            case W: {
                mc.player.rotationYaw = 90.0f;
                break;
            }
            case NW: {
                mc.player.rotationYaw = 135.0f;
                break;
            }
            default: {
                break;
            }
        }
        switch (pitchmode.getValue()) {
            case Off: {
                break;
            }
            case Doom: {
                mc.player.rotationPitch = 0.0f;
                break;
            }
            case Goldeneye: {
                mc.player.rotationPitch = 60.0f;
                break;
            }
        }
    }
    
    public void onEnable() {
        switch (pitchmode.getValue()) {
            case Doom: {
                Command.sendMessage("Knee-Deep in the Safe Hole");
                break;
            }
            case Goldeneye: {
                Command.sendMessage("EVERY FRAME COUNTS");
                break;
            }
            default: {
                break;
            }
        }
    }
    
    public void onTick() {
        if (yawmode.getValue() == YawMode.Spin) {
            mc.player.rotationYaw += 10.0f;
        }
    }
}

