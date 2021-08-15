package com.example.Blocks.MovingCrystal;

import com.example.Blocks.FloatingCrystal.FloatingCrystalBE;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class MovingCrystalBlock extends Block {
    public MovingCrystalBlock() {
        super(AbstractBlock.Properties.of(Material.METAL).noOcclusion().dynamicShape());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MovingCrystalBE();
    }
}
