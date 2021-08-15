package com.example.Blocks.StructureTest.Constructors;

public class StreetNode extends Node {
    public enum EStreetType {
        STRAIGHT,
        CORNER,
        T_SECTION,
        CROSS

    }
    private final EStreetType TYPE;
    public StreetNode(int x, int z, EStreetType type) {
        super(x, z);
        TYPE = type;
    }
    public EStreetType getTYPE() {
        return TYPE;
    }

}
