package com.github.ysbbbbbb.kaleidoscopetavern.client.init.registry;

import com.github.ysbbbbbb.kaleidoscopetavern.client.init.ClientSetupEvent;
import com.github.ysbbbbbb.kaleidoscopetavern.client.init.ModEntitiesRender;
import com.github.ysbbbbbb.kaleidoscopetavern.compat.create.ponder.init.PonderCompat;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModFluids;
import com.github.ysbbbbbb.kaleidoscopetavern.network.NetworkHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

import static com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks.*;

@Environment(EnvType.CLIENT)
public class ClientRegistry {
    public static void init() {
        NetworkHandler.Clientside.init();
        ClientSetupEvent.init();
        ModEntitiesRender.init();
        ModFluids.registerFluidRenderers();
        modCompatClient();
    }

    private static void modCompatClient() {
        PonderCompat.init();
    }
}
