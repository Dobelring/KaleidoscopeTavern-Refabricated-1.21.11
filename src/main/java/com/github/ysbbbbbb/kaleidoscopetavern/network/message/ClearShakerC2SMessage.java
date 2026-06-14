package com.github.ysbbbbbb.kaleidoscopetavern.network.message;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopetavern.item.ShakerItem;
import com.github.ysbbbbbb.kaleidoscopetavern.network.NetworkHandler;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;

public record ClearShakerC2SMessage() implements FabricPacket {
    public static final PacketType<ClearShakerC2SMessage> TYPE = PacketType.create(NetworkHandler.CLEAR_SHAKER_S2C_PACKET, ClearShakerC2SMessage::new);

    public ClearShakerC2SMessage(FriendlyByteBuf buf) {
        this();
    }

    @Override
    public void write(FriendlyByteBuf buf) {

    }

    public static void receive(ClearShakerC2SMessage message, ServerPlayer sender, PacketSender packetSender) {
        if (sender == null) {
            return;
        }
        ItemStack stack = sender.getMainHandItem();
        if (stack.is(ModItems.SHAKER) && ShakerItem.hasStorage(stack)) {
            ShakerItem.removeAll(stack);
            sender.level().playSound(null, sender.blockPosition(), SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS);
        }
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
}
