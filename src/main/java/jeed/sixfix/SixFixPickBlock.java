package jeed.sixfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet102WindowClick;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.ForgeHooks;
import net.minecraft.block.Block;

// doing my best to learn from https://github.com/MattCzyr/PickBlockPlus/blob/master/src/main/java/com/chaosthedude/pickblockplus/ClientTickHandler.java

public class SixFixPickBlock {

    public static void pickBetter() {
        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;

        if (theCraft.currentScreen != null) {
//            System.out.println("In gui, can't pick block!");
            return;
        }

        MovingObjectPosition target = theCraft.objectMouseOver;

        if (target == null) {
//            System.out.println("swing and a miss");
            return;
        }

//        System.out.println(target.blockX + " " + target.blockY + " " + target.blockZ);
//        ForgeHooks.onPickBlock(target, steve, theCraft.theWorld);

        if (target.typeOfHit == EnumMovingObjectType.ENTITY) {
//            System.out.println("entity!");
            return;
        }

        int targetID = theCraft.theWorld.getBlockId(target.blockX, target.blockY, target.blockZ);
//        System.out.println("block ID is " + targetID);

//        if (inventory.getCurrentItem() != null) {
//            System.out.println("holding ID is " + inventory.getCurrentItem().itemID);
//        }

        hotbarSearch(targetID);

    }

    private static void hotbarSearch(int targetID) {
        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;


        if (inventory.getCurrentItem() != null) { // simple check for if you're already holding what you need
            if (inventory.getCurrentItem().itemID == targetID) {
//                System.out.println("have you tried looking down");
                return;
            }
        }

        // searching hotbar for item
        for (int i = 0; i < 9; i++) {
            if (inventory.getStackInSlot(i) == null) continue; // skip if hotbar slot empty

            if (inventory.getStackInSlot(i).itemID == targetID) {
//                System.out.println("Item " + targetID + " found in slot " + i);
                inventory.currentItem = i; // change to the correct item slot if item found in hotbar
                return;
            }
        }

//        System.out.println("not found in hotbar, checking inventory now");
        inventorySearch(targetID);

    }

    private static void inventorySearch(int targetID) {
        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;

        for (int i = 9; i < 36; i++) {
            if (inventory.getStackInSlot(i) == null) continue; // skip if inv slot empty

            if (inventory.getStackInSlot(i).itemID == targetID) {
//                System.out.println("Item " + targetID + " found in slot " + i);
                itemSwap(getSwappingSlot(), i);
                return;
            }
        }
    }

    private static void itemSwap(int currentSlot, int targetSlot) {
        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;

        // typical z = x, x = y, y = z swap sequence
        ItemStack buffer = inventory.getStackInSlot(targetSlot);
//        inventory.setInventorySlotContents();
//        theCraft.playerController.sendSlotPacket();

//        System.out.println("swapping!");

        Packet102WindowClick swapPacket = new Packet102WindowClick(0, targetSlot, currentSlot, 2, buffer, (short) 0);
        theCraft.getNetHandler().addToSendQueue(swapPacket);
        inventory.currentItem = currentSlot;
    }

    private static int getSwappingSlot() {
        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;

        // return current slot if empty
        if (inventory.getStackInSlot(inventory.currentItem) == null) {
            return inventory.currentItem;
        }

        // looking for first empty slot and returning
        for (int i = 0; i < 9; i++) {
            if (inventory.getStackInSlot(i) == null) {
//                System.out.println("slot " + i + " is empty!");
                return i;
            }
        }


        // if no slots empty, return current slot anyway
        return inventory.currentItem;
    }
}
