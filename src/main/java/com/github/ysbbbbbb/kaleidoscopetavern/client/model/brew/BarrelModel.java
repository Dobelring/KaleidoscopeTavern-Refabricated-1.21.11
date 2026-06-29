package com.github.ysbbbbbb.kaleidoscopetavern.client.model.brew;

import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

@Environment(EnvType.CLIENT)
public class BarrelModel extends Model<BarrelModel.State> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(KaleidoscopeTavern.MOD_ID, "barrel"), "main");
    private final ModelPart close;
    private final ModelPart open;

    public BarrelModel(ModelPart root) {
        super(root, RenderTypes::entityCutout);
        ModelPart modelRoot = root.getChild("root");
        this.close = modelRoot.getChild("close");
        this.open = modelRoot.getChild("open");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(8.0F, 9.0F, -9.0F));
        root.addOrReplaceChild("close", CubeListBuilder.create()
                .texOffs(102, 113).addBox(-16.0F, -33.0F, 1.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition open = root.addOrReplaceChild("open", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -33.0F, -1.0F, 1.309F, 0.0F, 0.0F));
        open.addOrReplaceChild("open_r1", CubeListBuilder.create()
                .texOffs(106, 114).mirror().addBox(0.0F, -4.0F, -12.0F, 0.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false),
                PartPose.offsetAndRotation(7.0F, 9.7654F, 5.0978F, -0.6109F, 0.0F, 0.0F));
        open.addOrReplaceChild("open_r2", CubeListBuilder.create()
                .texOffs(102, 113).addBox(-8.0F, -2.4F, -2.8F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 17.0F, 8.0F, 0.5672F, 0.0F, 0.0F));

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(28, 136).addBox(6.0F, 7.0F, -10.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 136).addBox(-26.0F, 7.0F, -10.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(174, 118).addBox(-22.0F, 7.0F, -8.0F, 28.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(174, 118).addBox(-22.0F, 7.0F, 22.0F, 28.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(28, 136).addBox(6.0F, 7.0F, 22.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 136).addBox(-26.0F, 7.0F, 22.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-28.0F, -33.0F, -15.0F, 40.0F, 40.0F, 48.0F, new CubeDeformation(0.0F))
                .texOffs(0, 160).addBox(-16.0F, -33.0F, 1.0F, 16.0F, 21.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 144).addBox(0.0F, -33.0F, 1.0F, 0.0F, 24.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 144).addBox(-16.0F, -33.0F, 1.0F, 0.0F, 21.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(32, 160).addBox(-16.0F, -12.0F, 1.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 160).addBox(-16.0F, -33.0F, 17.0F, 16.0F, 21.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(State object) {
        super.setupAnim(object);
        boolean openState = object.open();
        this.open.visible = openState;
        this.close.visible = !openState;
    }

    @Environment(EnvType.CLIENT)
    public record State(boolean open) {
    }
}
