package com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.barrel;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class BarrelViewType implements ReliableClientRecipeType {

    public static final BarrelViewType INSTANCE = new BarrelViewType();

    private BarrelViewType() {
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("emi.category.kaleidoscope_tavern.barrel");
    }

    @Override
    public int getDisplayWidth() {
        return 170;
    }

    @Override
    public int getDisplayHeight() {
        return 138;
    }

    @Override
    public @Nullable Identifier getGuiTexture() {
        return Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "textures/gui/eiv/barrel.png");
    }

    @Override
    public int getSlotCount() {
        return 7;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 10, 2); // base fluid
        for (int i = 0; i < 4; i++) {
            slotDefinition.addItemSlot(i + 1, 30 + 18 * i, 2); // additional ingredients
        }
        slotDefinition.addItemSlot(5, 84, 110); // carrier
        slotDefinition.addItemSlot(6, 147, 79); // result
    }

    @Override
    public Identifier getId() {
        return Identifier.withDefaultNamespace("kaleidoscope_barrel");
    }

    @Override
    public ItemStack getIcon() {
        return ModItems.BARREL.getDefaultInstance();
    }
}
