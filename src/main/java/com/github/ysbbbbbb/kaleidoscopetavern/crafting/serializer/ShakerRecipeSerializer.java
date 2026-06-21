package com.github.ysbbbbbb.kaleidoscopetavern.crafting.serializer;

import com.github.ysbbbbbb.kaleidoscopetavern.crafting.recipe.ShakerRecipe;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShakerRecipeSerializer {
    public static final int MAX_INGREDIENTS = 3;

    private static NonNullList<Ingredient> normalizeIngredients(List<Ingredient> list) {
        NonNullList<Ingredient> nonnull = NonNullList.create();
        int size = Math.min(list.size(), MAX_INGREDIENTS);
        for (int i = 0; i < size; i++) {
            Ingredient ingredient = list.get(i);
            if (ingredient != null) {
                nonnull.add(ingredient);
            }
        }
        return nonnull;
    }

    private static final MapCodec<ShakerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Ingredient.CODEC.listOf().xmap(
                    ShakerRecipeSerializer::normalizeIngredients,
                    nonnull -> nonnull.stream().limit(MAX_INGREDIENTS).toList()
            ).optionalFieldOf("ingredients", NonNullList.create()).forGetter(ShakerRecipe::ingredients),
            ItemStackTemplate.CODEC.fieldOf("result").forGetter(ShakerRecipe::result)
    ).apply(instance, (ingredients, result) -> new ShakerRecipe(ingredients, result, Int2ObjectMaps.emptyMap())));

    private static final StreamCodec<RegistryFriendlyByteBuf, ShakerRecipe> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public @NotNull ShakerRecipe decode(RegistryFriendlyByteBuf buf) {
            int size = Math.min(MAX_INGREDIENTS, buf.readVarInt());
            NonNullList<Ingredient> ingredients = NonNullList.create();
            for (int i = 0; i < size; i++) {
                ingredients.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            }

            ItemStackTemplate result = ItemStackTemplate.STREAM_CODEC.decode(buf);

            Int2ObjectMap<ChatFormatting> ingredientColors = new Int2ObjectOpenHashMap<>();
            int colorSize = buf.readVarInt();
            for (int i = 0; i < colorSize; i++) {
                int index = buf.readVarInt();
                ChatFormatting formatting = ChatFormatting.getById(buf.readVarInt());
                if (formatting != null) {
                    ingredientColors.put(index, formatting);
                }
            }
            return new ShakerRecipe(ingredients, result, ingredientColors);
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, ShakerRecipe recipe) {
            var ingredients = recipe.ingredients().stream().limit(MAX_INGREDIENTS).toList();
            buf.writeVarInt(ingredients.size());
            for (Ingredient ingredient : ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
            }

            ItemStackTemplate.STREAM_CODEC.encode(buf, recipe.result());

            Int2ObjectMap<ChatFormatting> ingredientColors = recipe.ingredientColors();
            buf.writeVarInt(ingredientColors.size());
            ingredientColors.forEach((index, formatting) -> {
                buf.writeVarInt(index);
                buf.writeVarInt(formatting.getId());
            });
        }
    };

    public static @NotNull MapCodec<ShakerRecipe> codec() {
        return CODEC;
    }

    public static @NotNull StreamCodec<RegistryFriendlyByteBuf, ShakerRecipe> streamCodec() {
        return STREAM_CODEC;
    }
}
