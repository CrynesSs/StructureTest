package com.example.Blocks.StructureTest.TestStructure;

import com.example.Core.CrystalMod;
import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestStructurePieces {
    private static final ResourceLocation ENTRANCE = new ResourceLocation(CrystalMod.MODID + ":tower_entrance");
    private static final ResourceLocation ROOF = new ResourceLocation(CrystalMod.MODID + ":tower_roof");
    private static final ResourceLocation FOUNDATION = new ResourceLocation(CrystalMod.MODID + ":tower_foundation");

    private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder().put(ENTRANCE, new BlockPos(0, 1, 0))
            .put(ROOF, new BlockPos(0, 33, 0)).put(FOUNDATION, new BlockPos(0, -12, 0)).build();

    public static IStructurePieceType TEST_TYPE = TestStructurePieces.Piece::new;


    public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random) {
        pieceList.add(new Piece(templateManager, ENTRANCE, pos, rotation));
        pieceList.add(new Piece(templateManager, ROOF, pos, rotation));
        pieceList.add(new Piece(templateManager, FOUNDATION, pos, rotation));
    }

    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation resourceLocation;
        private Rotation rotation;

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(TEST_TYPE, nbt);

        }

        public Piece(TemplateManager templateManager, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotation) {
            super(TEST_TYPE, 0);
            this.resourceLocation = resourceLocationIn;
            BlockPos blockpos = TestStructurePieces.OFFSET.get(resourceLocation);
            this.templatePosition = pos.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            this.rotation = rotation;
            this.setupPiece(templateManager);
        }
        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.getOrCreate(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }


        @Override
        protected void handleDataMarker(String s, BlockPos blockPos, IServerWorld iServerWorld, Random random, MutableBoundingBox mutableBoundingBox) {

        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT nbt) {
            super.addAdditionalSaveData(nbt);
            nbt.putString("Template", this.resourceLocation.toString());
            nbt.putString("Rot", this.rotation.name());
        }

        @Override
        public boolean postProcess(ISeedReader iSeedReader, StructureManager structureManager, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            BlockPos blockpos = TestStructurePieces.OFFSET.get(this.resourceLocation);
            this.templatePosition.offset(Template.calculateRelativePosition(placementsettings, new BlockPos(-blockpos.getX(), 0, -blockpos.getZ())));
            return super.postProcess(iSeedReader, structureManager, generator, random, boundingBox, chunkPos, pos);
        }
    }
}
