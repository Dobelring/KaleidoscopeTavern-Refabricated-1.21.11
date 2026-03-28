package com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.common.recipe.ServerRecipeManager;
import com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.barrel.BarrelServerRecipe;
import com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.barrel.BarrelViewRecipe;
import com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.pressing_tub.PressingTubServerRecipe;
import com.github.ysbbbbbb.kaleidoscopetavern.compat.rrv.pressing_tub.PressingTubViewRecipe;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModRecipes;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModRRVPlugin implements ReliableRecipeViewerPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addServerRecipeProvider(list -> {
            ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipes.BARREL_RECIPE).forEach(recipe -> {
                List<Ingredient> ingredients = new ArrayList<>(recipe.ingredients());
                ingredients.addFirst(Ingredient.of(recipe.fluid().getBucket()));
                list.add(new BarrelServerRecipe(recipe.result(), ingredients, recipe.carrier()));
            });
            ServerRecipeManager.INSTANCE.getRecipesForType(ModRecipes.PRESSING_TUB_RECIPE).forEach(recipe -> {
                list.add(new PressingTubServerRecipe(recipe.getFluid().getBucket().getDefaultInstance(), recipe.input()));
            });
        });

        ItemView.addClientRecipeWrapper(BarrelServerRecipe.TYPE, i -> Collections.singletonList(new BarrelViewRecipe(i)));
        ItemView.addClientRecipeWrapper(PressingTubServerRecipe.TYPE, i -> Collections.singletonList(new PressingTubViewRecipe(i)));
    }
}
