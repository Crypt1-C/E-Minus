/*
 * MIT License
 *
 * Copyright 2020 klikli-dev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package net.cryptic.eminus.event;


import com.mojang.blaze3d.shaders.Effect;
import com.smashingmods.chemlib.api.ChemicalItemType;
import com.smashingmods.chemlib.registry.ItemRegistry;
import net.cryptic.eminus.Main;
import net.cryptic.eminus.block.ModBlocks;
import net.cryptic.eminus.util.Math3DUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {
    //region Static Methods

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        boolean isFlintAndSteel = event.getItemStack().getItem() == Items.FLINT_AND_STEEL;
        boolean isFireCharge = event.getItemStack().getItem() == Items.FIRE_CHARGE;
        if (isFlintAndSteel || isFireCharge) {
            //find if there is any valid items
            AABB box = new AABB(-1, -1, -1, 1, 1, 1).move(Math3DUtil.center(event.getPos()));

            List<ItemEntity> list = event.getWorld().getEntitiesOfClass(ItemEntity.class, box, item -> item.getItem().getItem()
                    == ItemRegistry.getChemicalItemByNameAndType("strontium_nitrate", ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("strontium_nitrate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("strontium_carbonate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("strontium_sulfate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("strontium_chloride",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("calcium_carbonate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("calcium_sulfate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("calcium_chloride",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("sodium_carbonate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("sodium_chloride",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("sodium_nitrate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("barium_carbonate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("barium_chloride",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("barium_nitrate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("copper_i_oxide",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("copper_carbonate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("copper_ii_hydroxide",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("copper_ii_sulfate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("rubidium_chloride",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("rubidium_carbonate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("rubidium_hydroxide",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("antimony_trisulfide",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("rubidium_nitrate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("cesium_nitrate",ChemicalItemType.COMPOUND).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("aluminum",ChemicalItemType.DUST).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("magnesium",ChemicalItemType.DUST).get()
                    || item.getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("titanium",ChemicalItemType.DUST).get()
            );

            if (!list.isEmpty()) {

                BlockPos pos = event.getPos().relative(event.getFace());
                if (!event.getEntity().mayInteract(event.getWorld(),pos)) {
                    return;
                }

                list.forEach(e -> e.remove(Entity.RemovalReason.DISCARDED));

                Level level = event.getWorld();

                /*if there is air, place block and play sound*/


                // Red Fire
                if (level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("strontium_nitrate",ChemicalItemType.COMPOUND).get()
                        || level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("strontium_carbonate",ChemicalItemType.COMPOUND).get()
                        || level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("strontium_sulfate",ChemicalItemType.COMPOUND).get()
                        || level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("strontium_chloride",ChemicalItemType.COMPOUND).get()) {

                    SoundEvent soundEvent = isFlintAndSteel ? SoundEvents.FLINTANDSTEEL_USE : SoundEvents.FIRECHARGE_USE;
                    level.playSound(event.getPlayer(), pos, soundEvent, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                    level.setBlock(pos, ModBlocks.RED_FIRE.get().defaultBlockState(), 11);

                // Orange Fire
                } else if (level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("calcium_carbonate",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("calcium_sulfate",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("calcium_chloride",ChemicalItemType.COMPOUND).get()) {

                    SoundEvent soundEvent = isFlintAndSteel ? SoundEvents.FLINTANDSTEEL_USE : SoundEvents.FIRECHARGE_USE;
                    level.playSound(event.getPlayer(), pos, soundEvent, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                    level.setBlock(pos, ModBlocks.ORANGE_FIRE.get().defaultBlockState(), 11);

                // Yellow Fire
                } else if (level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("sodium_carbonate",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("sodium_chloride",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("sodium_nitrate",ChemicalItemType.COMPOUND).get()) {

                    SoundEvent soundEvent = isFlintAndSteel ? SoundEvents.FLINTANDSTEEL_USE : SoundEvents.FIRECHARGE_USE;
                    level.playSound(event.getPlayer(), pos, soundEvent, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                    level.setBlock(pos, ModBlocks.YELLOW_FIRE.get().defaultBlockState(), 11);

                // Green Fire
                } else if (level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("barium_carbonate",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("barium_chloride",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("barium_nitrate",ChemicalItemType.COMPOUND).get()) {

                    SoundEvent soundEvent = isFlintAndSteel ? SoundEvents.FLINTANDSTEEL_USE : SoundEvents.FIRECHARGE_USE;
                    level.playSound(event.getPlayer(), pos, soundEvent, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                    level.setBlock(pos, ModBlocks.GREEN_FIRE.get().defaultBlockState(), 11);

                // Blue Fire
                } else if (level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("copper_i_oxide",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("copper_carbonate",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("copper_ii_hydroxide",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("copper_ii_sulfate",ChemicalItemType.COMPOUND).get()) {

                    SoundEvent soundEvent = isFlintAndSteel ? SoundEvents.FLINTANDSTEEL_USE : SoundEvents.FIRECHARGE_USE;
                    level.playSound(event.getPlayer(), pos, soundEvent, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                    level.setBlock(pos, ModBlocks.BLUE_FIRE.get().defaultBlockState(), 11);

                // Purple Fire
                } else if (level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("rubidium_chloride",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("rubidium_carbonate",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("rubidium_hydroxide",ChemicalItemType.COMPOUND).get()) {

                    SoundEvent soundEvent = isFlintAndSteel ? SoundEvents.FLINTANDSTEEL_USE : SoundEvents.FIRECHARGE_USE;
                    level.playSound(event.getPlayer(), pos, soundEvent, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                    level.setBlock(pos, ModBlocks.PURPLE_FIRE.get().defaultBlockState(), 11);

                // White Fire
                } else if (level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("aluminum",ChemicalItemType.DUST).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("magnesium",ChemicalItemType.DUST).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("titanium",ChemicalItemType.DUST).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("antimony_trisulfide",ChemicalItemType.COMPOUND).get()) {

                    SoundEvent soundEvent = isFlintAndSteel ? SoundEvents.FLINTANDSTEEL_USE : SoundEvents.FIRECHARGE_USE;
                    level.playSound(event.getPlayer(), pos, soundEvent, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                    level.setBlock(pos, ModBlocks.WHITE_FIRE.get().defaultBlockState(), 11);
                    event.getPlayer().playSound(SoundEvents.FIREWORK_ROCKET_TWINKLE_FAR,1.0f,0.7f);
                    event.getPlayer().addEffect(new MobEffectInstance(MobEffects.BLINDNESS,20*2,255,true,false));
                    event.getPlayer().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,20*1,24,true,false));

                // Infrared
                } else if (level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("rubidium_nitrate",ChemicalItemType.COMPOUND).get()
                        ||level.isEmptyBlock(pos) && list.get(0).getItem().getItem() == ItemRegistry.getChemicalItemByNameAndType("cesium_nitrate",ChemicalItemType.COMPOUND).get()) {

                    SoundEvent soundEvent = isFlintAndSteel ? SoundEvents.FLINTANDSTEEL_USE : SoundEvents.FIRECHARGE_USE;
                    level.playSound(event.getPlayer(), pos, soundEvent, SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);
                    level.setBlock(pos, ModBlocks.INFRARED_FIRE.get().defaultBlockState(), 11);
                }

                if (isFlintAndSteel) {
                    event.getItemStack().hurtAndBreak(1, event.getPlayer(), (player) -> {
                        player.broadcastBreakEvent(event.getHand());
                    });
                } else if (isFireCharge) {
                    event.getItemStack().shrink(1);
                }

                event.setCanceled(true);
                event.getPlayer().swing(InteractionHand.MAIN_HAND);
            }
        }
    }
}