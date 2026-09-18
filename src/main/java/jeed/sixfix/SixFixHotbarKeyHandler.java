package jeed.sixfix;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import java.util.EnumSet;

public class SixFixHotbarKeyHandler extends KeyHandler {

    public SixFixHotbarKeyHandler() {
        super(SixFixClientProxy.hotbarBinds, new boolean[9]);
    }

    @Override
    public String getLabel() {
        return "SixFixHotbarKeyHandler";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding pressed, boolean tickEnd, boolean isRepeat) {
        Minecraft theCraft = Minecraft.getMinecraft();

        // only run when possible and once per game bla bla bla
        if (tickEnd && theCraft.thePlayer != null && theCraft.currentScreen == null) {

            // not in a menu
            if (theCraft.currentScreen == null) {
                for (int i = 0; i < 9; i++) {
                    if (pressed == SixFixClientProxy.hotbarBinds[i]) {
                        theCraft.thePlayer.inventory.currentItem = i;
//                        theCraft.playerController.syncCurrentPlayItem();
                        break;
                    }
                }
            }
        }
    }


    @Override public void keyUp(EnumSet<TickType> t, KeyBinding k, boolean te) {

    }

    @Override public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }
}