package com.example.Blocks.CustomAnimatedModel;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class AnimTE extends TileEntity {
    private final AnimationHolder ANIMATIONS = registerAnimations();

    public AnimTE(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public abstract AnimationHolder registerAnimations();

    public static class AnimationHolder {
        private final AnimatedBakedModel model;
        private final AnimationInstance[] instances;

        public AnimationHolder(AnimatedBakedModel model, AnimationInstance... instances) {
            this.model = model;
            this.instances = instances;
        }
        
    }

    public static class AnimationInstance {
        private final EAnimType type;
        private final EInterpolationType interpolationType;
        private final ERepeatingType repeatingType;
        private final AnimatedBakedModel.Group group;

        public AnimationInstance(EAnimType type, EInterpolationType interpolationType, ERepeatingType repeatingType, AnimatedBakedModel.Group group) {
            this.type = type;
            this.interpolationType = interpolationType;
            this.repeatingType = repeatingType;
            this.group = group;
        }
    }

    public enum EAnimType {
        ROTATION,
        TRANSLATION,
        SCALING
    }

    public enum EInterpolationType {
        LINEAR,
        LADDER,
        BACK_AND_FOURTH,
        SIN_INTERPOLATION,
        COS_INTERPOLATION,
        QUADRATIC,
        CUBIC
    }

    public enum ERepeatingType {
        ONCE,
        LOOPING,
        BACK_AND_FOURTH
    }


}
