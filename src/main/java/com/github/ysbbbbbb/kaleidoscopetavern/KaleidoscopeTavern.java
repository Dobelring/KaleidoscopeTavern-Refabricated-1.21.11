package com.github.ysbbbbbb.kaleidoscopetavern;

import com.github.ysbbbbbb.kaleidoscopetavern.config.GeneralConfig;
import com.github.ysbbbbbb.kaleidoscopetavern.init.*;
import com.github.ysbbbbbb.kaleidoscopetavern.init.registery.CommonRegistry;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;

import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KaleidoscopeTavern implements ModInitializer {
	public static final String MOD_ID = "kaleidoscope_tavern";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ForgeConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.COMMON, GeneralConfig.init());
		CommonRegistry.init();
		ModEffects.registerEffects();
		ModItems.registerItems();
		ModFluids.registerFluids();
		ModBlocks.registerBlocks();
		ModRecipes.registerRecipes();
		ModEntities.registerEntities();
		ModCreativeTabs.registerTabs();
		ModParticles.registerParticles();
		ModSounds.registerSounds();
	}
}
