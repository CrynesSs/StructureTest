package com.example.Util.Init;

import com.example.Blocks.FloatingCrystal.FloatingCrystalBlock;
import com.example.Blocks.HumanGuy.HumanGuy;
import com.example.Blocks.MovingCrystal.MovingCrystalBlock;
import com.example.Core.CrystalMod;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CrystalMod.MODID);
    public static final RegistryObject<FloatingCrystalBlock> FLOATING_CRYSTAL = BLOCKS.register("floating_crystal", FloatingCrystalBlock::new);
    public static final RegistryObject<MovingCrystalBlock> MOVING_CRYSTAL = BLOCKS.register("moving_crystal", MovingCrystalBlock::new);
    public static final RegistryObject<HumanGuy> HUMAN_GUY = BLOCKS.register("humanguy", HumanGuy::new);
}
