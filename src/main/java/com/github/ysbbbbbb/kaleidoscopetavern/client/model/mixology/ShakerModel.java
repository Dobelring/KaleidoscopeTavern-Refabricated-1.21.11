package com.github.ysbbbbbb.kaleidoscopetavern.client.model.mixology;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.client.animation.ShakerAnimation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import org.jspecify.annotations.NonNull;

@Environment(EnvType.CLIENT)
public class ShakerModel extends Model<ShakerModel.State> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "shaker"), "main");

    private final KeyframeAnimation putAnimation;

    public ShakerModel(ModelPart root) {
        super(root, RenderTypes::entityCutout);
        ModelPart rootPart = root.getChild("root");
        ModelPart lid = rootPart.getChild("bone2");

        this.putAnimation = ShakerAnimation.PUT.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create()
                        .texOffs(23, 28).addBox(-3.0F, 0.75F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(2, 2).addBox(-3.5F, -6.25F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 19.25F, 0.0F));

        root.addOrReplaceChild("bone2", CubeListBuilder.create()
                        .texOffs(3, 36).addBox(-1.5F, -3.8333F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(2, 2).addBox(-3.5F, 0.1667F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 46).addBox(-3.0F, -1.8333F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -7.4167F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NonNull State state) {
        super.setupAnim(state);
        this.putAnimation.apply(state.animationState, state.animationAge);
    }

    @Environment(EnvType.CLIENT)
    public record State(float animationAge, AnimationState animationState) {
    }
}
