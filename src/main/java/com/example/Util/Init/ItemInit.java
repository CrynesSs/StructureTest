package com.example.Util.Init;

import com.example.Blocks.MMORPG.HorsePet;
import com.example.Core.CrystalMod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CrystalMod.MOD_ID);
    public static final RegistryObject<Item> HORSE_ITEM = ITEMS.register("horse_pet", HorsePet::new);
}
