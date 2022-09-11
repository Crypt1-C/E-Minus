package net.cryptic.eminus.block.entity;

import net.cryptic.eminus.Main;
import net.cryptic.eminus.block.ModBlocks;
import net.cryptic.eminus.block.entity.custom.SubatomicDestructorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Main.MOD_ID);

    public static final RegistryObject<BlockEntityType<SubatomicDestructorBlockEntity>> SUBATOMIC_DESTRUCTOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("subatomic_destructor_block_entity",() -> BlockEntityType.Builder.of(SubatomicDestructorBlockEntity::new, ModBlocks.SUBATOMIC_DESTRUCTOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
