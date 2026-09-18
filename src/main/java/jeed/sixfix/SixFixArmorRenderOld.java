package jeed.sixfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.ForgeSubscribe;


// couldn't get this to work lol idk
// this was me trying the approach from another mod https://github.com/flowerinsnowdh/HideArmour
// because the way it works looked insane

public class SixFixArmorRenderOld {
    private ItemStack[] armorBuffer;

    public boolean hideArmorToggle = true;

    @ForgeSubscribe
    public void PreRenderArmor(RenderLivingEvent.Pre event) {
//        System.out.println("bye armor");

        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;
        ItemStack[] armorEquipped = inventory.armorInventory;

        if (!event.entity.equals(steve)) return;
        if (steve.worldObj.isRemote) return;

        if (hideArmorToggle) {
            for (int i = 0; i < 4; i++) {
//                if (armorEquipped[i] != null) {
                    System.out.print(armorEquipped[i] + "" + armorBuffer);
                    armorBuffer[i] = armorEquipped[i];
                    armorEquipped[i] = null;
//                }
            }


        }

    }

    @ForgeSubscribe
    public void PostRenderArmor(RenderLivingEvent.Post event) {
//        System.out.println("hi armor");

        Minecraft theCraft = Minecraft.getMinecraft();
        EntityPlayerSP steve = theCraft.thePlayer;
        InventoryPlayer inventory = steve.inventory;
        ItemStack[] armorEquipped = inventory.armorInventory;

        if (!event.entity.equals(steve)) return;
        if (steve.worldObj.isRemote) return;
        System.out.println("hi " + steve.worldObj.isRemote);

        if (hideArmorToggle) {
            for (int i = 0; i < 4; i++) {
//                if (armorBuffer[i] != null) {
                    armorEquipped[i] = armorBuffer[i];
                    armorBuffer[i] = null;
//                }
            }


        }

    }
}
