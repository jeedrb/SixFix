package jeed.sixfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.*;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet102WindowClick;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
//import net.minecraftforge.common.ForgeHooks;
//import net.minecraft.block.Block;

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
            pickEntity(target); // minecarts, boats, paintings, item frames, etc
//            System.out.println("entity!");
            return;
        }

        int targetID = theCraft.theWorld.getBlockId(target.blockX, target.blockY, target.blockZ); // get the main ID
        int targetMeta = theCraft.theWorld.getBlockMetadata(target.blockX, target.blockY, target.blockZ); // get the metadata, for things like wood or wool variants
        System.out.println("looking ID is " + targetID + " : " + targetMeta);

        if (inventory.getCurrentItem() != null) {
            System.out.println("holding ID is " + inventory.getCurrentItem().itemID + " : " + inventory.getCurrentItem().getItemDamage());
        }

        hotbarSearch(targetID, targetMeta);

    }

    private static void pickEntity(MovingObjectPosition target) {
        int targetID = 0; // safe default ig

        // can't use switch case for this :/
        if (target.entityHit instanceof EntityLiving) { // living
            if (target.entityHit instanceof EntityWolf) {
                targetID = 352; // bone item
            } else if (target.entityHit instanceof EntityOcelot) {
                targetID = 349; // raw fish item
            }
        } else { // nonliving
            if (target.entityHit instanceof EntityMinecartEmpty) {
                targetID = 328; // minecart item
            } else if (target.entityHit instanceof EntityBoat) {
                targetID = 333; // boat item
            } else if (target.entityHit instanceof EntityMinecartChest) {
                targetID = 342; // minecart chest item
            } else if (target.entityHit instanceof EntityMinecartFurnace) {
                targetID = 343; // minecart furnace item
            } else if (target.entityHit instanceof EntityMinecartTNT) {
                targetID = 407; // minecart tnt item
            } else if (target.entityHit instanceof EntityMinecartHopper) {
                targetID = 408; // minecart hopper item
            } else if (target.entityHit instanceof EntityPainting) {
                targetID = 321; // painting item
            } else if (target.entityHit instanceof EntityItemFrame) {
                targetID = 389; // item frame item
            }
        }

        hotbarSearch(targetID, 0);
    }

    private static void hotbarSearch(int targetID, int targetMeta) {
        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;

        // HANDLE EXCEPTIONS WHERE BLOCK ID DOESN'T MATCH ITEM ID
        // maybe move to a function, it's getting long
        switch (targetID) {
            case 59: // wheat seeds block
                targetID = 295; // wheat seeds item
                targetMeta = 0;
                break;
            case 105: // melon seeds block
                targetID = 362; // melon seeds item
                targetMeta = 0;
                break;
            case 104: // pumpkin seeds block
                targetID = 361; // pumpkin seeds item
                targetMeta = 0;
                break;
            case 83: // sugar cane block
                targetID = 338; // sugar cane item
                targetMeta = 0;
                break;
            case 144: // skull block
                targetID = 397; // skull item, DON'T CLEAR targetMeta since the different skulls use that
                break;
            case 26: // bed block
                targetID = 355; // bed item
                targetMeta = 0;
                break;
            case 68: // wall sign block
            case 63: // floor sign block
                targetID = 323; // sign item
                targetMeta = 0;
                break;
            case 6: // saplings, block and item ID are the same, but you have to modulo 4 the metadata because it grows in an insane way
            case 17: // same for logs
            case 18: // and same for leaves
                targetMeta %= 4; // the item ID for obtainable saplings/logs/leaves will always be 0-3, but grown/natural intermingle 0-11
                break;
            case 140: // flower pot block
                targetID = 390; // flower pot item
                targetMeta = 0; // remove metadata from inserted flower
                break;
            case 64: // door block
                targetID = 324; // door item
                targetMeta = 0; // remove rotation/orientation/state
                break;
            case 71: // iron door block
                targetID = 330; // iron door item
                targetMeta = 0; // remove rotation/orientation/state
                break;
            case 75: // unlit redstone torch block
                targetID = 76; // redstone torch item
                targetMeta = 0;
                break;
            case 33: // sticky piston block/item
            case 29: // piston block/item
            case 120: // end portal frame block/item
            case 50: // torch block/item
            case 76: // redstone torch block/item
            case 66: // rail block/item
            case 27: // powered rail block/item
            case 28: // detector rail block/item
            case 157: // activator rail block/item
            case 96: // trapdoor block/item
            case 53: // oak stairs block/item
            case 67: // cobblestone stairs block/item
            case 108: // brick stairs block/item
            case 109: // stone brick stairs block/item
            case 114: // nether brick stairs block/item
            case 128: // sandstone stairs block/item
            case 134: // spruce stairs block/item
            case 135: // birch stairs block/item
            case 136: // jungle stairs block/item
            case 156: // quartz stairs block/item
            case 107: // fence gate block
            case 143: // wooden button block/item
            case 77: // stone button block/item
            case 70: // stone pressure plate block/item
            case 72: // wooden pressure plate block/item
            case 147: // gold pressure plate block/item
            case 148: // iron pressure plate block/item
            case 69: // lever block/item
            case 131: // tripwire hook block/item
            case 151: // daylight detector block/item
            case 154: // hopper block/item
            case 23: // dispenser block/item
            case 158: // dropper block/item
                targetMeta = 0;
                break;
            case 55: // redstone dust block
                targetID = 331; // redstone dust item
                targetMeta = 0; // remove power
                break;
            case 124: // lit redstone lamp block/item
                targetID = 123; // unlit redstone lamp block/item
                break;
            case 34: // piston extension block
                if (targetMeta <= 5) {
                    targetID = 33; // piston block/item
                } else {
                    targetID = 29; // sticky piston block/item
                }
                targetMeta = 0; // remove rotation
                break;
            case 93: // unlit repeater block
            case 94: // lit repeater block
                targetID = 356; // repeater item
                targetMeta = 0; // remove rotation/state
                break;
            case 149: // comparator block
                targetID = 404; // comparator item
                targetMeta = 0; // remove rotation/state
                break;
            case 132: // string block
                targetID = 287; // string item
                targetMeta = 0; // remove state
                break;
            case 65: // ladder block/item
            case 78: // snow (layer) block/item
            case 106: // vine block/item
            case 54: // chest block/item
            case 146: // trapped chest block/item
            case 130: // ender chest
                targetMeta = 0;
                break;
            case 61: // furnace block/item
            case 62: // lit furnace block
                targetID = 61; // change to unlit furnace
                targetMeta = 0; // rotation
                break;
            case 145: // anvil block/item
                targetMeta = -1; // -1 is for ignoring metadata in search
                break;
            case 44: // slabs
            case 126: // wood slabs
                targetMeta %= 8; // does a similar thing to saplings/logs/leaves
                break;
            case 43: // double slabs
            case 125: // double wood slabs
                targetID += 1; // turn to single slabs
                break;

        }



        if (inventory.getCurrentItem() != null) { // simple check for if you're already holding what you need
            if (inventory.getCurrentItem().itemID == targetID && (inventory.getCurrentItem().getItemDamage() == targetMeta || targetMeta == -1)) {
//                System.out.println("have you tried looking down");
                return;
            }
        }

        // searching hotbar for items
        for (int i = 0; i < 9; i++) {
            if (inventory.getStackInSlot(i) == null) continue; // skip if hotbar slot empty

            if (inventory.getStackInSlot(i).itemID == targetID && (inventory.getStackInSlot(i).getItemDamage() == targetMeta || targetMeta == -1)) {
//                System.out.println("Item " + targetID + " found in slot " + i);
                inventory.currentItem = i; // change to the correct item slot if item found in hotbar
                return;
            }
        }

//        System.out.println("not found in hotbar, checking inventory now");
        inventorySearch(targetID, targetMeta);

    }

    private static void inventorySearch(int targetID, int targetMeta) {
        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;

        for (int i = 9; i < 36; i++) {
            if (inventory.getStackInSlot(i) == null) continue; // skip if inv slot empty

            if (inventory.getStackInSlot(i).itemID == targetID && (inventory.getStackInSlot(i).getItemDamage() == targetMeta || targetMeta == -1)) {
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

        // this kinda sucks I need to change it, doesn't correctly swap two occupied slots
        // wait apparently that's vanilla behavior with 1-9 binds in a gui??? whatever man
        Packet102WindowClick swapPacket = new Packet102WindowClick(0, targetSlot, currentSlot, 2, buffer, (short) 0);
        inventory.currentItem = currentSlot;
        theCraft.getNetHandler().addToSendQueue(swapPacket);
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
