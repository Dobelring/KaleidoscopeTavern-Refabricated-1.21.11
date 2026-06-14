package com.github.ysbbbbbb.kaleidoscopetavern.effect;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class LongReachEffect extends BaseEffect {
    public LongReachEffect(int color) {
        super(color);
        this.addAttributeModifier(
                ReachEntityAttributes.REACH,
                "7a38abe7-6c6d-4894-8391-84417cb8368a",
                3.0,
                AttributeModifier.Operation.ADDITION
        );
        this.addAttributeModifier(
                ReachEntityAttributes.ATTACK_RANGE,
                "995f2ddb-6f4c-48c2-b9a0-68fe5b4277d7",
                3.0,
                AttributeModifier.Operation.ADDITION
        );
    }
}
