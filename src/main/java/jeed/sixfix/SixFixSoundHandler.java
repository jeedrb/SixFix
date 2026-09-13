package jeed.sixfix;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class SixFixSoundHandler {
    private static final String[] soundArray = {
        "", // 0 BLANK
        "dig.wood", // 1 WOOD
        "dig.cloth", // 2 CLOTH/WOOL
        "dig.grass", // 3 GRASS/CROPS/GREEN
        "dig.stone", // 4 STONE/IRON
        "random.bow" // 5 POTION THROW
    };

    @ForgeSubscribe
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.entityPlayer.worldObj.isRemote) return;

        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if (event.useBlock == Event.Result.DENY || event.useItem == Event.Result.DENY) return;


            ItemStack heldStack = event.entityPlayer.getHeldItem();

            if (heldStack != null) {
                Item heldItem = heldStack.getItem();

                // maybe have most of this block after the if block below
                int soundCategory = 0;
                float pitch = 0.8F + (event.entityPlayer.worldObj.rand.nextFloat() * 0.4F);
                double x = (double)event.x + 0.5D;
                double y = (double)event.y + 0.5D;
                double z = (double)event.z + 0.5D;

                if (heldItem == Item.doorWood || heldItem == Item.bed || heldItem == Item.sign || (heldItem == Item.dyePowder && heldStack.getItemDamage() == 3)) {
                    soundCategory = 1;
                } else if (heldItem == Item.seeds || heldItem.itemID == Block.waterlily.blockID || heldItem == Item.carrot || heldItem == Item.potato) {
                    soundCategory = 3;
                } else if (heldItem == Item.doorIron || heldItem == Item.skull || heldItem == Item.netherStalkSeeds) {
                    soundCategory = 4;
                } else {
                    return;
                }

                event.entityPlayer.worldObj.playSoundEffect(x, y, z, soundArray[soundCategory].toString(), 1.0F, pitch);

            } else {
                return;
            }


        }
    }
}
