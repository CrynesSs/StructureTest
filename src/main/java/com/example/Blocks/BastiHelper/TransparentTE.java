package com.example.Blocks.BastiHelper;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TransparentTE extends TileEntity {
    private float transparency = 0.5f;

    public TransparentTE(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }

    public float getTransparency() {
        return transparency;
    }
}
