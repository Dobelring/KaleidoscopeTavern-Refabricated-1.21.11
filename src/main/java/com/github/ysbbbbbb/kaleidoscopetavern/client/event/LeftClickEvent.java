package com.github.ysbbbbbb.kaleidoscopetavern.client.event;

import com.github.ysbbbbbb.kaleidoscopetavern.api.event.PlayerLeftClickEvent;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopetavern.item.ShakerItem;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.ClearShakerC2SMessage;
import com.github.ysbbbbbb.kaleidoscopetavern.util.event.IKTEvent;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class LeftClickEvent {

    public static void register() {
        IKTEvent.CALLBACK.register(event -> {
            if (event instanceof PlayerLeftClickEvent playerLeftClickEvent) {
                onLeftClickItem(playerLeftClickEvent);
            }
        });
    }

    public static void onLeftClickItem(PlayerLeftClickEvent event) {
        Player player = event.getPlayer();
        if (player != null && player.isSecondaryUseActive() && event.getHand() == InteractionHand.MAIN_HAND) {
            ItemStack stack = player.getMainHandItem();
            if (stack.is(ModItems.SHAKER) && ShakerItem.hasStorage(stack)) {
                ClientPlayNetworking.send(new ClearShakerC2SMessage());
            }
        }
    }
}
