package com.example.Blocks.MovingCrystal;

import com.example.Util.Quads.StaticModels;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class MovingCrystalRenderer extends TileEntityRenderer<MovingCrystalBE> {
    public MovingCrystalRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(MovingCrystalBE tile, float p_225616_2_, MatrixStack mat, IRenderTypeBuffer buffer, int light, int otherlight) {
        double[] offset = tile.getOffset();

        mat.translate(offset[0], offset[1], offset[2]);
        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(
                tile.getLevel(),
                StaticModels.FLOATING_CRYSTAL_MODEL,
                tile.getBlockState(),
                tile.getBlockPos(),
                mat,
                buffer.getBuffer(Atlases.solidBlockSheet()),
                false,
                new Random(),
                42,
                light,
                tile.getModelData());
    }
}
