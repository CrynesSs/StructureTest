package com.example.Blocks.CustomAnimatedModel;

import com.google.gson.*;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.VillageStructure;
import net.minecraftforge.client.model.*;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnimatedBakedModel implements IDynamicBakedModel {

    private final List<IBakedModel> BAKED_PARTS;
    private final HashMap<String, Group> GROUPS;
    private final boolean isAmbientOcclusion;
    private final boolean isGui3d;
    private final boolean isSideLit;
    private final TextureAtlasSprite particle;
    private final ItemOverrideList overrides;
    private final IModelTransform transforms;

    public AnimatedBakedModel(HashMap<String, Group> groups, boolean isGui3d, boolean isSideLit, boolean isAmbientOcclusion, TextureAtlasSprite particle, List<IBakedModel> bakedParts, IModelTransform combinedTransform, ItemOverrideList overrides) {
        GROUPS = groups;
        this.isAmbientOcclusion = isAmbientOcclusion;
        this.isGui3d = isGui3d;
        this.isSideLit = isSideLit;
        this.particle = particle;
        this.overrides = overrides;
        this.transforms = combinedTransform;
        this.BAKED_PARTS = bakedParts;
    }


    private static final BinaryOperator<List<BakedQuad>> ARRAY_LIST_MERGE = (quads, quads2) -> {
        quads.addAll(quads2);
        return quads;
    };

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {
        return BAKED_PARTS.parallelStream().map(k -> k.getQuads(state, side, rand, extraData)).reduce(new ArrayList<>(), ARRAY_LIST_MERGE);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return isAmbientOcclusion;
    }

    @Override
    public boolean isGui3d() {
        return isGui3d;
    }

    @Override
    public boolean usesBlockLight() {
        return isSideLit;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return particle;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ItemOverrideList.EMPTY;
    }

    public static class AnimLoader implements IModelLoader<AnimGeometry> {
        public static final AnimLoader INSTANCE = new AnimLoader();
        private final List<BlockModel> MODELS = new ArrayList<>();
        private final HashMap<String, Group> GROUPS = new HashMap<>();

        @Override
        public void onResourceManagerReload(IResourceManager resourceManager) {

        }

        @Override
        @Nonnull
        public AnimGeometry read(@Nonnull JsonDeserializationContext deserializationContext, JsonObject modelContents) {
            modelContents.get("elements").getAsJsonArray().forEach(k -> {
                JsonObject object = new JsonObject();
                JsonArray arr = new JsonArray();
                arr.add(k.getAsJsonObject());
                object.add("textures", modelContents.get("textures").getAsJsonObject());
                object.add("elements", arr);
                MODELS.add(deserializationContext.deserialize(object, BlockModel.class));
            });
            modelContents.get("groups").getAsJsonArray().forEach(k -> {
                Group g = deserializationContext.deserialize(k.getAsJsonObject(), Group.class);
                g.serialize(k.getAsJsonObject(), deserializationContext);
                GROUPS.put(g.name, g);
            });


            return new AnimGeometry(MODELS, GROUPS);
        }
    }

    static class Group {
        private final String name;
        private final int[] origin;
        private final int color;
        private transient List<Integer> MODELS;
        private transient List<Group> CHILDREN;

        private Group(String name, int[] origin, int color) {
            this.name = name;
            this.origin = origin;
            this.color = color;
        }

        public List<IBakedModel> getAllModelsInGroup(List<IBakedModel> allParts) {
            List<IBakedModel> models = new ArrayList<>();
            MODELS.parallelStream().forEach(i -> models.add(allParts.get(i)));
            return models;
        }

        public void serialize(JsonObject element, JsonDeserializationContext context) {
            this.MODELS = new ArrayList<>();
            this.CHILDREN = new ArrayList<>();
            element.get("children").getAsJsonArray().forEach(k -> {
                if (k.isJsonPrimitive()) {
                    MODELS.add(k.getAsInt());
                } else {
                    Group g = context.deserialize(k.getAsJsonObject(), Group.class);
                    g.serialize(k.getAsJsonObject(), context);
                    CHILDREN.add(g);
                }
            });
        }
    }

    public static class AnimGeometry implements IModelGeometry<AnimGeometry> {
        private final List<BlockModel> MODELS;
        private final HashMap<String, Group> GROUPS;

        public AnimGeometry(List<BlockModel> models, HashMap<String, Group> groups) {
            MODELS = models;
            GROUPS = groups;
        }

        @Override
        public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
            RenderMaterial particleLocation = owner.resolveTexture("particle");
            TextureAtlasSprite particle = spriteGetter.apply(particleLocation);
            return new AnimatedBakedModel(GROUPS, owner.isShadedInGui(), owner.isSideLit(), owner.useSmoothLighting(), particle, MODELS.stream().map(k -> k.bake(bakery, spriteGetter, modelTransform, modelLocation)).collect(Collectors.toList()), modelTransform, overrides);
        }

        @Override
        public Collection<RenderMaterial> getTextures(IModelConfiguration owner, Function<ResourceLocation, IUnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
            Set<RenderMaterial> textures = new HashSet<>();
            MODELS.parallelStream().forEach(e -> textures.addAll(e.getMaterials(modelGetter, missingTextureErrors)));
            return textures;
        }

    }

}
