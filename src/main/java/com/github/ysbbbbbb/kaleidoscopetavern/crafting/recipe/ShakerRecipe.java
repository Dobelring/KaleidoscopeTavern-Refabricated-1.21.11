package com.github.ysbbbbbb.kaleidoscopetavern.crafting.recipe;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.crafting.container.SimpleInput;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModRecipes;
import com.github.ysbbbbbb.kaleidoscopetavern.util.ColorUtils;
import com.github.ysbbbbbb.kaleidoscopetavern.util.neo.RecipeMatcher;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public final class ShakerRecipe implements Recipe<SimpleInput> {
    private final NonNullList<Ingredient> ingredients;
    private final ItemStackTemplate result;
    private Int2ObjectMap<TextColor> ingredientColors;

    public ShakerRecipe(NonNullList<Ingredient> ingredients, ItemStackTemplate result, Int2ObjectMap<TextColor> ingredientColors) {
        this.ingredients = ingredients;
        this.result = result;
        this.ingredientColors = ingredientColors;
    }

    @Override
    public boolean matches(SimpleInput input, @NonNull Level level) {
        return RecipeMatcher.findMatches(input.inputs(), this.effectiveIngredients()) != null;
    }

    @Override
    public @NotNull ItemStack assemble(@NonNull SimpleInput input) {
        return this.result.create();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public @NonNull String group() {
        return "shaker";
    }

    @Override
    public @NonNull RecipeSerializer<? extends Recipe<SimpleInput>> getSerializer() {
        return ModRecipes.SHAKER_SERIALIZER;
    }

    @Override
    public @NonNull RecipeType<? extends Recipe<SimpleInput>> getType() {
        return ModRecipes.SHAKER_RECIPE;
    }

    @Override
    public @NonNull PlacementInfo placementInfo() {
        return PlacementInfo.create(this.effectiveIngredients());
    }

    @Override
    public @NonNull RecipeBookCategory recipeBookCategory() {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "shaker"), new RecipeBookCategory());
    }

    public NonNullList<Ingredient> ingredients() {
        return ingredients;
    }

    public ItemStackTemplate result() {
        return result;
    }

    public Int2ObjectMap<TextColor> ingredientColors() {
        if (this.ingredientColors.equals(Int2ObjectMaps.emptyMap())) {
            this.ingredientColors = getIngredientColors(this.ingredients);
        }
        return ingredientColors;
    }

    private NonNullList<Ingredient> effectiveIngredients() {
        NonNullList<Ingredient> filtered = NonNullList.create();
        for (Ingredient ingredient : this.ingredients) {
            if (!ingredient.isEmpty()) {
                filtered.add(ingredient);
            }
        }
        return filtered;
    }

    private static Int2ObjectMap<TextColor> getIngredientColors(NonNullList<Ingredient> ingredients) {
        Int2ObjectMap<TextColor> ingredientColors = new Int2ObjectOpenHashMap<>();
        for (int i = 0; i < ingredients.size(); i++) {
            var formatting = getColor(ingredients.get(i));
            if (formatting != null) {
                ingredientColors.put(i, formatting);
            }
        }
        return ingredientColors;
    }

    @Nullable
    private static TextColor getColor(Ingredient ingredient) {
        for (ItemStack stack : ingredient.items().map(ItemStack::new).toList()) {
            for (var entry : ColorUtils.COCKTAIL_INGREDIENT_COLORS.entrySet()) {
                if (stack.is(entry.getKey())) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }
}
