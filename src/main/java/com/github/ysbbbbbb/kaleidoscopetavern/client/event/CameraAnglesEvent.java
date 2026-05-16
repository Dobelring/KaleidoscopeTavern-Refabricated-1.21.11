package com.github.ysbbbbbb.kaleidoscopetavern.client.event;

import com.github.ysbbbbbb.kaleidoscopetavern.api.event.ViewportEvent;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

@Environment(EnvType.CLIENT)
public class CameraAnglesEvent {
    public static void onCameraAngles(ViewportEvent.ComputeCameraAngles event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        if (player.hasEffect(ModEffects.SLIGHTLY_TIPSY.get())) {
            double t = player.tickCount + event.getPartialTick();

            // 组合三个不同周期的波：
            // 基础慢速大摇晃 (主导方向)
            double wave1 = Math.sin(t / 19.0) * 0.6;
            // 中速的补充摇晃 (打破单调)
            double wave2 = Math.cos(t / 13.0) * 0.3;
            // 稍微快一点的细微抖动 (模拟重心的偶尔失衡)
            double wave3 = Math.sin(t / 9.0) * 0.1;

            // 叠加结果，总体范围大概在 -1.0 到 1.0 之间
            double value = wave1 + wave2 + wave3;

            event.setRoll(event.getRoll() + (float) value);
        }
    }
}
