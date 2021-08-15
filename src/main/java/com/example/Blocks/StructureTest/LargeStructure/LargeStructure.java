package com.example.Blocks.StructureTest.LargeStructure;

import com.example.Blocks.StructureTest.LargeStructure.LargeStructurePieces.MainPieces;
import com.mojang.serialization.Codec;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class LargeStructure extends Structure<NoFeatureConfig> {
    public LargeStructure(Codec<NoFeatureConfig> p_i231997_1_) {
        super(p_i231997_1_);
    }

    @Override
    @Nonnull
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    @Nonnull
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    @Override
    public Structure<?> getStructure() {
        return this;
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return super.getDefaultSpawnList();
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
        return super.getDefaultCreatureSpawnList();
    }

    @Override
    public boolean getDefaultRestrictsSpawnsToInside() {
        return super.getDefaultRestrictsSpawnsToInside();
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long random) {
            super(structure, chunkX, chunkZ, boundingBox, references, random);
        }


        @Override
        protected void calculateBoundingBox() {
            this.boundingBox = MutableBoundingBox.getUnknownBox();
            for (StructurePiece structurepiece : this.pieces) {
                this.boundingBox.expand(structurepiece.getBoundingBox());
            }
        }

        @Override
        public void generatePieces(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {
            MainPieces.addPieces(manager, new BlockPos(chunkX << 4, 90, chunkZ << 4), Rotation.getRandom(random), pieces, random);
        }
    }
    public static class Piece extends StructurePiece {
        protected Piece(IStructurePieceType p_i51342_1_, int p_i51342_2_) {
            super(p_i51342_1_, p_i51342_2_);
        }

        public Piece(IStructurePieceType p_i51343_1_, CompoundNBT p_i51343_2_) {
            super(p_i51343_1_, p_i51343_2_);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT p_143011_1_) {

        }

        @Override
        public boolean postProcess(ISeedReader p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
            return false;
        }
    }
}
