package com.example.Blocks.StructureTest.Constructors;

public class Node {
    private int x;
    private int z;

    public Node(int x, int z){
        this.x = x;
        this.z = z;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && z == node.z;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }
}
