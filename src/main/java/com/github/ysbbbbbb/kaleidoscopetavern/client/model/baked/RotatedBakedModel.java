package com.github.ysbbbbbb.kaleidoscopetavern.client.model.baked;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public final class RotatedBakedModel implements BakedModel {
    private static final int VERTEX_STRIDE = 8;
    private static final int VERTEX_COUNT = 4;
    private static final double SEGMENT_RADIANS = Math.PI * 2.0D / 16.0D;

    private final BakedModel parent;
    private final double sin;
    private final double cos;
    private final Map<Direction, List<BakedQuad>> culledQuads = new EnumMap<>(Direction.class);
    private List<BakedQuad> unculledQuads;

    public RotatedBakedModel(BakedModel parent, int rotation) {
        this.parent = parent;
        double radians = rotation * SEGMENT_RADIANS;
        this.sin = Math.sin(radians);
        this.cos = Math.cos(radians);
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction direction, @NotNull RandomSource randomSource) {
        if (blockState == null) {
            return this.parent.getQuads(null, direction, randomSource);
        }

        if (direction != null) {
            return Collections.emptyList();
        }

        if (this.unculledQuads == null) {
            this.unculledQuads = bakeUnculledQuads(blockState, randomSource);
        }
        return this.unculledQuads;
    }

    private List<BakedQuad> bakeUnculledQuads(BlockState blockState, RandomSource randomSource) {
        List<BakedQuad> quads = new ArrayList<>(rotateQuads(this.parent.getQuads(blockState, null, randomSource)));

        for (Direction face : Direction.values()) {
            quads.addAll(this.culledQuads.computeIfAbsent(face, direction -> rotateQuads(this.parent.getQuads(blockState, direction, randomSource))));
        }

        return Collections.unmodifiableList(quads);
    }

    private List<BakedQuad> rotateQuads(List<BakedQuad> source) {
        if (source.isEmpty()) {
            return source;
        }

        List<BakedQuad> rotated = new ArrayList<>(source.size());
        for (BakedQuad quad : source) {
            rotated.add(rotateQuad(quad));
        }
        return Collections.unmodifiableList(rotated);
    }

    private BakedQuad rotateQuad(BakedQuad quad) {
        int[] vertices = quad.getVertices().clone();

        for (int vertex = 0; vertex < VERTEX_COUNT; vertex++) {
            int offset = vertex * VERTEX_STRIDE;
            float x = Float.intBitsToFloat(vertices[offset]);
            float z = Float.intBitsToFloat(vertices[offset + 2]);
            double centeredX = x - 0.5D;
            double centeredZ = z - 0.5D;

            vertices[offset] = Float.floatToRawIntBits((float) (centeredX * this.cos - centeredZ * this.sin + 0.5D));
            vertices[offset + 2] = Float.floatToRawIntBits((float) (centeredX * this.sin + centeredZ * this.cos + 0.5D));
        }

        Direction rotatedDirection = FaceBakery.calculateFacing(vertices);
        return new BakedQuad(vertices, quad.getTintIndex(), rotatedDirection, quad.getSprite(), quad.isShade());
    }

    @Override
    public boolean useAmbientOcclusion() {
        return this.parent.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return this.parent.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return this.parent.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return this.parent.isCustomRenderer();
    }

    @Override
    public @NotNull TextureAtlasSprite getParticleIcon() {
        return this.parent.getParticleIcon();
    }

    @Override
    public @NotNull ItemTransforms getTransforms() {
        return this.parent.getTransforms();
    }

    @Override
    public @NotNull ItemOverrides getOverrides() {
        return this.parent.getOverrides();
    }
}
