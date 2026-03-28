package com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.pressing_tub;

import cc.cassian.rrv.api.TagUtil;
import cc.cassian.rrv.api.recipe.ReliableServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;
import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class PressingTubServerRecipe implements ReliableServerRecipe {
    public static final ReliableServerRecipeType<PressingTubServerRecipe> TYPE = ReliableServerRecipeType.register(
            Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "pressing_tub"),
            () -> new PressingTubServerRecipe(null, null)
    );
    private ItemStack result;
    private Ingredient input;

    public PressingTubServerRecipe(ItemStack result, Ingredient input) {
        this.result = result;
        this.input = input;
    }

    public ItemStack getResult() {
        return result;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public void writeToTag(CompoundTag tag) {
        tag.put("result", TagUtil.encodeItemStackOnServer(this.result));
        tag.put("input", TagUtil.writeIngredient(this.input));
    }

    @Override
    public void loadFromTag(CompoundTag tag) {
        this.result = TagUtil.decodeItemStackOnServer(tag.getCompound("result").orElseGet(CompoundTag::new));
        this.input = TagUtil.readIngredient(tag.getCompound("input").orElseGet(CompoundTag::new));
    }

    @Override
    public ReliableServerRecipeType<? extends ReliableServerRecipe> getRecipeType() {
        return TYPE;
    }
}
