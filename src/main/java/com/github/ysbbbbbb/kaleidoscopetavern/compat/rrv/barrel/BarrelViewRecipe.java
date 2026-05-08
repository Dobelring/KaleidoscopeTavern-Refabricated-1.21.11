package com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.barrel;

import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BarrelViewRecipe implements ReliableClientRecipe {
    private final ItemStack result;
    private final List<Ingredient> ingredients;
    private final Ingredient carrier;
    public BarrelViewRecipe(BarrelServerRecipe serverRecipe) {
        this.carrier = serverRecipe.getCarrier();
        this.ingredients = serverRecipe.getIngredients();
        this.result = serverRecipe.getResult();
    }
    @Override
    public ReliableClientRecipeType getViewType() {
        return BarrelViewType.INSTANCE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, SlotContent.of(this.ingredients.getFirst().items().map(s -> s.value().getDefaultInstance().copyWithCount(4)).toList()));
        for (int i = 1; i < this.ingredients.size(); i++) {
            slotFillContext.bindSlot(i, SlotContent.of(this.ingredients.get(i).items().map(s -> s.value().getDefaultInstance().copyWithCount(16)).toList()));
        }
        slotFillContext.bindSlot(5, SlotContent.of(this.carrier));
        slotFillContext.bindSlot(6, SlotContent.of(this.result.copyWithCount(16)));
    }

    @Override
    public List<SlotContent> getIngredients() {
        List<SlotContent> list = new ArrayList<>(this.ingredients.stream().map(SlotContent::of).toList());
        list.add(SlotContent.of(this.carrier));
        return list;
    }

    @Override
    public List<SlotContent> getResults() {
        return Collections.singletonList(SlotContent.of(this.result));
    }
}
