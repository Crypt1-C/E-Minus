package net.cryptic.eminus;

import net.cryptic.eminus.block.ModBlocks;
import net.cryptic.eminus.block.entity.ModBlockEntities;
import net.cryptic.eminus.item.ModItems;
import net.cryptic.eminus.screen.ModMenuTypes;
import net.cryptic.eminus.screen.SubatomicDestructorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main {
    // Directly reference a slf4j logger

    public static final String MOD_ID = "eminus";

    //private static final Logger LOGGER = LogUtils.getLogger();

    public Main() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);


        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SUBATOMIC_DESTRUCTOR.get(), RenderType.endGateway());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_FIRE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_FIRE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_FIRE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_FIRE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_FIRE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_FIRE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_FIRE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.INFRARED_FIRE.get(), RenderType.translucent());


        MenuScreens.register(ModMenuTypes.SUBATOMIC_DESTRUCTOR_MENU.get(), SubatomicDestructorScreen::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // setup
    }
}
