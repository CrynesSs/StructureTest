package com.example.Util.Init;

import com.example.Blocks.FloatingCrystal.FloatingCrystalBE;
import com.example.Blocks.MovingCrystal.MovingCrystalBE;
import com.example.Core.CrystalMod;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntityInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CrystalMod.MOD_ID);

    public static final RegistryObject<TileEntityType<FloatingCrystalBE>> FLOATING_CRYSTAL = TILE_ENTITY_TYPES.register("floating_crystal", () -> TileEntityType.Builder.of(FloatingCrystalBE::new, BlockInit.FLOATING_CRYSTAL.get()).build(null));
    public static final RegistryObject<TileEntityType<MovingCrystalBE>> MOVING_CRYSTAL = TILE_ENTITY_TYPES.register("moving_crystal", () -> TileEntityType.Builder.of(MovingCrystalBE::new, BlockInit.MOVING_CRYSTAL.get()).build(null));

}
