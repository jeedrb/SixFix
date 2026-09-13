package jeed.sixfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemSaddle;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;



public class SixFixArmorRender {

   @ForgeSubscribe
    public void onArmorRender(RenderPlayerEvent.SetArmorModel event) {
//       System.out.println("hi");
       if (SixFixConfig.hideArmorToggle) {
           event.result = -2; // -1 didn't work but i guess this does???
       }
   }
}
