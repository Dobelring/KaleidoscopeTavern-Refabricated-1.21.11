package com.github.ysbbbbbb.kaleidoscopetavern.compat.trinkets.init;

import com.github.ysbbbbbb.kaleidoscopetavern.compat.trinkets.renderer.StringLightTrinketRenderer;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import eu.pb4.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.Contract;

@Environment(EnvType.CLIENT)
public class ModTrinketsClientCompat {

    @Contract(pure = true)
    static void init() {
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_COLORLESS, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_WHITE, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_LIGHT_GRAY, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_GRAY, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_BLACK, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_BROWN, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_RED, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_ORANGE, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_YELLOW, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_LIME, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_GREEN, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_CYAN, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_LIGHT_BLUE, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_BLUE, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_PURPLE, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_MAGENTA, new StringLightTrinketRenderer());
        TrinketRendererRegistry.registerRenderer(ModItems.STRING_LIGHTS_PINK, new StringLightTrinketRenderer());
    }
}
