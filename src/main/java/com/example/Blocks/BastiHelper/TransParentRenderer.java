package com.example.Blocks.BastiHelper;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class TransParentRenderer extends TileEntityRenderer<TransparentTE> {

    public TransParentRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(TransparentTE tile, float p_225616_2_, MatrixStack stack, IRenderTypeBuffer buffer, int light, int otherlight) {
        IVertexBuilder builder = buffer.getBuffer(RenderType.translucent());
        BlockState state = tile.getBlockState();
        IBakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getBlockModel(tile.getBlockState());
        for (Direction d : Direction.values()) {
            if (d == Direction.NORTH) {
                for (BakedQuad quad : model.getQuads(state, null, new Random(), EmptyModelData.INSTANCE)) {
                    builder.addVertexData(stack.last(), quad, 1, 1, 1, 0.75f, light, light, true);
                }
            }
            for (BakedQuad quad : model.getQuads(state, d, new Random(), EmptyModelData.INSTANCE)) {
                builder.addVertexData(stack.last(), quad, 1, 1, 1, 0.75f, light, light, true);
            }
        }

    }
}
