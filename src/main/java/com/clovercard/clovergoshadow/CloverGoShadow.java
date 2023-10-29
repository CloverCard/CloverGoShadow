package com.clovercard.clovergoshadow;

import com.clovercard.clovergoshadow.commands.*;
import com.clovercard.clovergoshadow.config.Config;
import com.clovercard.clovergoshadow.listeners.BattleStartListener;
import com.clovercard.clovergoshadow.listeners.*;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CloverGoShadow.MOD_ID)
@Mod.EventBusSubscriber(modid = CloverGoShadow.MOD_ID)
public class CloverGoShadow {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "clovergoshadow";

    public CloverGoShadow() {
        MinecraftForge.EVENT_BUS.register(this);
        //Register listeners
        LOGGER.debug("REGISTERING CLOVERGOSHADOW LISTENERS");
        Pixelmon.EVENT_BUS.register(new DetectShadowPokemon());
        Pixelmon.EVENT_BUS.register(new SpawnShadowPokemon());
        Pixelmon.EVENT_BUS.register(new ExperienceGain());
        Pixelmon.EVENT_BUS.register(new EVGain());
        Pixelmon.EVENT_BUS.register(new RibbonEquipped());
        Pixelmon.EVENT_BUS.register(new SendOutPokemon());
        Pixelmon.EVENT_BUS.register(new BeatShadowTrainer());
        Pixelmon.EVENT_BUS.register(new WishPieceInteraction());
        Pixelmon.EVENT_BUS.register(new RaidDenStart());
        Pixelmon.EVENT_BUS.register(new CaptureListeners());
        Pixelmon.EVENT_BUS.register(new BeatShadowPokemon());
        Pixelmon.EVENT_BUS.register(new BattleStartListener());
    }

    @SubscribeEvent
    public static void onCommand(RegisterCommandsEvent event) {
        //Register commands
        LOGGER.debug("REGISTERING CLOVERGOSHADOW COMMANDS");
        new Purify(event.getDispatcher());
        new ShowStats(event.getDispatcher());
        new SpawnShadow(event.getDispatcher());
        new GiveShadow(event.getDispatcher());
        new Reload(event.getDispatcher());
        new ModifyStats(event.getDispatcher());

        LOGGER.debug("SETTING UP CLOVERGOSHADOW CONFIG");
        Config.load();

        LOGGER.debug("DONE!");
    }
}
