package com.whitebrim.commandsallaround;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutoRotateDirectionCommand extends CommandBase {

    public static List<String> validator = Arrays.asList("down", "east", "north", "south", "up", "west");

    public static EnumGravityDirection GetEnum(String key){
        switch (key){
            case "up":
                return EnumGravityDirection.UP;
            case "down":
                return EnumGravityDirection.DOWN;
            case "north":
                return EnumGravityDirection.NORTH;
            case "south":
                return EnumGravityDirection.SOUTH;
            case "west":
                return EnumGravityDirection.WEST;
            case "east":
                return EnumGravityDirection.EAST;
        }
        return EnumGravityDirection.DOWN;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] params) throws CommandException {
        if(params.length > 0){
            String value = params[0].toLowerCase().trim();
            if(!validator.contains(value)){
                throw new CommandException("commands.autorotatedirection.invalid", new Object[] {value});
            }
            ConfigHandler.writeConfig("Auto rotate", "rotate direction", value);
            sender.sendMessage(new TextComponentTranslation("commands.commandsallaround.success"));
        }else{
            sender.sendMessage(new TextComponentString(ConfigHandler.getString("Auto rotate", "rotate direction")));
        }
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer p_getTabCompletions_1_, ICommandSender p_getTabCompletions_2_, String[] p_getTabCompletions_3_, @Nullable BlockPos p_getTabCompletions_4_) {
        if (p_getTabCompletions_3_.length == 1){
            return validator;
        }
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return "autoRotateDirection";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.autorotatedirection.usage";
    }
}
