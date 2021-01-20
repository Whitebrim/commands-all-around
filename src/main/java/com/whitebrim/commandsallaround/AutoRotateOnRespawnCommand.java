package com.whitebrim.commandsallaround;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoRotateOnRespawnCommand extends CommandBase {

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] params) throws CommandException {
        if(params.length > 0){
            ConfigHandler.writeConfig("Auto rotate", "rotate on respawn", parseBoolean(params[0]));
            sender.sendMessage(new TextComponentTranslation("commands.commandsallaround.success"));
        }else{
            sender.sendMessage(new TextComponentString(String.valueOf(ConfigHandler.getBoolean("Auto rotate", "rotate on respawn"))));
        }
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer p_getTabCompletions_1_, ICommandSender p_getTabCompletions_2_, String[] p_getTabCompletions_3_, @Nullable BlockPos p_getTabCompletions_4_) {
        if (p_getTabCompletions_3_.length == 1){
            ArrayList<String> list = new ArrayList<String>();
            list.add("true");
            list.add("false");
            return list;
        }
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return "autoRotateOnRespawn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.autorotateonrespawn.usage";
    }
}
