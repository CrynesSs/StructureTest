package com.example.Blocks.FloatingCrystal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class FloatingCrystalLoader implements IModelLoader<FloatingCrystalGeometry> {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public FloatingCrystalGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new FloatingCrystalGeometry();
    }
}
