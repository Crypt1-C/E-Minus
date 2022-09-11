package net.cryptic.eminus.block;

import net.cryptic.eminus.Main;
import net.cryptic.eminus.block.custom.*;
import net.cryptic.eminus.item.ModItems;
import net.cryptic.eminus.item.creativetab.ModCreativeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);

    // blocks
    public static final RegistryObject<Block> SUBATOMIC_DESTRUCTOR = registerBlock("subatomic_destructor",
            ()-> new SubatomicDestructorBlock(BlockBehaviour.Properties
                    .copy(Blocks.NETHERITE_BLOCK)
            ), ModCreativeTab.EMINUS_TAB);

    public static final RegistryObject<Block> RED_FIRE = registerBlockWithoutBlockItem("red_fire",
            ()-> new RedFireBlock(BlockBehaviour.Properties.of(Material.FIRE),2.0F));

    public static final RegistryObject<Block> ORANGE_FIRE = registerBlockWithoutBlockItem("orange_fire",
            ()-> new OrangeFireBlock(BlockBehaviour.Properties.of(Material.FIRE),2.0F));

    public static final RegistryObject<Block> YELLOW_FIRE = registerBlockWithoutBlockItem("yellow_fire",
            ()-> new YellowFireBlock(BlockBehaviour.Properties.of(Material.FIRE),2.0F));

    public static final RegistryObject<Block> GREEN_FIRE = registerBlockWithoutBlockItem("green_fire",
            ()-> new GreenFireBlock(BlockBehaviour.Properties.of(Material.FIRE),2.0F));
    public static final RegistryObject<Block> BLUE_FIRE = registerBlockWithoutBlockItem("blue_fire",
            ()-> new BlueFireBlock(BlockBehaviour.Properties.of(Material.FIRE),2.0F));

    public static final RegistryObject<Block> PURPLE_FIRE = registerBlockWithoutBlockItem("purple_fire",
            ()-> new PurpleFireBlock(BlockBehaviour.Properties.of(Material.FIRE),2.0F));

    public static final RegistryObject<Block> WHITE_FIRE = registerBlockWithoutBlockItem("white_fire",
            ()-> new WhiteFireBlock(BlockBehaviour.Properties.of(Material.FIRE),2.0F));

    public static final RegistryObject<Block> INFRARED_FIRE = registerBlockWithoutBlockItem("infrared_fire",
            ()-> new InfraredFireBlock(BlockBehaviour.Properties.of(Material.FIRE),2.0F));



    // item registry
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
