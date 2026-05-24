package com.github.ysbbbbbb.kaleidoscopetavern.client.init.registry;

import com.github.ysbbbbbb.kaleidoscopetavern.client.init.ClientSetupEvent;
import com.github.ysbbbbbb.kaleidoscopetavern.client.init.ModEntitiesRender;
import com.github.ysbbbbbb.kaleidoscopetavern.client.init.ModParticleFactoryRegistry;
import com.github.ysbbbbbb.kaleidoscopetavern.compat.create.ponder.init.PonderCompat;
import com.github.ysbbbbbb.kaleidoscopetavern.compat.trinkets.init.TrinketsCompactClient;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModFluids;
import com.github.ysbbbbbb.kaleidoscopetavern.network.NetworkHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class ClientRegistry {
    public static void init() {
        NetworkHandler.Clientside.init();
        ClientSetupEvent.init();
        ModEntitiesRender.init();
        ModFluids.registerFluidRenderers();
        ModParticleFactoryRegistry.init();
        modCompatClient();
    }

    private static void modCompatClient() {
        PonderCompat.init();
        TrinketsCompactClient.init();
    }
}
