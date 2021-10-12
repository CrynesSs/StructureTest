package com.example.Util.Events;

import com.example.Core.ConfiguredStructures;
import com.example.Core.CrystalMod;
import com.example.Util.Init.StructureInit;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = CrystalMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {
    @SubscribeEvent
    public static void commonSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            StructureInit.setupStructures();
            ConfiguredStructures.registerConfiguredStructures();
        });
    }
}
