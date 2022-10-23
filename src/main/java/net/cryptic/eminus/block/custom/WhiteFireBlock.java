package net.cryptic.eminus.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class WhiteFireBlock extends BaseFireBlock {
    public WhiteFireBlock(Properties pProperties, float pFireDamage) {
        super(pProperties
                .noOcclusion()
                .noCollission()
                .lightLevel((state)-> 15)
                , pFireDamage);
    }

    private int timer;

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pPlayer.displayClientMessage(new TextComponent("§6Burning Compound(s): §3Srontium Chloride §6|| §3Strontium Nitrate"),true);
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return super.isRandomlyTicking(pState);
    }


    /*@SuppressWarnings("deprecation")
    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        timer++;
        System.out.println(timer);
        if (timer == 20*5) {
            pLevel.setBlock(pPos, Blocks.FIRE.defaultBlockState(),4);
        }
    }*/

    @Override
    protected boolean canBurn(BlockState pState) {
        return true;
    }
}
