package com.whitebrim.commandsallaround;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import scala.Int;
import uk.co.mysterymayhem.gravitymod.api.API;
import uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection;
import uk.co.mysterymayhem.gravitymod.common.capabilities.gravitydirection.GravityDirectionCapability;

@Mod.EventBusSubscriber(modid = Main.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event)
    {
        if(ConfigHandler.getBoolean("Auto rotate", "rotate on respawn")){
            API.forceSetPlayerGravity(AutoRotateDirectionCommand.GetEnum(ConfigHandler.getString("Auto rotate", "rotate direction")), (EntityPlayerMP)event.player, false);
            GravityDirectionCapability.getGravityCapability(event.player).setTimeoutTicks(Int.MaxValue());
        }
    }

    @SubscribeEvent
    public static void playerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        if(ConfigHandler.getBoolean("Auto rotate", "rotate on login")) {
            API.forceSetPlayerGravity(AutoRotateDirectionCommand.GetEnum(ConfigHandler.getString("Auto rotate", "rotate direction")), (EntityPlayerMP) event.player, false);
            GravityDirectionCapability.getGravityCapability(event.player).setTimeoutTicks(Int.MaxValue());
        }
    }
}
