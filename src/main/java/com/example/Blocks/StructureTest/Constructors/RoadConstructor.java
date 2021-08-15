package com.example.Blocks.StructureTest.Constructors;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoadConstructor {
    private final int sizeX; //This size is in Structures -> 17/17 is one Structure by Edge
    private final int sizeZ;
    private final ResourceLocation streetLocation;

    private final List<StreetNode> streetNodes = new ArrayList<>();

    public RoadConstructor(int sizeX, int sizeZ, ResourceLocation streetLocation) {
        this.sizeX = sizeX;
        this.sizeZ = sizeZ;
        this.streetLocation = streetLocation;
    }

    public void generateRoad() {
        HashMap<Node,List<Node>> roadConnections;

    }
}
