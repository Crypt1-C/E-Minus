package net.cryptic.eminus.block.entity.custom;

import net.cryptic.eminus.block.custom.SubatomicDestructorBlock;
import net.cryptic.eminus.block.entity.ModBlockEntities;
import net.cryptic.eminus.item.ModItems;
import net.cryptic.eminus.recipe.SubatomicDestructorRecipe;
import net.cryptic.eminus.screen.SubatomicDestructorMenu;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;


public class SubatomicDestructorBlockEntity extends BlockEntity implements MenuProvider {



    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    private int progress = 0;

    private int maxProgress = 20*60*2; // 2 minutes

    public SubatomicDestructorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.SUBATOMIC_DESTRUCTOR_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return SubatomicDestructorBlockEntity.this.progress;
                    case 1: return SubatomicDestructorBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: SubatomicDestructorBlockEntity.this.progress = value; break;
                    case 1: SubatomicDestructorBlockEntity.this.maxProgress = value; break;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("§6Sub-Atomic Destructor");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new SubatomicDestructorMenu(pContainerId,pPlayerInventory,this,this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("subatomic_destructor.progress",progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("simulator_block.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static int timer = 0;
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SubatomicDestructorBlockEntity pBlockEntity) {

        Block blockAbove = pLevel.getBlockState(pPos.above(1)).getBlock();
        BlockState blockAboveState = pLevel.getBlockState(pPos.above(1));

        if (blockAbove == Blocks.LIGHTNING_ROD && blockAboveState.getValue(BlockStateProperties.POWERED) && blockAboveState.getValue(BlockStateProperties.FACING) == Direction.UP) {
            if (hasRecipe(pBlockEntity)) {
                pBlockEntity.progress++;
                setChanged(pLevel, pPos, pState);
                if (pBlockEntity.progress > pBlockEntity.maxProgress) { craftItem(pBlockEntity); }
            } else {
                pBlockEntity.resetProgress();
                pLevel.setBlock(pPos,pState.setValue(SubatomicDestructorBlock.CHARGED,false),3);
                setChanged(pLevel, pPos, pState);
            }
            //pLevel.playLocalSound(pPos.getX(),pPos.getY(),pPos.getZ(), SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS,2.0f,0.5f,false);
            pLevel.setBlock(pPos,pState.setValue(SubatomicDestructorBlock.CHARGED,true),3);

            pLevel.playLocalSound(pPos.getX(),pPos.getY(),pPos.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS,1f,2.0f,false);


        }
    }

    private static boolean hasRecipe(SubatomicDestructorBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<SubatomicDestructorRecipe> match = level.getRecipeManager().getRecipeFor(SubatomicDestructorRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem()); //&& entity.getBlockState().getValue(SubatomicDestructorBlock.CHARGED);
    }




    private static void craftItem(SubatomicDestructorBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<SubatomicDestructorRecipe> match = level.getRecipeManager().getRecipeFor(SubatomicDestructorRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);

            entity.itemHandler.setStackInSlot(1, new ItemStack(match.get().getResultItem().getItem(),entity.itemHandler.getStackInSlot(1).getCount() + 1));

            entity.resetProgress();
            entity.getLevel().setBlock(entity.getBlockPos(),entity.getBlockState().setValue(SubatomicDestructorBlock.CHARGED,false),3);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(1).getItem() == output.getItem() || inventory.getItem(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(1).getMaxStackSize() > inventory.getItem(1).getCount();
    }

}
