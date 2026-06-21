package com.github.ysbbbbbb.kaleidoscopetavern.network;

import com.github.ysbbbbbb.kaleidoscopetavern.network.message.ClearShakerC2SMessage;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.DrinkEffectSyncS2CMessage;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.TextOpenS2CMessage;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.TextUpdateC2SMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.jetbrains.annotations.Contract;

public class NetworkHandler {

    @Contract
    public static void init() {
        //==================================================Payload==============================================
        PayloadTypeRegistry.clientboundPlay().register(TextOpenS2CMessage.TYPE, TextOpenS2CMessage.STREAM_CODEC);
        PayloadTypeRegistry.clientboundPlay().register(DrinkEffectSyncS2CMessage.TYPE, DrinkEffectSyncS2CMessage.STREAM_CODEC);
        PayloadTypeRegistry.serverboundPlay().register(TextUpdateC2SMessage.TYPE, TextUpdateC2SMessage.STREAM_CODEC);
        PayloadTypeRegistry.serverboundPlay().register(ClearShakerC2SMessage.TYPE, ClearShakerC2SMessage.STREAM_CODEC);


        ServerPlayNetworking.registerGlobalReceiver(TextUpdateC2SMessage.TYPE, TextUpdateC2SMessage::receive);
        ServerPlayNetworking.registerGlobalReceiver(ClearShakerC2SMessage.TYPE, ClearShakerC2SMessage::receive);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            DrinkEffectSyncS2CMessage message = DrinkEffectSyncS2CMessage.fromServer();
            server.getPlayerList().getPlayers().forEach(serverPlayer -> {
                ServerPlayNetworking.send(serverPlayer, message);
            });
        });
    }

    @Environment(EnvType.CLIENT)
    public static class Clientside {
        @Contract
        public static void init() {
            ClientPlayNetworking.registerGlobalReceiver(TextOpenS2CMessage.TYPE, TextOpenS2CMessage::receive);
            ClientPlayNetworking.registerGlobalReceiver(DrinkEffectSyncS2CMessage.TYPE, DrinkEffectSyncS2CMessage::onHandle);
        }
    }
}
