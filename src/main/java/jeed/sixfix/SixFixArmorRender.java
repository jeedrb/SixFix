package jeed.sixfix;

import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;



public class SixFixArmorRender {

   @ForgeSubscribe
    public void onArmorRender(RenderPlayerEvent.SetArmorModel event) {
//       System.out.println("hi");
       if (SixFixConfig.hideArmorToggle) {
           event.result = -2; // -1 didn't work but i guess this does??? i set it to -67 in frustration initially and that also worked
       }
   }
}
