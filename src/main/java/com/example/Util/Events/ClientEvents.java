package com.example.Util.Events;

import com.example.Blocks.CustomAnimatedModel.AnimatedBakedModel;
import com.example.Blocks.FloatingCrystal.FloatingCrystalLoader;
import com.example.Blocks.FloatingCrystal.FloatingCrystalModel;
import com.example.Blocks.MovingCrystal.MovingCrystalRenderer;
import com.example.Core.CrystalMod;
import com.example.Util.Init.BlockEntityInit;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

@Mod.EventBusSubscriber(modid = CrystalMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(BlockEntityInit.MOVING_CRYSTAL.get(), MovingCrystalRenderer::new);
        Minecraft.getInstance().getBlockColors().register((state, blockaccess, pos, tintindex) -> new Color(127, 15, 75).getRGB(), ForgeRegistries.BLOCKS.getValues().stream().toArray(Block[]::new));


    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getMap().location() == AtlasTexture.LOCATION_BLOCKS) {
            event.addSprite(FloatingCrystalModel.TEXTURE);
        }
    }

    @SubscribeEvent
    public static void onLoaderRegister(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(CrystalMod.MOD_ID, "floating_crystal"), new FloatingCrystalLoader());
        ModelLoaderRegistry.registerLoader(new ResourceLocation(CrystalMod.MOD_ID, "anim"), AnimatedBakedModel.AnimLoader.INSTANCE);
    }

}
