package com.example.Blocks.BastiHelper;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.EmptyModelData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CombinedModel implements IBakedModel {

    private final List<Pair<Block, Float>> BLOCKLIST;

    public CombinedModel(List<Pair<Block, Float>> blockList) {
        BLOCKLIST = blockList;
    }


    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState p_200117_1_, @Nullable Direction p_200117_2_, Random random) {
        return BLOCKLIST.parallelStream()
                .map(pair -> TransparentBlockModel.MODEL_CACHE.
                        computeIfAbsent(pair,
                                (blockFloatPair) -> new TransparentBlockModel(
                                        Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS)
                                                .apply(
                                                        new ResourceLocation(pair.getFirst().getRegistryName().getNamespace(),
                                                                "block/" + pair.getFirst().getRegistryName().getPath())),
                                        pair.getSecond())).getQuads(null, null, random, EmptyModelData.INSTANCE))
                .reduce(new ArrayList<>(), (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return null;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return null;
    }
}
