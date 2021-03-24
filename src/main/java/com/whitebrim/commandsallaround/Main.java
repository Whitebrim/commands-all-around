package com.whitebrim.commandsallaround;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.Int;
import uk.co.mysterymayhem.gravitymod.api.API;
import uk.co.mysterymayhem.gravitymod.api.EnumGravityDirection;
import uk.co.mysterymayhem.gravitymod.common.capabilities.gravitydirection.GravityDirectionCapability;

import java.awt.event.ActionListener;

@Mod(
        modid = Main.MOD_ID,
        name = Main.MOD_NAME,
        version = Main.VERSION,
        dependencies = "required:mysttmtgravitymod;"
)
public class Main {

    public static final String MOD_ID = "commandsallaround";
    public static final String MOD_NAME = "Commandsallaround";
    public static final String VERSION = "1.4";

    @Mod.Instance(MOD_ID)
    public static Main INSTANCE;


    @Mod.EventHandler
    public void init(FMLServerStartingEvent event) {
        event.registerServerCommand(new GravityCommand());
        event.registerServerCommand(new AutoRotateOnLoginCommand());
        event.registerServerCommand(new AutoRotateOnRespawnCommand());
        event.registerServerCommand(new AutoRotateDirectionCommand());
    }

    @Mod.EventHandler
    public void onInitialization(FMLInitializationEvent event) {
        if(!ConfigHandler.hasCategory("Auto rotate")){
            ConfigHandler.writeConfig("Auto rotate", "rotate on login", true);
            ConfigHandler.writeConfig("Auto rotate", "rotate on respawn", true);
            ConfigHandler.writeConfig("Auto rotate", "rotate direction", "up");
        }
    }
}
