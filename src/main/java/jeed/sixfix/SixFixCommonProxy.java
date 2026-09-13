package jeed.sixfix;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class SixFixCommonProxy {
    public void hi(FMLPreInitializationEvent event) {
        SixFix.LOGGER.info("Hello from CommonProxy");
    }
}