package com.github.ysbbbbbb.kaleidoscopetavern;

import com.github.ysbbbbbb.kaleidoscopetavern.compat.jei.ModJeiPlugin;
import com.github.ysbbbbbb.kaleidoscopetavern.config.GeneralConfig;
import com.github.ysbbbbbb.kaleidoscopetavern.init.*;
import com.github.ysbbbbbb.kaleidoscopetavern.init.registery.CommonRegistry;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KaleidoscopeTavern implements ModInitializer {

    public static final String MOD_ID = "kaleidoscope_tavern";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        ConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.COMMON, GeneralConfig.init());
        CommonRegistry.init();
        ModEffects.registerEffects();
        ModDataComponents.register();
        ModItems.registerItems();
        ModFluids.registerFluids();
        ModBlocks.registerBlocks();
        ModRecipes.registerRecipes();
        ModEntities.init();
        ModCreativeTabs.registerTabs();
        ModParticles.registerParticles();
        ModSounds.registerSounds();

        if (FabricLoader.getInstance().isModLoaded("jei")) {
            ModJeiPlugin.syncRecipes();
        }
    }
}
