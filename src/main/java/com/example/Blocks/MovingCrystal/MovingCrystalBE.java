package com.example.Blocks.MovingCrystal;

import com.example.Util.Init.BlockEntityInit;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;

import javax.annotation.Nonnull;

public class MovingCrystalBE extends TileEntity implements ITickableTileEntity {
    public static ModelProperty<int[]> ROT = new ModelProperty<>();
    private double x;
    private double y;
    private double z;
    private int xRot = 0;//[0,180]
    private int yRot = 0;//[0,180]
    private int zRot = 0;//[0,180]
    private final double r = 0.5d;
    private double eulerPhi = 0;
    private double eulerTheta = Math.PI / 2;
    private double velocity;
    private int tick = 0;


    public MovingCrystalBE() {
        super(BlockEntityInit.MOVING_CRYSTAL.get());
    }

    public double[] getOffset() {
        return new double[]{x, y, z};
    }

    public int[] getRot() {
        return new int[]{xRot, yRot, zRot};
    }

    @Override
    public void tick() {
        tick++;
        if (tick == 360) {
            tick = 0;
        }
        //xRot = tick;
        //yRot = tick;
        eulerPhi = (Math.PI * 2 * tick) / 360;
        x = Math.sin(eulerTheta) * Math.cos(eulerPhi) * r;
        y = Math.sin(eulerTheta) * Math.sin(eulerPhi) * r;
        z = Math.cos(eulerTheta) * r;
    }

    @Nonnull
    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder().withInitial(ROT, getRot()).build();
    }
}
