package com.example.Blocks.BastiHelper;

import com.example.Util.Quads.CoordinateStorage;
import com.example.Util.Quads.QuadCreator;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TransparentBlockModel implements IBakedModel {

    private final TextureAtlasSprite sprite;
    private final float transparency;
    public static final HashMap<Pair<Block, Float>, TransparentBlockModel> MODEL_CACHE = new HashMap<>();

    public TransparentBlockModel(TextureAtlasSprite sprite, float transparency) {
        this.sprite = sprite;
        this.transparency = transparency;
    }

    @Override
    @Nonnull
    public List<BakedQuad> getQuads(@Nullable BlockState p_200117_1_, @Nullable Direction p_200117_2_, Random p_200117_3_) {
        List<BakedQuad> quads = new ArrayList<>();
        Vector3d[] cs = CoordinateStorage.BLOCK;
        quads.add(QuadCreator.createQuadTransparent(cs[0], cs[1], cs[2], cs[3], sprite, null, transparency));
        quads.add(QuadCreator.createQuadTransparent(cs[7], cs[6], cs[5], cs[4], sprite, null, transparency));
        quads.add(QuadCreator.createQuadTransparent(cs[4], cs[5], cs[1], cs[0], sprite, null, transparency));
        quads.add(QuadCreator.createQuadTransparent(cs[6], cs[7], cs[3], cs[2], sprite, null, transparency));
        quads.add(QuadCreator.createQuadTransparent(cs[5], cs[6], cs[2], cs[1], sprite, null, transparency));
        quads.add(QuadCreator.createQuadTransparent(cs[0], cs[3], cs[7], cs[4], sprite, null, transparency));
        return quads;
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
        return sprite;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    public float getTransparency() {
        return transparency;
    }
}
