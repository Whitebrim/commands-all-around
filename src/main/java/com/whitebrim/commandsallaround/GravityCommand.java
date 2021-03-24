package com.whitebrim.commandsallaround;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import scala.Int;
import uk.co.mysterymayhem.gravitymod.api.API;
import uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection;
import uk.co.mysterymayhem.gravitymod.common.capabilities.gravitydirection.GravityDirectionCapability;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class GravityCommand extends CommandBase {

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] params) throws CommandException {
        EntityPlayerMP player;
        int timeout = Int.MaxValue();
        EnumGravityDirection direction = EnumGravityDirection.UP;
        if(params.length > 0){
            String value = params[0].toLowerCase().trim();
            if(!AutoRotateDirectionCommand.validator.contains(value)){
                throw new CommandException("commands.sv_gravity.invalid");
            }
            direction = AutoRotateDirectionCommand.GetEnum(value);
        }
        if(params.length > 1){
            player = getPlayer(server, sender, params[1]);
        }
        else{
            player = getCommandSenderAsPlayer(sender);
        }
        API.forceSetPlayerGravity(direction, player, false);
        GravityDirectionCapability.getGravityCapability(player).setTimeoutTicks(timeout);
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer p_getTabCompletions_1_, ICommandSender p_getTabCompletions_2_, String[] p_getTabCompletions_3_, @Nullable BlockPos p_getTabCompletions_4_) {
        if (p_getTabCompletions_3_.length == 1){
            return AutoRotateDirectionCommand.validator;
        }
        if (p_getTabCompletions_3_.length == 2){
            return getListOfStringsMatchingLastWord(p_getTabCompletions_3_, p_getTabCompletions_1_.getPlayerList().getOnlinePlayerNames());
        }
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return "sv_gravity";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.sv_gravity.usage";
    }
}
