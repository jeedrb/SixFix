package jeed.sixfix;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.util.EnumSet;

public class SixFixUtilKeyHandler extends KeyHandler {

    public SixFixUtilKeyHandler(KeyBinding[] utilBinds) { super(utilBinds, new boolean[utilBinds.length]); }

    @Override
    public String getLabel() {
        return "SixFixUtilKeyHandler";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding pressed, boolean tickEnd, boolean isRepeat) {
        Minecraft theCraft = Minecraft.getMinecraft();

        if (tickEnd && theCraft.thePlayer != null && theCraft.currentScreen == null) { // if we can
            if (pressed == SixFixClientProxy.bindF5 && pressed.keyCode != 63) // cycle between three perspective states if original F5 key isn't pressed
                theCraft.gameSettings.thirdPersonView = (theCraft.gameSettings.thirdPersonView + 1) % 3;

            if (pressed == SixFixClientProxy.bindF3 && pressed.keyCode != 61) // toggle debug info if original F3 isn't pressed
                theCraft.gameSettings.showDebugInfo = !theCraft.gameSettings.showDebugInfo;

            if (pressed == SixFixClientProxy.bindArmor) { // run the armor toggle function in the other room
                SixFixConfig.toggleArmor();
            }

            if (pressed == SixFixClientProxy.bindPickBetter) {
                SixFixPickBlock.pickBetter();
//                System.out.println("hi");
            }
        }
    }

    @Override public void keyUp(EnumSet<TickType> t, KeyBinding k, boolean te) {}
    @Override public EnumSet<TickType> ticks() { return EnumSet.of(TickType.CLIENT); }
}