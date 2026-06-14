package com.github.ysbbbbbb.kaleidoscopetavern.network;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.ClearShakerC2SMessage;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.DrinkEffectSyncS2CMessage;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.TextOpenS2CMessage;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.TextUpdateC2SMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class NetworkHandler {
    public static final ResourceLocation TEXT_UPDATE_C2S_PACKET = new ResourceLocation(KaleidoscopeTavern.MOD_ID, "text_update");
    public static final ResourceLocation TEXT_OPEN_S2C_PACKET = new ResourceLocation(KaleidoscopeTavern.MOD_ID, "text_open");
    public static final ResourceLocation DRINK_EFFECT_SYNC_S2C_PACKET = new ResourceLocation(KaleidoscopeTavern.MOD_ID, "drink_effect_sync");
    public static final ResourceLocation CLEAR_SHAKER_S2C_PACKET = new ResourceLocation(KaleidoscopeTavern.MOD_ID, "clear_shaker");

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(TextUpdateC2SMessage.TYPE, TextUpdateC2SMessage::receive);
        ServerPlayNetworking.registerGlobalReceiver(ClearShakerC2SMessage.TYPE, ClearShakerC2SMessage::receive);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            FriendlyByteBuf buf = PacketByteBufs.create();
            DrinkEffectSyncS2CMessage message = DrinkEffectSyncS2CMessage.fromServer(buf);
            server.getPlayerList().getPlayers().forEach(serverPlayer -> {
                ServerPlayNetworking.send(serverPlayer, message.getType().getId(), buf);
            });
        });
    }

    @Environment(EnvType.CLIENT)
    public static class Clientside {
        public static void init() {
            ClientPlayNetworking.registerGlobalReceiver(TextOpenS2CMessage.TYPE, TextOpenS2CMessage::receive);
        }
    }
}
