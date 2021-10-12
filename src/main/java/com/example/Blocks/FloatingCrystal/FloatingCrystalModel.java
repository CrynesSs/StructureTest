package com.example.Blocks.FloatingCrystal;

import com.example.Blocks.MovingCrystal.MovingCrystalBE;
import com.example.Core.CrystalMod;
import com.example.Util.Quads.CoordinateStorage;
import com.example.Util.Quads.QuadCreator;
import com.example.Util.Quads.QuadHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FloatingCrystalModel implements IDynamicBakedModel {

    public FloatingCrystalModel() {

    }

    public static final ResourceLocation TEXTURE = new ResourceLocation(CrystalMod.MODID, "block/floating_crystal");

    @Override
    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data) {
        return getParticleIcon();
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        List<BakedQuad> quads = new ArrayList<>();
        Vector3d[] cs = CoordinateStorage.DOUBLE_PYRAMID_SLIM_POINTS;
        IBakedModel model = Minecraft.getInstance().getBlockRenderer().getBlockModel(Blocks.ACACIA_STAIRS.defaultBlockState());

        if (side == null) {
            for (int i = 0; i < 4; ++i) {
                quads.add(QuadCreator.createTriangle(cs[4], cs[i], cs[(i + 1) % 4], getParticleIcon(), null));
                quads.add(QuadCreator.createTriangle(cs[5], cs[(i + 1) % 4], cs[i], getParticleIcon(), null));
            }
        }
        if (extraData.hasProperty(MovingCrystalBE.ROT)) {
            int[] rot = extraData.getData(MovingCrystalBE.ROT);
            if (rot != null) {
                quads = QuadHelper.rotateModel(quads, rot[0], rot[1], rot[2]);
            }
        }
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
    @Nonnull
    public TextureAtlasSprite getParticleIcon() {
        return Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(TEXTURE);
    }

    @Override
    @Nonnull
    public ItemOverrideList getOverrides() {
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(AtlasTexture.LOCATION_BLOCKS).apply(TEXTURE);

        return ItemOverrideList.EMPTY;
    }
}
