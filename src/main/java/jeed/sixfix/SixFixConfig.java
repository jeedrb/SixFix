package jeed.sixfix;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.ForgeSubscribe;

import java.io.File;

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

        Property prop = config.get("Armor", "Hide Player Armor", true);
        prop.set(hideArmorToggle);

        config.save();
//        System.out.println("Toggled: " + hideArmorToggle);
    }




}
