package com.example.Blocks.StructureTest.TestStructure;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class TestStructure extends Structure<NoFeatureConfig> {
    public TestStructure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }
    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return null;
    }



}
