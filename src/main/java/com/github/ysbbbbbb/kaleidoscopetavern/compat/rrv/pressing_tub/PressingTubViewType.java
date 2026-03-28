package com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.pressing_tub;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class PressingTubViewType implements ReliableClientRecipeType {

    public static final PressingTubViewType INSTANCE = new PressingTubViewType();

    private PressingTubViewType() {
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("emi.category.kaleidoscope_tavern.pressing_tub");
    }

    @Override
    public int getDisplayWidth() {
        return 112;
    }

    @Override
    public int getDisplayHeight() {
        return 67;
    }

    @Override
    public @Nullable Identifier getGuiTexture() {
        return Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "textures/gui/eiv/pressing_tub.png");
    }

    @Override
    public int getSlotCount() {
        return 3;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 23, 13); // ingredient
        slotDefinition.addItemSlot(1, 66, 4); // bucket
        slotDefinition.addItemSlot(2, 92, 26); // result
    }

    @Override
    public Identifier getId() {
        return Identifier.withDefaultNamespace("kaleidoscope_pressing_tub");
    }

    @Override
    public ItemStack getIcon() {
        return ModItems.PRESSING_TUB.getDefaultInstance();
    }
}
