package jeed.sixfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class SixFixCommandGui extends CommandBase {


    @Override
    public String getCommandName() {
        return "guiSize";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/guiSize [size]";
    }

    // doesn't work?
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }


    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 1) { // check only one argument was passed
            try { // check if it's an integer
                Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                throw new WrongUsageException("GUI size must be an integer!");
//                return;
            }

            Minecraft theCraft = Minecraft.getMinecraft();
            GameSettings settings = theCraft.gameSettings;

            settings.guiScale = Integer.parseInt(args[0]);

            theCraft.gameSettings.saveOptions();


        } else if (args.length == 0) {
            throw new WrongUsageException("/guiSize [size]");
        } else {
            throw new WrongUsageException("/guiSize [size]");
        }



    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
