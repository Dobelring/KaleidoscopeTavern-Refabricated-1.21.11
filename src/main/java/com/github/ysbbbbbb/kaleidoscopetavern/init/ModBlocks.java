package com.github.ysbbbbbb.kaleidoscopetavern.init;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.block.brew.*;
import com.github.ysbbbbbb.kaleidoscopetavern.block.deco.*;
import com.github.ysbbbbbb.kaleidoscopetavern.block.mixology.*;
import com.github.ysbbbbbb.kaleidoscopetavern.block.plant.*;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.brew.*;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.deco.*;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.mixology.ShakerBlockEntity;
import com.github.ysbbbbbb.kaleidoscopetavern.blockentity.mixology.SignatureCocktailBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.phys.shapes.Shapes;

@SuppressWarnings("all")
public final class ModBlocks {
    // 沙发
    public static final Block WHITE_SOFA = new SofaBlock();
    public static final Block LIGHT_GRAY_SOFA = new SofaBlock();
    public static final Block GRAY_SOFA = new SofaBlock();
    public static final Block BLACK_SOFA = new SofaBlock();
    public static final Block BROWN_SOFA = new SofaBlock();
    public static final Block RED_SOFA = new SofaBlock();
    public static final Block ORANGE_SOFA = new SofaBlock();
    public static final Block YELLOW_SOFA = new SofaBlock();
    public static final Block LIME_SOFA = new SofaBlock();
    public static final Block GREEN_SOFA = new SofaBlock();
    public static final Block CYAN_SOFA = new SofaBlock();
    public static final Block LIGHT_BLUE_SOFA = new SofaBlock();
    public static final Block BLUE_SOFA = new SofaBlock();
    public static final Block PURPLE_SOFA = new SofaBlock();
    public static final Block MAGENTA_SOFA = new SofaBlock();
    public static final Block PINK_SOFA = new SofaBlock();

    // 高脚凳
    public static final Block WHITE_BAR_STOOL = new BarStoolBlock(DyeColor.WHITE);
    public static final Block LIGHT_GRAY_BAR_STOOL = new BarStoolBlock(DyeColor.LIGHT_GRAY);
    public static final Block GRAY_BAR_STOOL = new BarStoolBlock(DyeColor.GRAY);
    public static final Block BLACK_BAR_STOOL = new BarStoolBlock(DyeColor.BLACK);
    public static final Block BROWN_BAR_STOOL = new BarStoolBlock(DyeColor.BROWN);
    public static final Block RED_BAR_STOOL = new BarStoolBlock(DyeColor.RED);
    public static final Block ORANGE_BAR_STOOL = new BarStoolBlock(DyeColor.ORANGE);
    public static final Block YELLOW_BAR_STOOL = new BarStoolBlock(DyeColor.YELLOW);
    public static final Block LIME_BAR_STOOL = new BarStoolBlock(DyeColor.LIME);
    public static final Block GREEN_BAR_STOOL = new BarStoolBlock(DyeColor.GREEN);
    public static final Block CYAN_BAR_STOOL = new BarStoolBlock(DyeColor.CYAN);
    public static final Block LIGHT_BLUE_BAR_STOOL = new BarStoolBlock(DyeColor.LIGHT_BLUE);
    public static final Block BLUE_BAR_STOOL = new BarStoolBlock(DyeColor.BLUE);
    public static final Block PURPLE_BAR_STOOL = new BarStoolBlock(DyeColor.PURPLE);
    public static final Block MAGENTA_BAR_STOOL = new BarStoolBlock(DyeColor.MAGENTA);
    public static final Block PINK_BAR_STOOL = new BarStoolBlock(DyeColor.PINK);

    // 展板
    public static final Block BASE_SANDWICH_BOARD = new SandwichBoardBlock();
    public static final Block GRASS_SANDWICH_BOARD = new SandwichBoardBlock(Items.SHORT_GRASS);
    public static final Block ALLIUM_SANDWICH_BOARD = new SandwichBoardBlock(Items.AZURE_BLUET, Items.OXEYE_DAISY, Items.LILY_OF_THE_VALLEY);
    public static final Block AZURE_BLUET_SANDWICH_BOARD = new SandwichBoardBlock(Items.AZURE_BLUET, Items.OXEYE_DAISY, Items.LILY_OF_THE_VALLEY);
    public static final Block CORNFLOWER_SANDWICH_BOARD = new SandwichBoardBlock(Items.CORNFLOWER);
    public static final Block ORCHID_SANDWICH_BOARD = new SandwichBoardBlock(Items.BLUE_ORCHID);
    public static final Block PEONY_SANDWICH_BOARD = new SandwichBoardBlock(Items.PEONY, Items.LILAC);
    public static final Block PINK_PETALS_SANDWICH_BOARD = new SandwichBoardBlock(Items.PINK_PETALS);
    public static final Block PITCHER_PLANT_SANDWICH_BOARD = new SandwichBoardBlock(Items.PITCHER_PLANT);
    public static final Block POPPY_SANDWICH_BOARD = new SandwichBoardBlock(Items.POPPY, Items.ROSE_BUSH);
    public static final Block SUNFLOWER_SANDWICH_BOARD = new SandwichBoardBlock(Items.SUNFLOWER, Items.DANDELION);
    public static final Block TORCHFLOWER_SANDWICH_BOARD = new SandwichBoardBlock(Items.TORCHFLOWER);
    public static final Block TULIP_SANDWICH_BOARD = new SandwichBoardBlock(Items.RED_TULIP, Items.ORANGE_TULIP, Items.WHITE_TULIP, Items.PINK_TULIP);
    public static final Block WITHER_ROSE_SANDWICH_BOARD = new SandwichBoardBlock(Items.WITHER_ROSE);
    public static final Block EYEBLOSSOM_SANDWICH_BOARD = new SandwichBoardBlock();

    // 彩灯
    public static final Block STRING_LIGHTS_COLORLESS = new StringLightsBlock(null);
    public static final Block STRING_LIGHTS_WHITE = new StringLightsBlock(Items.WHITE_DYE);
    public static final Block STRING_LIGHTS_LIGHT_GRAY = new StringLightsBlock(Items.LIGHT_GRAY_DYE);
    public static final Block STRING_LIGHTS_GRAY = new StringLightsBlock(Items.GRAY_DYE);
    public static final Block STRING_LIGHTS_BLACK = new StringLightsBlock(Items.BLACK_DYE);
    public static final Block STRING_LIGHTS_BROWN = new StringLightsBlock(Items.BROWN_DYE);
    public static final Block STRING_LIGHTS_RED = new StringLightsBlock(Items.RED_DYE);
    public static final Block STRING_LIGHTS_ORANGE = new StringLightsBlock(Items.ORANGE_DYE);
    public static final Block STRING_LIGHTS_YELLOW = new StringLightsBlock(Items.YELLOW_DYE);
    public static final Block STRING_LIGHTS_LIME = new StringLightsBlock(Items.LIME_DYE);
    public static final Block STRING_LIGHTS_GREEN = new StringLightsBlock(Items.GREEN_DYE);
    public static final Block STRING_LIGHTS_CYAN = new StringLightsBlock(Items.CYAN_DYE);
    public static final Block STRING_LIGHTS_LIGHT_BLUE = new StringLightsBlock(Items.LIGHT_BLUE_DYE);
    public static final Block STRING_LIGHTS_BLUE = new StringLightsBlock(Items.BLUE_DYE);
    public static final Block STRING_LIGHTS_PURPLE = new StringLightsBlock(Items.PURPLE_DYE);
    public static final Block STRING_LIGHTS_MAGENTA = new StringLightsBlock(Items.MAGENTA_DYE);
    public static final Block STRING_LIGHTS_PINK = new StringLightsBlock(Items.PINK_DYE);

    // 挂画
    public static final Block YSBB_PAINTING = new PaintingBlock();
    public static final Block TARTARIC_ACID_PAINTING = new PaintingBlock();
    public static final Block CR019_PAINTING = new PaintingBlock();
    public static final Block UNKNOWN_PAINTING = new PaintingBlock();
    public static final Block MASTER_MARISA_PAINTING = new PaintingBlock();
    public static final Block SON_OF_MAN_PAINTING = new PaintingBlock();
    public static final Block DAVID_PAINTING = new PaintingBlock();
    public static final Block GIRL_WITH_PEARL_EARRING_PAINTING = new PaintingBlock();
    public static final Block STARRY_NIGHT_PAINTING = new PaintingBlock();
    public static final Block VAN_GOGH_SELF_PORTRAIT_PAINTING = new PaintingBlock();
    public static final Block FATHER_PAINTING = new PaintingBlock();
    public static final Block GREAT_WAVE_PAINTING = new PaintingBlock();
    public static final Block MONA_LISA_PAINTING = new PaintingBlock();
    public static final Block MONDRIAN_PAINTING = new PaintingBlock();
    public static final Block NIGHT_EPIPHANY_PAINTING = new PaintingBlock();

    // 桌子
    public static final Block TABLE = new TableBlock();
    // 果盆
    public static final Block PRESSING_TUB = new PressingTubBlock();
    // 空瓶
    public static final Block EMPTY_BOTTLE = new BottleBlock();
    public static final Block EMPTY_GLASSWARE = new GlasswareBlock();
    // 酒杯架
    public static final Block GLASSWARE_HOLDER = new GlasswareHolderBlock();
    // 鸡尾酒
    public static final Block SIGNATURE_COCKTAIL = new SignatureCocktailBlock();
    public static final Block MYSTERY_COCKTAIL = new MysteryCocktailBlock();
    public static final Block WHITE_LADY = new CocktailBlock();
    public static final Block EMERALD = new CocktailBlock();
    public static final Block BRASS_HEART = new CocktailBlock();
    public static final Block GODFATHER = new CocktailBlock();
    public static final Block GRASSHOPPER = new CocktailBlock();
    public static final Block SCREWDRIVER = new CocktailBlock();
    public static final Block MOJITO = new CocktailBlock();
    public static final Block ALLIUM_GARDEN = new CocktailBlock();
    public static final Block DEPTH_CHARGE = new CocktailBlock();
    public static final Block NETHER_SPECIAL = new CocktailBlock();
    public static final Block BLOODY_MARY = new CocktailBlock();
    public static final Block SCULK_SPECIAL = new CocktailBlock();
    // 杂项的瓶子
    public static final Block WATER_BOTTLE = BottleBlock.simpleBottle();
    public static final Block HONEY_BOTTLE = BottleBlock.simpleBottle();
    public static final Block DRAGON_BREATH_BOTTLE = BottleBlock.simpleBottle();
    public static final Block POTION_BOTTLE = new PotionBottleBlock();
    public static final Block XP_BOTTLE = BottleBlock.simpleBottle();

    // 酒桶
    public static final Block BARREL = new BarrelBlock();
    // 酒柜
    public static final Block BAR_CABINET = new BarCabinetBlock();
    public static final Block GLASS_BAR_CABINET = new BarCabinetBlock();
    public static final Block CELLAR_CABINET = new CellarCabinetBlock();
    // 酒架
    public static final Block TILTED_RACK = new TiltedRackBlock();
    public static final Block CIRCULAR_RACK = new CircularRackBlock();
    public static final Block HOLDER = new HolderBlock();

    public static final Block SHAKER = new ShakerBlock();
    // 野生葡萄藤
    public static final Block WILD_GRAPEVINE = new WildGrapevineBlock();
    public static final Block WILD_GRAPEVINE_PLANT = new WildGrapevinePlantBlock();
    // 藤架
    public static final Block TRELLIS = new TrellisBlock();
    // 葡萄
    public static final Block GRAPE_CROP = new GrapeCropBlock(
            (state, level, pos, random) -> 0.25F,
            () -> new ItemStack(ModItems.GRAPE, 3)
    );
    public static final Block ICE_GRAPE_CROP = new GrapeCropBlock(
            (state, level, pos, random) -> level.getBiome(pos).value().getBaseTemperature() < 0.15F ? 0.8F : 0.25F,
            () -> new ItemStack(ModItems.ICE_GRAPE, 3)
    );
    public static final Block GOLD_GRAPE_CROP = new GrapeCropBlock(
            (state, level, pos, random) -> level.getBiome(pos).value().getBaseTemperature() > 1.0F ? 0.8F : 0.25F,
            () -> new ItemStack(ModItems.GOLD_GRAPE, 3)
    );

    // 葡萄藤
    public static final Block GRAPEVINE_TRELLIS = new GrapevineTrellisBlock(
            (state, level, pos, random) -> 0.25F,
            ModBlocks.GRAPE_CROP::defaultBlockState
    );
    public static final Block ICE_GRAPEVINE_TRELLIS = new GrapevineTrellisBlock(
            (state, level, pos, random) ->
                    level.getBiome(pos).value().getBaseTemperature() < 0.15F ? 0.8F : 0.25F,
            ModBlocks.ICE_GRAPE_CROP::defaultBlockState
    );
    public static final Block GOLD_GRAPEVINE_TRELLIS = new GrapevineTrellisBlock(
            (state, level, pos, random) ->
                    level.getBiome(pos).value().getBaseTemperature() > 1.0F ? 0.8F : 0.25F,
            ModBlocks.GOLD_GRAPE_CROP::defaultBlockState
    );
    // 燃烧瓶
    public static final Block MOLOTOV =new MolotovBlock();
    // 龙头
    public static final Block TAP = new TapBlock();
    // 吧台
    public static final Block BAR_COUNTER = new BarCounterBlock();
    // 人字梯
    public static final Block STEPLADDER = new StepladderBlock();
    // 黑板
    public static final Block CHALKBOARD = new ChalkboardBlock();
    // 垂灯
    public static final Block BELL_PENDANT_LAMP = new PendantLampBlock();
    public static final Block YELLOW_PENDANT_LAMP = new PendantLampBlock();
    public static final Block BLUE_PENDANT_LAMP = new PendantLampBlock();
    // 香薰
    public static final Block SAKURA_INCENSE = new IncenseBlock(
            () -> ModParticles.SAKURA_INCENSE_PARTICLE, () -> ParticleTypes.CHERRY_LEAVES
    );

    public static final Block PINE_INCENSE = new IncenseBlock(
            () -> ModParticles.PINE_INCENSE_PARTICLE, () -> ModParticles.PINE_INCENSE_LARGE_PARTICLE
    );

    public static final Block GINKGO_INCENSE = new IncenseBlock(
            () -> ModParticles.GINKGO_INCENSE_PARTICLE, () -> ModParticles.GINKGO_INCENSE_LARGE_PARTICLE
    );

    public static final Block SPORE_INCENSE = new IncenseBlock(
            () -> ModParticles.SPORE_INCENSE_PARTICLE, () -> ParticleTypes.SPORE_BLOSSOM_AIR
    );

    public static final Block CATNIP_INCENSE = new IncenseBlock(
            () -> ModParticles.CATNIP_INCENSE_PARTICLE, () -> ModParticles.CATNIP_INCENSE_LARGE_PARTICLE
    );

    public static final Block SNOW_INCENSE = new IncenseBlock(
            () -> ModParticles.SNOW_INCENSE_PARTICLE, () -> ModParticles.SNOW_INCENSE_LARGE_PARTICLE
    );

    public static final Block BUTTERFLY_INCENSE = new IncenseBlock(
            () -> ModParticles.BUTTERFLY_INCENSE_PARTICLE, () -> ModParticles.BUTTERFLY_INCENSE_LARGE_PARTICLE
    );

    public static final Block FIREFLY_INCENSE = new IncenseBlock(
            () -> ModParticles.FIREFLY_INCENSE_PARTICLE, () -> ModParticles.FIREFLY_INCENSE_LARGE_PARTICLE,
            -0.67, 5.33
    );
    // 酒
    public static final Block WINE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block CHAMPAGNE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block VODKA = DrinkBlock.create().maxCount(4).shapes(
            Block.box(4, 0, 4, 12, 15, 12),
            Block.box(0, 0, 4, 16, 15, 12),
            Shapes.or(
                    Block.box(0, 0, 8, 16, 15, 16),
                    Block.box(4, 0, 0, 12, 15, 16)
            ),
            Block.box(0, 0, 0, 16, 16, 16)
    ).build();

    public static final Block BRANDY = DrinkBlock.create().maxCount(3).shapes(
            Block.box(3, 0, 6, 13, 13, 10),
            Block.box(1, 0, 3, 15, 12, 12),
            Block.box(1, 0, 1, 16, 12, 13)
    ).build();

    public static final Block CARIGNAN = DrinkBlock.create().maxCount(3).shapes(
            Block.box(3, 0, 6, 13, 13, 10),
            Block.box(1, 0, 3, 15, 12, 12),
            Block.box(1, 0, 1, 16, 12, 13)
    ).build();

    public static final Block SAKURA_WINE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block PLUM_WINE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 12, 10),
            Block.box(3, 0, 6, 13, 12, 10),
            Shapes.or(
                    Block.box(3, 0, 9, 13, 12, 13),
                    Block.box(6, 0, 3, 10, 12, 13)
            ),
            Block.box(3, 0, 3, 13, 12, 13)
    ).build();

    public static final Block WHISKEY = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block ICE_WINE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block POLARIS_SWEET_WHITE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block HONEY_WINE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block RED_QUEEN = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block MINERS_STAR = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block RUM = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block RIESLING_DRY_WHITE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(4, 0, 4, 12, 15, 12),
            Block.box(0, 0, 4, 16, 15, 12),
            Shapes.or(
                    Block.box(0, 0, 8, 16, 15, 16),
                    Block.box(4, 0, 0, 12, 15, 16)
            ),
            Block.box(0, 0, 0, 16, 16, 16)
    ).build();

    public static final Block SUNSET_GLOW = DrinkBlock.create().maxCount(3).shapes(
            Block.box(3, 0, 6, 13, 13, 10),
            Block.box(1, 0, 3, 15, 12, 12),
            Block.box(1, 0, 1, 16, 12, 13)
    ).build();

    public static final Block MADAME_SHEXIANG = DrinkBlock.create().maxCount(4).shapes(
            Block.box(4, 0, 4, 12, 15, 12),
            Block.box(0, 0, 4, 16, 15, 12),
            Shapes.or(
                    Block.box(0, 0, 8, 16, 15, 16),
                    Block.box(4, 0, 0, 12, 15, 16)
            ),
            Block.box(0, 0, 0, 16, 16, 16)
    ).build();

    public static final Block SWEET_BERRY_WINE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(4, 0, 4, 12, 15, 12),
            Block.box(0, 0, 4, 16, 15, 12),
            Shapes.or(
                    Block.box(0, 0, 8, 16, 15, 16),
                    Block.box(4, 0, 0, 12, 15, 16)
            ),
            Block.box(0, 0, 0, 16, 16, 16)
    ).build();

    public static final Block SHERRY = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block MOTHER_SNOW = DrinkBlock.create().maxCount(4).shapes(
            Block.box(4, 0, 4, 12, 15, 12),
            Block.box(0, 0, 4, 16, 15, 12),
            Shapes.or(
                    Block.box(0, 0, 8, 16, 15, 16),
                    Block.box(4, 0, 0, 12, 15, 16)
            ),
            Block.box(0, 0, 0, 16, 16, 16)
    ).build();

    public static final Block LUMINOUS_BRIDE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block GLOWFLOWER_BREW = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block SAUVIGNON_BLANC_DRY_WHITE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block VINEGAR = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    public static final Block WATERMELON_JUICE = DrinkBlock.create().maxCount(4).shapes(
            Block.box(6, 0, 6, 10, 16, 10),
            Block.box(2, 0, 6, 14, 16, 10),
            Shapes.or(
                    Block.box(2, 0, 10, 14, 16, 14),
                    Block.box(6, 0, 2, 10, 16, 14)
            ),
            Block.box(2, 0, 2, 14, 16, 14)
    ).build();

    // Block entity
    public static final BlockEntityType<SandwichBoardBlockEntity> SANDWICH_BOARD_BE = BlockEntityType.Builder.of(SandwichBoardBlockEntity::new,
            BASE_SANDWICH_BOARD,
            GRASS_SANDWICH_BOARD,
            ALLIUM_SANDWICH_BOARD,
            AZURE_BLUET_SANDWICH_BOARD,
            CORNFLOWER_SANDWICH_BOARD,
            ORCHID_SANDWICH_BOARD,
            PEONY_SANDWICH_BOARD,
            PINK_PETALS_SANDWICH_BOARD,
            PITCHER_PLANT_SANDWICH_BOARD,
            POPPY_SANDWICH_BOARD,
            SUNFLOWER_SANDWICH_BOARD,
            TORCHFLOWER_SANDWICH_BOARD,
            TULIP_SANDWICH_BOARD,
            WITHER_ROSE_SANDWICH_BOARD,
            EYEBLOSSOM_SANDWICH_BOARD
    ).build(null);
    public static final BlockEntityType<BarStoolBlockEntity> BAR_STOOL_BE = BlockEntityType.Builder.of(BarStoolBlockEntity::new,
            BLUE_BAR_STOOL,
            GREEN_BAR_STOOL,
            ORANGE_BAR_STOOL,
            PURPLE_BAR_STOOL,
            YELLOW_BAR_STOOL,
            BLACK_BAR_STOOL,
            WHITE_BAR_STOOL,
            GRAY_BAR_STOOL,
            BROWN_BAR_STOOL,
            LIME_BAR_STOOL,
            MAGENTA_BAR_STOOL,
            CYAN_BAR_STOOL,
            LIGHT_BLUE_BAR_STOOL,
            PINK_BAR_STOOL,
            LIGHT_GRAY_BAR_STOOL,
            RED_BAR_STOOL
    ).build(null);
    public static final BlockEntityType<ChalkboardBlockEntity> CHALKBOARD_BE = BlockEntityType.Builder.of(ChalkboardBlockEntity::new, CHALKBOARD).build(null);
    public static final BlockEntityType<PressingTubBlockEntity> PRESSING_TUB_BE = BlockEntityType.Builder.of(PressingTubBlockEntity::new, PRESSING_TUB).build(null);
    public static final BlockEntityType<BarrelBlockEntity> BARREL_BE = BlockEntityType.Builder.of(BarrelBlockEntity::new, BARREL).build(null);
    public static final BlockEntityType<TapBlockEntity> TAP_BE = BlockEntityType.Builder.of(TapBlockEntity::new, TAP).build(null);
    public static final BlockEntityType<ShakerBlockEntity> SHAKER_BE = BlockEntityType.Builder.of(ShakerBlockEntity::new, SHAKER).build(null);
    public static final BlockEntityType<PotionBottleBlockEntity> POTION_BOTTLE_BE = BlockEntityType.Builder.of(PotionBottleBlockEntity::new, POTION_BOTTLE).build(null);
    public static final BlockEntityType<IncenseBlockEntity> INCENSE_BE = BlockEntityType.Builder.of(IncenseBlockEntity::new,
            SAKURA_INCENSE, PINE_INCENSE, GINKGO_INCENSE, SPORE_INCENSE,
            CATNIP_INCENSE, SNOW_INCENSE, BUTTERFLY_INCENSE, FIREFLY_INCENSE
            ).build(null);
    public static final BlockEntityType<BarCabinetBlockEntity> BAR_CABINET_BE = BlockEntityType.Builder.of(BarCabinetBlockEntity::new,
            BAR_CABINET,
            GLASS_BAR_CABINET
    ).build(null);
    public static final BlockEntityType<SignatureCocktailBlockEntity> SIGNATURE_COCKTAIL_BE = BlockEntityType.Builder.of(SignatureCocktailBlockEntity::new,
            SIGNATURE_COCKTAIL
    ).build(null);
    public static final BlockEntityType<CellarCabinetBlockEntity> CELLAR_CABINET_BE = BlockEntityType.Builder.of(CellarCabinetBlockEntity::new,
            CELLAR_CABINET
    ).build(null);
    public static final BlockEntityType<TiltedRackBlockEntity> TILTED_RACK_BE = BlockEntityType.Builder.of(TiltedRackBlockEntity::new,
            TILTED_RACK
    ).build(null);
    public static final BlockEntityType<GlasswareHolderBlockEntity> GLASSWARE_HOLDER_BE = BlockEntityType.Builder.of(GlasswareHolderBlockEntity::new,
            GLASSWARE_HOLDER
    ).build(null);
    public static final BlockEntityType<CircularRackBlockEntity> CIRCULAR_RACK_BE = BlockEntityType.Builder.of(CircularRackBlockEntity::new,
            CIRCULAR_RACK
    ).build(null);
    public static final BlockEntityType<HolderBlockEntity> HOLDER_BE = BlockEntityType.Builder.of(HolderBlockEntity::new,
            HOLDER
    ).build(null);
    public static final BlockEntityType<DrinkBlockEntity> DRINK_BE = BlockEntityType.Builder.of(DrinkBlockEntity::new,
            WINE, CHAMPAGNE, VODKA, BRANDY, CARIGNAN,
            SAKURA_WINE, PLUM_WINE, WHISKEY, ICE_WINE,
            POLARIS_SWEET_WHITE, HONEY_WINE, RED_QUEEN, MINERS_STAR,
            RUM, RIESLING_DRY_WHITE, SUNSET_GLOW, MADAME_SHEXIANG,
            SWEET_BERRY_WINE, SHERRY, MOTHER_SNOW, LUMINOUS_BRIDE,
            GLOWFLOWER_BREW, SAUVIGNON_BLANC_DRY_WHITE, VINEGAR,
            WATERMELON_JUICE
    ).build(null);

    public static void registerBlocks() {
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "white_sofa"), WHITE_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "black_sofa"), BLACK_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "yellow_sofa"), YELLOW_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "red_sofa"), RED_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "blue_sofa"), BLUE_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "green_sofa"), GREEN_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "purple_sofa"), PURPLE_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "orange_sofa"), ORANGE_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "pink_sofa"), PINK_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "brown_sofa"), BROWN_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "gray_sofa"), GRAY_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "light_gray_sofa"), LIGHT_GRAY_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "cyan_sofa"), CYAN_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "magenta_sofa"), MAGENTA_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "lime_sofa"), LIME_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "light_blue_sofa"), LIGHT_BLUE_SOFA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "white_bar_stool"), WHITE_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "light_gray_bar_stool"), LIGHT_GRAY_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "black_bar_stool"), BLACK_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "brown_bar_stool"), BROWN_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "gray_bar_stool"), GRAY_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "cyan_bar_stool"), CYAN_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "magenta_bar_stool"), MAGENTA_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "lime_bar_stool"), LIME_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "light_blue_bar_stool"), LIGHT_BLUE_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "red_bar_stool"), RED_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "yellow_bar_stool"), YELLOW_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "pink_bar_stool"), PINK_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "purple_bar_stool"), PURPLE_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "orange_bar_stool"), ORANGE_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "blue_bar_stool"), BLUE_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "green_bar_stool"), GREEN_BAR_STOOL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "base_sandwich_board"), BASE_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "grass_sandwich_board"), GRASS_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "allium_sandwich_board"), ALLIUM_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "azure_bluet_sandwich_board"), AZURE_BLUET_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "cornflower_sandwich_board"), CORNFLOWER_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "orchid_sandwich_board"), ORCHID_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "peony_sandwich_board"), PEONY_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "pink_petals_sandwich_board"), PINK_PETALS_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "pitcher_plant_sandwich_board"), PITCHER_PLANT_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "poppy_sandwich_board"), POPPY_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sunflower_sandwich_board"), SUNFLOWER_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "torchflower_sandwich_board"), TORCHFLOWER_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "tulip_sandwich_board"), TULIP_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "wither_rose_sandwich_board"), WITHER_ROSE_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "eyeblossom_sandwich_board"), EYEBLOSSOM_SANDWICH_BOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_colorless"), STRING_LIGHTS_COLORLESS);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_white"), STRING_LIGHTS_WHITE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_light_gray"), STRING_LIGHTS_LIGHT_GRAY);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_gray"), STRING_LIGHTS_GRAY);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_black"), STRING_LIGHTS_BLACK);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_brown"), STRING_LIGHTS_BROWN);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_red"), STRING_LIGHTS_RED);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_orange"), STRING_LIGHTS_ORANGE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_yellow"), STRING_LIGHTS_YELLOW);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_lime"), STRING_LIGHTS_LIME);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_green"), STRING_LIGHTS_GREEN);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_cyan"), STRING_LIGHTS_CYAN);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_light_blue"), STRING_LIGHTS_LIGHT_BLUE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_blue"), STRING_LIGHTS_BLUE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_purple"), STRING_LIGHTS_PURPLE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_magenta"), STRING_LIGHTS_MAGENTA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "string_lights_pink"), STRING_LIGHTS_PINK);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "ysbb_painting"), YSBB_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "tartaric_acid_painting"), TARTARIC_ACID_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "cr019_painting"), CR019_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "unknown_painting"), UNKNOWN_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "master_marisa_painting"), MASTER_MARISA_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "son_of_man_painting"), SON_OF_MAN_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "david_painting"), DAVID_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "girl_with_pearl_earring_painting"), GIRL_WITH_PEARL_EARRING_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "starry_night_painting"), STARRY_NIGHT_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "van_gogh_self_portrait_painting"), VAN_GOGH_SELF_PORTRAIT_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "father_painting"), FATHER_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "great_wave_painting"), GREAT_WAVE_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "mona_lisa_painting"), MONA_LISA_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "mondrian_painting"), MONDRIAN_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "night_epiphany_painting"), NIGHT_EPIPHANY_PAINTING);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "pressing_tub"), PRESSING_TUB);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "empty_bottle"), EMPTY_BOTTLE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "empty_glassware"), EMPTY_GLASSWARE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "glassware_holder"), GLASSWARE_HOLDER);

        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "signature_cocktail"), SIGNATURE_COCKTAIL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "mystery_cocktail"), MYSTERY_COCKTAIL );
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "white_lady"), WHITE_LADY);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "emerald"), EMERALD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "brass_heart"), BRASS_HEART);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "godfather"), GODFATHER);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "grasshopper"), GRASSHOPPER);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "screwdriver"), SCREWDRIVER);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "mojito"), MOJITO);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "allium_garden"), ALLIUM_GARDEN);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "depth_charge"), DEPTH_CHARGE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "nether_special"), NETHER_SPECIAL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "bloody_mary"), BLOODY_MARY);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sculk_special"), SCULK_SPECIAL);

        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "water_bottle"), WATER_BOTTLE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "honey_bottle"), HONEY_BOTTLE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "dragon_breath_bottle"), DRAGON_BREATH_BOTTLE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "potion_bottle"), POTION_BOTTLE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "xp_bottle"), XP_BOTTLE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "table"), TABLE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "barrel"), BARREL);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "bar_cabinet"), BAR_CABINET);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "glass_bar_cabinet"), GLASS_BAR_CABINET);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "cellar_cabinet"), CELLAR_CABINET);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "tilted_rack"), TILTED_RACK);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "circular_rack"), CIRCULAR_RACK);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "holder"), HOLDER);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "shaker"), SHAKER);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "wild_grapevine"), WILD_GRAPEVINE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "wild_grapevine_plant"), WILD_GRAPEVINE_PLANT);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "trellis"), TRELLIS);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "grapevine_trellis"), GRAPEVINE_TRELLIS);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "ice_grapevine_trellis"), ICE_GRAPEVINE_TRELLIS);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "gold_grapevine_trellis"), GOLD_GRAPEVINE_TRELLIS);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "grape_crop"), GRAPE_CROP);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "ice_grape_crop"), ICE_GRAPE_CROP);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "gold_grape_crop"), GOLD_GRAPE_CROP);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "molotov"), MOLOTOV);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "tap"), TAP);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "bar_counter"), BAR_COUNTER);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "stepladder"), STEPLADDER);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "chalkboard"), CHALKBOARD);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "bell_pendant_lamp"), BELL_PENDANT_LAMP);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "yellow_pendant_lamp"), YELLOW_PENDANT_LAMP);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "blue_pendant_lamp"), BLUE_PENDANT_LAMP);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sakura_incense"), SAKURA_INCENSE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "pine_incense"), PINE_INCENSE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "ginkgo_incense"), GINKGO_INCENSE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "spore_incense"), SPORE_INCENSE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "catnip_incense"), CATNIP_INCENSE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "snow_incense"), SNOW_INCENSE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "butterfly_incense"), BUTTERFLY_INCENSE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "firefly_incense"), FIREFLY_INCENSE);

        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "wine"), WINE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "champagne"), CHAMPAGNE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "vodka"), VODKA);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "brandy"), BRANDY);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "carignan"), CARIGNAN);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sakura_wine"), SAKURA_WINE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "plum_wine"), PLUM_WINE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "whiskey"), WHISKEY);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "ice_wine"), ICE_WINE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "vinegar"), VINEGAR);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "polaris_sweet_white"), POLARIS_SWEET_WHITE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "honey_wine"), HONEY_WINE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "red_queen"), RED_QUEEN);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "miners_star"), MINERS_STAR);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "rum"), RUM);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "riesling_dry_white"), RIESLING_DRY_WHITE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sunset_glow"), SUNSET_GLOW);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "madame_shexiang"), MADAME_SHEXIANG);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sweet_berry_wine"), SWEET_BERRY_WINE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sherry"), SHERRY);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "mother_snow"), MOTHER_SNOW);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "luminous_bride"), LUMINOUS_BRIDE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "glowflower_brew"), GLOWFLOWER_BREW);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sauvignon_blanc_dry_white"), SAUVIGNON_BLANC_DRY_WHITE);
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "watermelon_juice"), WATERMELON_JUICE);

        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "sandwich_board"), SANDWICH_BOARD_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "bar_stool"), BAR_STOOL_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "chalkboard"), CHALKBOARD_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "pressing_tub"), PRESSING_TUB_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "barrel"), BARREL_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "tap"), TAP_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "shaker"), SHAKER_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "potion_bottle"), POTION_BOTTLE_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "incense"), INCENSE_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "bar_cabinet"), BAR_CABINET_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "signature_cocktail"), SIGNATURE_COCKTAIL_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "cellar_cabinet"), CELLAR_CABINET_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "tilted_rack"), TILTED_RACK_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "glassware_holder"), GLASSWARE_HOLDER_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "circular_rack"), CIRCULAR_RACK_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "holder"), HOLDER_BE);
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "drink"), DRINK_BE);
    }

}
