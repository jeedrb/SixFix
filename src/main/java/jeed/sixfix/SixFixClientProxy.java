package jeed.sixfix;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.EnumOptions;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

public class SixFixClientProxy extends SixFixCommonProxy {
    public static final EnumOptions[] target;

    static {
        target = EnumOptions.values();
    }

    public static KeyBinding[] hotbarBinds = new KeyBinding[9]; // hotbar bind array
    public static KeyBinding bindF5, bindF3, bindArmor; // perspective and debug binds

    @Override
    public void hi(FMLPreInitializationEvent event) {
        // from the template
        SixFix.LOGGER.info("Hello from ClientProxy");
        SixFix.LOGGER.info("Minecraft's class name is: " + Minecraft.class.getName());
        SixFix.LOGGER.info("Minecraft mcDataDir: " + Minecraft.getMinecraft().mcDataDir);


        for (int i = 0; i < 9; i++) // loop to set default hotbar binds
            hotbarBinds[i] = new KeyBinding("Hotbar Slot " + (i + 1), (i + 2));

        // register hotbar bind stuff and use the classes and whatever
        KeyBindingRegistry.instance().registerKeyBinding(new SixFixHotbarKeyHandler());
        TickRegistry.registerTickHandler(new SixFixTickHandler(), Side.CLIENT);

        // set default F5, F3, armor toggle binds
        bindF5 = new KeyBinding("Perspective", 63); // F5
        bindF3 = new KeyBinding("Debug Overlay", 61); // F3
        bindArmor = new KeyBinding("Armor Toggle", 62); // F4

        KeyBinding[] utilBinds = {bindF5, bindF3, bindArmor};
        KeyBindingRegistry.instance().registerKeyBinding(new SixFixUtilKeyHandler(utilBinds));

        MinecraftForge.EVENT_BUS.register(new SixFixSoundHandler());
        MinecraftForge.EVENT_BUS.register(new SixFixArmorRender());

        SixFixConfig.readConfig(event);


    }
}