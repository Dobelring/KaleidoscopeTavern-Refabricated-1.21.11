package com.github.ysbbbbbb.kaleidoscopetavern.client.event;

import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopetavern.item.ShakerItem;
import com.github.ysbbbbbb.kaleidoscopetavern.network.message.ClearShakerC2SMessage;
import io.github.fabricators_of_create.porting_lib.entity.events.PlayerInteractionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


import static io.github.fabricators_of_create.porting_lib.entity.events.PlayerInteractionEvents.LEFT_CLICK_EMPTY;

public class LeftClickEvent {

    public static void register() {
        LEFT_CLICK_EMPTY.register(LeftClickEvent::onLeftClickItem);
    }

    private static void onLeftClickItem(PlayerInteractionEvents.LeftClickEmpty event) {
        Player player = event.getEntity();
        if (player.isSecondaryUseActive() && event.getHand() == InteractionHand.MAIN_HAND) {
            ItemStack stack = player.getMainHandItem();
            if (stack.is(ModItems.SHAKER) && ShakerItem.hasStorage(stack)) {
                ClientPlayNetworking.send(new ClearShakerC2SMessage());
            }
        }
    }
}
