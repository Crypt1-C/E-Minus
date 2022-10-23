package net.cryptic.eminus.item;

import net.cryptic.eminus.Main;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);


    // regular items
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
