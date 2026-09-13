package jeed.sixfix;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.util.logging.Logger;

@Mod(modid = "sixfix", name = "SixFix", version = "0.4.0")
public class SixFix {
    public static final Logger LOGGER = Logger.getLogger("sixfix");

    @SidedProxy(clientSide = "jeed.sixfix.SixFixClientProxy", serverSide = "jeed.sixfix.SixFixCommonProxy")
    public static SixFixCommonProxy proxy;

    public SixFix() {
        LOGGER.setParent(FMLLog.getLogger());
        LOGGER.info("Hello, constructor!");
    }

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello, preinit!");
        proxy.hi(event);


    }
}