package com.github.ysbbbbbb.kaleidoscopetavern.network;

import com.github.ysbbbbbb.kaleidoscopetavern.network.message.TextOpenS2CMessage;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.TextUpdateC2SMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class NetworkHandler {

    public static void init() {
        PayloadTypeRegistry.clientboundPlay().register(TextOpenS2CMessage.TYPE, TextOpenS2CMessage.STREAM_CODEC);
        PayloadTypeRegistry.serverboundPlay().register(TextUpdateC2SMessage.TYPE, TextUpdateC2SMessage.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(TextUpdateC2SMessage.TYPE, TextUpdateC2SMessage::receive);
    }

    @Environment(EnvType.CLIENT)
    public static class Clientside {
        public static void init() {
            ClientPlayNetworking.registerGlobalReceiver(TextOpenS2CMessage.TYPE, TextOpenS2CMessage::receive);
        }
    }
}
