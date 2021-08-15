package com.example.Blocks.StructureTest.StructureProcessors;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.template.*;

public class Processors {
    ImmutableList<StructureProcessor> BlackToWhiteTerracotta = ImmutableList.of(
            new RuleStructureProcessor(ImmutableList.of(
                    new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_PATH), new BlockMatchRuleTest(Blocks.WATER), Blocks.OAK_PLANKS.defaultBlockState()),
                    new RuleEntry(new RandomBlockMatchRuleTest(Blocks.BLACK_CONCRETE, 0.1F), AlwaysTrueRuleTest.INSTANCE, Blocks.WHITE_TERRACOTTA.defaultBlockState()),
                    new RuleEntry(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.defaultBlockState()),
                    new RuleEntry(new BlockMatchRuleTest(Blocks.DIRT), new BlockMatchRuleTest(Blocks.WATER), Blocks.WATER.defaultBlockState()))
            ));
}
