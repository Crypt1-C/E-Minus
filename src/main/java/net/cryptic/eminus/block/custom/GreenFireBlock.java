package net.cryptic.eminus.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class GreenFireBlock extends BaseFireBlock {
    public GreenFireBlock(Properties pProperties, float pFireDamage) {
        super(pProperties
                .noOcclusion()
                .noCollission()
                .lightLevel((state)-> 15)
                , pFireDamage);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pPlayer.displayClientMessage(new TextComponent("§6Burning Compound(s): §3Copper(II) Sulfate §6&& §3Boric Acid"), true);
        return InteractionResult.SUCCESS;
    }



    @Override
    protected boolean canBurn(BlockState pState) {
        return true;
    }
}
