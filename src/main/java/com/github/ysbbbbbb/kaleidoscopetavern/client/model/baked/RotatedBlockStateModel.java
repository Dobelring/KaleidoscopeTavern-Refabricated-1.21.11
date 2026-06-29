package com.github.ysbbbbbb.kaleidoscopetavern.client.model.baked;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.wrapper.WrapperBlockStateModel;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.client.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Environment(EnvType.CLIENT)
public final class RotatedBlockStateModel extends WrapperBlockStateModel {
    private static final double SEGMENT_RADIANS = Math.PI * 2.0D / 16.0D;
    private final int rotation;
    private final double sin;
    private final double cos;
    private final Matrix4f normalTransform;

    public RotatedBlockStateModel(BlockStateModel wrapped, int rotation) {
        super(wrapped);
        this.rotation = rotation;
        double radians = rotation * SEGMENT_RADIANS;
        this.sin = Math.sin(radians);
        this.cos = Math.cos(radians);
        this.normalTransform = new Matrix4f().rotateY((float) radians);
    }

    @Override
    public void collectParts(@NonNull RandomSource randomSource, @NonNull List<BlockStateModelPart> parts) {
        List<BlockStateModelPart> wrappedParts = new ArrayList<>();
        this.wrapped.collectParts(randomSource, wrappedParts);
        for (BlockStateModelPart part : wrappedParts) {
            parts.add(new RotatedPart(part));
        }
    }

    @Override
    public void emitQuads(@NonNull QuadEmitter emitter, @NonNull BlockAndTintGetter level, @NonNull BlockPos pos,
                          @NonNull BlockState state, @NonNull RandomSource random,
                          @NonNull Predicate<@Nullable Direction> cullTest) {
        emitter.pushTransform(this::rotateQuad);
        try {
            this.wrapped.emitQuads(emitter, level, pos, state, random, direction -> false);
        } finally {
            emitter.popTransform();
        }
    }

    @Override
    @Nullable
    public Object createGeometryKey(@NonNull BlockAndTintGetter level, @NonNull BlockPos pos,
                                    @NonNull BlockState state, @NonNull RandomSource random) {
        Object wrappedKey = this.wrapped.createGeometryKey(level, pos, state, random);
        return wrappedKey == null ? null : new GeometryKey(wrappedKey, this.rotation);
    }

    private BakedQuad rotateQuad(BakedQuad quad) {
        Direction rotatedDirection = Direction.rotate(this.normalTransform, quad.direction());
        return new BakedQuad(
                rotatePosition(quad.position0()),
                rotatePosition(quad.position1()),
                rotatePosition(quad.position2()),
                rotatePosition(quad.position3()),
                quad.packedUV0(),
                quad.packedUV1(),
                quad.packedUV2(),
                quad.packedUV3(),
                rotatedDirection,
                quad.materialInfo()
        );
    }

    private boolean rotateQuad(MutableQuadView quad) {
        for (int vertex = 0; vertex < BakedQuad.VERTEX_COUNT; vertex++) {
            double centeredX = quad.x(vertex) - 0.5D;
            double centeredZ = quad.z(vertex) - 0.5D;
            quad.pos(vertex,
                    (float) (centeredX * this.cos - centeredZ * this.sin + 0.5D),
                    quad.y(vertex),
                    (float) (centeredX * this.sin + centeredZ * this.cos + 0.5D));

            if (quad.hasNormal(vertex)) {
                float normalX = quad.normalX(vertex);
                float normalZ = quad.normalZ(vertex);
                quad.normal(vertex,
                        (float) (normalX * this.cos - normalZ * this.sin),
                        quad.normalY(vertex),
                        (float) (normalX * this.sin + normalZ * this.cos));
            }
        }

        quad.cullFace(null);
        quad.nominalFace(null);
        return true;
    }

    private Vector3fc rotatePosition(Vector3fc source) {
        double centeredX = source.x() - 0.5D;
        double centeredZ = source.z() - 0.5D;
        return new Vector3f(
                (float) (centeredX * this.cos - centeredZ * this.sin + 0.5D),
                source.y(),
                (float) (centeredX * this.sin + centeredZ * this.cos + 0.5D)
        );
    }

    private record GeometryKey(Object wrappedKey, int rotation) {
    }

    private final class RotatedPart implements BlockStateModelPart {
        private final BlockStateModelPart wrappedPart;
        private @Nullable List<BakedQuad> quads;

        private RotatedPart(BlockStateModelPart wrappedPart) {
            this.wrappedPart = wrappedPart;
        }

        @Override
        public @NonNull List<BakedQuad> getQuads(@Nullable Direction direction) {
            if (direction != null) {
                return List.of();
            }
            if (this.quads == null) {
                this.quads = bakeQuads();
            }
            return this.quads;
        }

        private List<BakedQuad> bakeQuads() {
            List<BakedQuad> rotated = new ArrayList<>();
            addRotated(rotated, this.wrappedPart.getQuads(null));
            for (Direction direction : Direction.values()) {
                addRotated(rotated, this.wrappedPart.getQuads(direction));
            }
            return List.copyOf(rotated);
        }

        private void addRotated(List<BakedQuad> output, List<BakedQuad> source) {
            for (BakedQuad quad : source) {
                output.add(rotateQuad(quad));
            }
        }

        @Override
        public boolean useAmbientOcclusion() {
            return this.wrappedPart.useAmbientOcclusion();
        }

        @Override
        public Material.@NonNull Baked particleMaterial() {
            return this.wrappedPart.particleMaterial();
        }

        @Override
        public int materialFlags() {
            return this.wrappedPart.materialFlags();
        }
    }
}
