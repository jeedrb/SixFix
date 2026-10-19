package jeed.sixfix;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class SixFixConfig {
    public static Configuration config;
    public static boolean hideArmorToggle;

//    @ForgeSubscribe
    public static void readConfig(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile()); // read the config file or make a new one
        config.load();

        hideArmorToggle = config.get("Armor", "Hide Player Armor", true).getBoolean(true);

        config.save();
//        System.out.println("Loaded: " + hideArmorToggle);
    }

    public static void toggleArmor() {
        hideArmorToggle = !hideArmorToggle;
        // sometimes when going through portals or respawning, the cape will start showing even with the option off, so maybe this will be a way to refresh it
        Minecraft.getMinecraft().gameSettings.showCape = !Minecraft.getMinecraft().gameSettings.showCape;
        Minecraft.getMinecraft().gameSettings.saveOptions();
        Minecraft.getMinecraft().gameSettings.showCape = !Minecraft.getMinecraft().gameSettings.showCape;
        Minecraft.getMinecraft().gameSettings.saveOptions();

        Property prop = config.get("Armor", "Hide Player Armor", true);
        prop.set(hideArmorToggle);

        config.save();
//        System.out.println("Toggled: " + hideArmorToggle);
    }




}
