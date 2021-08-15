package com.example.Blocks.MMORPG;

import com.example.Core.CrystalMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.MathHelper;

import java.util.UUID;

public class HorsePet extends Item {
    public HorsePet() {
        super(new Item.Properties().tab(CrystalMod.MMO).defaultDurability(500).fireResistant());
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        CompoundNBT tag = stack.getOrCreateTag();
        byte speedLevel = 0;
        if (tag.contains("speed")) {
            speedLevel = tag.getByte("speed");
        }
        return Rarity.values()[MathHelper.clamp((int) speedLevel / 25, 0, 3)];
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }


    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        if (context.getPlayer().getCooldowns().isOnCooldown(this)) return ActionResultType.FAIL;
        context.getPlayer().getCooldowns().addCooldown(this, 120);
        if (context.getLevel().isClientSide) return ActionResultType.SUCCESS;

        return ActionResultType.SUCCESS;
    }

}
