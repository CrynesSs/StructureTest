package com.example.Blocks.StructureTest.TestStructure;

import com.example.Core.CrystalMod;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestStructurePieces {

    private static final ResourceLocation HOUSE_LOCATION = new ResourceLocation(CrystalMod.MOD_ID, "test/house");

    public static void addPieces(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, Random random) {
        pieces.add(new Piece(manager, HOUSE_LOCATION, rotation, pos));
    }


    public static final IStructurePieceType TEST_PIECE_TYPE = setPieceId(Piece::new, "TpPT");

    public static IStructurePieceType setPieceId(IStructurePieceType pieceType, String id) {
        return Registry.register(Registry.STRUCTURE_PIECE, id.toLowerCase(Locale.ROOT), pieceType);
    }

    public static class Piece extends StructurePiece {
        private final ResourceLocation PIECE_LOCATION;
        private final Rotation ROTATION;
        private BlockPos POS;
        protected Template template;
        protected PlacementSettings placeSettings;

        protected Piece(TemplateManager manager, ResourceLocation piece_location, Rotation rotation, BlockPos pos) {
            super(TEST_PIECE_TYPE, 0);
            PIECE_LOCATION = piece_location;
            ROTATION = rotation;
            POS = pos;
            this.loadTemplate(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(TEST_PIECE_TYPE, nbt);
            this.PIECE_LOCATION = new ResourceLocation(nbt.getString("Template"));
            ROTATION = Rotation.valueOf(nbt.getString("Rot"));
            this.loadTemplate(manager);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT nbt) {
            nbt.putInt("TPX", this.POS.getX());
            nbt.putInt("TPY", this.POS.getY());
            nbt.putInt("TPZ", this.POS.getZ());
            nbt.putString("Template", this.PIECE_LOCATION.toString());
            nbt.putString("Rot", ROTATION.name());
        }
        private static final Logger LOGGER = LogManager.getLogger();
        @Override
        public boolean postProcess(ISeedReader p_230383_1_, StructureManager p_230383_2_, ChunkGenerator p_230383_3_, Random p_230383_4_, MutableBoundingBox p_230383_5_, ChunkPos p_230383_6_, BlockPos p_230383_7_) {
            this.placeSettings.setBoundingBox(p_230383_5_);
            this.boundingBox = this.template.getBoundingBox(this.placeSettings, this.POS);
            if (this.template.placeInWorld(p_230383_1_, this.POS, p_230383_7_, this.placeSettings, p_230383_4_, 2)) {
                List<Template.BlockInfo> lvt_8_1_ = this.template.filterBlocks(this.POS, this.placeSettings, Blocks.STRUCTURE_BLOCK);

                for (Template.BlockInfo lvt_10_1_ : lvt_8_1_) {
                    if (lvt_10_1_.nbt != null) {
                        StructureMode lvt_11_1_ = StructureMode.valueOf(lvt_10_1_.nbt.getString("mode"));
                        if (lvt_11_1_ == StructureMode.DATA) {
                            //this.handleDataMarker(lvt_10_1_.nbt.getString("metadata"), lvt_10_1_.pos, p_230383_1_, p_230383_4_, p_230383_5_);
                        }
                    }
                }

                List<Template.BlockInfo> lvt_9_1_ = this.template.filterBlocks(this.POS, this.placeSettings, Blocks.JIGSAW);

                for (Template.BlockInfo lvt_11_2_ : lvt_9_1_) {
                    if (lvt_11_2_.nbt != null) {
                        String lvt_12_1_ = lvt_11_2_.nbt.getString("final_state");
                        BlockStateParser lvt_13_1_ = new BlockStateParser(new StringReader(lvt_12_1_), false);
                        BlockState lvt_14_1_ = Blocks.AIR.defaultBlockState();

                        try {
                            lvt_13_1_.parse(true);
                            BlockState lvt_15_1_ = lvt_13_1_.getState();
                            if (lvt_15_1_ != null) {
                                lvt_14_1_ = lvt_15_1_;
                            } else {
                                LOGGER.error("Error while parsing blockstate {} in jigsaw block @ {}", lvt_12_1_, lvt_11_2_.pos);
                            }
                        } catch (CommandSyntaxException var16) {
                            LOGGER.error("Error while parsing blockstate {} in jigsaw block @ {}", lvt_12_1_, lvt_11_2_.pos);
                        }

                        p_230383_1_.setBlock(lvt_11_2_.pos, lvt_14_1_, 3);
                    }
                }
            }
            return true;
        }

        private void loadTemplate(TemplateManager manager) {
            Template template = manager.getOrCreate(this.PIECE_LOCATION);
            PlacementSettings settings = (new PlacementSettings()).setRotation(ROTATION).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, POS, settings);
        }

        protected void setup(Template template, BlockPos pos, PlacementSettings placementSettings) {
            this.template = template;
            this.setOrientation(Direction.NORTH);
            this.POS = pos;
            this.placeSettings = placementSettings;
            this.boundingBox = template.getBoundingBox(placementSettings, pos);
        }
    }


}
