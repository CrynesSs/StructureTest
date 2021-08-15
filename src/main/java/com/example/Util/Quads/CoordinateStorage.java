package com.example.Util.Quads;

import net.minecraft.util.math.vector.Vector3d;

public class CoordinateStorage {
    public static final Vector3d[] DOUBLE_PYRAMID_POINTS = new Vector3d[]{
            new Vector3d(1d, .5d, 1d),
            new Vector3d(0d, .5d, 1d),
            new Vector3d(0d, .5d, 0d),
            new Vector3d(1d, .5d, 0d),
            new Vector3d(0.5d, 0.0d, 0.5d),
            new Vector3d(0.5d, 1.0d, 0.5d)
    };
    public static final Vector3d[] DOUBLE_PYRAMID_SLIM_POINTS = new Vector3d[]{
            new Vector3d(.75d, .5d, .75d),
            new Vector3d(0.25d, .5d, .75d),
            new Vector3d(0.25d, .5d, 0.25d),
            new Vector3d(.75d, .5d, 0.25d),
            new Vector3d(0.5d, 0.0d, 0.5d),
            new Vector3d(0.5d, 1.0d, 0.5d)
    };
    public static final Vector3d[] BLOCK = new Vector3d[]{
            new Vector3d(0,0,0),
            new Vector3d(1,0,0),
            new Vector3d(1,0,1),
            new Vector3d(0,0,1),
            new Vector3d(0,1,0),
            new Vector3d(1,1,0),
            new Vector3d(1,1,1),
            new Vector3d(0,1,1)
    };
}
