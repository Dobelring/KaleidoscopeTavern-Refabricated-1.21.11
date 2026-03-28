package com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.pressing_tub;

import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Collections;
import java.util.List;

public class PressingTubViewRecipe implements ReliableClientRecipe {
    private final ItemStack result;
    private final Ingredient input;
    public PressingTubViewRecipe(PressingTubServerRecipe serverRecipe) {
        this.result = serverRecipe.getResult();
        this.input = serverRecipe.getInput();
    }
    @Override
    public ReliableClientRecipeType getViewType() {
        return PressingTubViewType.INSTANCE;
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, SlotContent.of(this.input));
        slotFillContext.bindSlot(1, SlotContent.of(Ingredient.of(Items.BUCKET)));
        slotFillContext.bindSlot(2, SlotContent.of(this.result));
    }

    @Override
    public List<SlotContent> getIngredients() {
        return Collections.singletonList(SlotContent.of(input));
    }

    @Override
    public List<SlotContent> getResults() {
        return Collections.singletonList(SlotContent.of(result));
    }
}
