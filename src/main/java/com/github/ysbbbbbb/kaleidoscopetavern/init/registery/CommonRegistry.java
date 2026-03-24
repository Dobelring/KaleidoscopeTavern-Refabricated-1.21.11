package com.github.ysbbbbbb.kaleidoscopetavern.init.registery;

import com.github.ysbbbbbb.kaleidoscopetavern.block.dispenser.BottleBlockDispenseBehavior;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.PressingTubBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.datamap.resources.DrinkEffectDataReloadListener;
import com.github.ysbbbbbb.kaleidoscopetavern.event.AddFeaturesEvent;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModItems;
import com.github.ysbbbbbb.kaleidoscopetavern.network.NetworkHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.DispenserBlock;

public class CommonRegistry {
    public static void init() {
        NetworkHandler.init();
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new DrinkEffectDataReloadListener());
        dispenseRegister();
        storageRegister();
        events();
        fuelRegistry();
        modCompat();
    }

    public static void events() {
        AddFeaturesEvent.addFeatures();
    }

    public static void fuelRegistry() {
        FuelRegistry.INSTANCE.add(ModItems.GRAPEVINE, 200);
    }

    public static void dispenseRegister() {
        DispenserBlock.registerBehavior(ModItems.WHISKEY, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.WINE, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.BRANDY, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.SAKURA_WINE, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.PLUM_WINE, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.CHAMPAGNE, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.VODKA, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.CARIGNAN, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.EMPTY_BOTTLE, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.ICE_WINE, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.VINEGAR, new BottleBlockDispenseBehavior());
        DispenserBlock.registerBehavior(ModItems.MOLOTOV, new BottleBlockDispenseBehavior());
    }

    public static void modCompat() {

    }


    public static void storageRegister() {
        ItemStorage.SIDED.registerForBlockEntity(
                PressingTubBlockEntity::getItemStorage,
                ModBlocks.PRESSING_TUB_BE
        );
    }
}
