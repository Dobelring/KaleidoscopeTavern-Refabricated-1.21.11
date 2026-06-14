package com.github.ysbbbbbb.kaleidoscopetavern.network.message;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.data.DrinkEffectData;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.resources.DrinkEffectDataReloadListener;
import com.github.ysbbbbbb.kaleidoscopetavern.network.NetworkHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;

/**
 * 服务端 -> 客户端同步所有 DrinkEffectData 数据
 */
public class DrinkEffectSyncS2CMessage implements FabricPacket {

    public static final PacketType<DrinkEffectSyncS2CMessage> TYPE = PacketType.create(NetworkHandler.DRINK_EFFECT_SYNC_S2C_PACKET, DrinkEffectSyncS2CMessage::new);
    private static final String ENTRIES = "entries";

    private final CompoundTag data;

    public DrinkEffectSyncS2CMessage(FriendlyByteBuf buf) {
        this.data = buf.readNbt();
    }

    /**
     * 从当前服务端 INSTANCE 构建消息
     */
    public static DrinkEffectSyncS2CMessage fromServer(FriendlyByteBuf buf) {
        CompoundTag syncData = new CompoundTag();
        ListTag entries = new ListTag();
        for (DrinkEffectData data : DrinkEffectDataReloadListener.INSTANCE.values()) {
            var result = DrinkEffectData.CODEC.encodeStart(NbtOps.INSTANCE, data);
            result.result().ifPresentOrElse(tag -> {
                if (tag instanceof CompoundTag compoundTag) {
                    entries.add(compoundTag);
                }
            }, () -> result.error().ifPresent(error -> KaleidoscopeTavern.LOGGER.error(
                    "Failed to encode drink effect data for sync: {}", error.message())));
        }
        syncData.put(ENTRIES, entries);
        buf.writeNbt(syncData);
        return new DrinkEffectSyncS2CMessage(buf);
    }

    public static DrinkEffectSyncS2CMessage decode(FriendlyByteBuf buf) {
        return new DrinkEffectSyncS2CMessage(buf);
    }

//    public static void handle(DrinkEffectSyncS2CMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
//        NetworkEvent.Context context = contextSupplier.get();
//        if (context.getDirection().getReceptionSide().isClient()) {
//            context.enqueueWork(() -> onHandle(message));
//        }
//        context.setPacketHandled(true);
//    }

    @Environment(EnvType.CLIENT)
    private static void onHandle(DrinkEffectSyncS2CMessage message) {
        DrinkEffectDataReloadListener.INSTANCE.clear();
        ListTag entries = message.data.getList(ENTRIES, Tag.TAG_COMPOUND);
        for (int i = 0; i < entries.size(); i++) {
            CompoundTag entry = entries.getCompound(i);
            var result = DrinkEffectData.CODEC.parse(NbtOps.INSTANCE, entry);
            result.result().ifPresentOrElse(data -> DrinkEffectDataReloadListener.INSTANCE.put(data.item(), data),
                    () -> result.error().ifPresent(error -> KaleidoscopeTavern.LOGGER.error(
                            "Failed to decode synced drink effect data: {}", error.message())));
        }
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeNbt(this.data);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
}
