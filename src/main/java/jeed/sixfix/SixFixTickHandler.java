package jeed.sixfix;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import org.lwjgl.input.Keyboard;

import java.util.EnumSet;

public class SixFixTickHandler implements ITickHandler {
    private final boolean[] isDown = new boolean[9];

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        // nothing ig
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        Minecraft theCraft = Minecraft.getMinecraft();

        // check if an inventory/container gui is open
        if (theCraft.thePlayer != null && theCraft.currentScreen instanceof GuiContainer) {
            GuiContainer currentGui = (GuiContainer) theCraft.currentScreen;

            for (int i = 0; i < 9; i++) {
                int keyCode = SixFixClientProxy.hotbarBinds[i].keyCode; // get the custom bound keycode

                if (Keyboard.isKeyDown(keyCode)) {
                    if (!isDown[i]) {
                        if (!(keyCode >= 2 && keyCode <= 10))
                            invSwap(currentGui, i);

                        isDown[i] = true;
                    }
                } else {
                    isDown[i] = false;
                }
            }
        }
    }

    private void invSwap(GuiContainer gui, int slot) { // simulating pressing a 1-9 key in a container with the custom keys
        Minecraft theCraft = Minecraft.getMinecraft();
        try {
            net.minecraft.inventory.Slot hoverSlot = null;
            net.minecraft.inventory.Container container = null;

            for (java.lang.reflect.Field field : GuiContainer.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (hoverSlot == null && field.getType() == net.minecraft.inventory.Slot.class) {
                    hoverSlot = (net.minecraft.inventory.Slot) field.get(gui);
                }
                if (container == null && field.getType() == net.minecraft.inventory.Container.class) {
                    container = (net.minecraft.inventory.Container) field.get(gui);
                }
                if (hoverSlot != null && container != null) break;
            }

            if (hoverSlot != null && container != null) {
                theCraft.playerController.windowClick(container.windowId, hoverSlot.slotNumber, slot, 2, theCraft.thePlayer);
//                theCraft.thePlayer.inventory.onInventoryChanged();
            }
        } catch (Exception ignored) {}
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {
        return "SixFixTick";
    }

}
