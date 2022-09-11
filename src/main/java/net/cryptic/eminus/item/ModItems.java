package net.cryptic.eminus.item;

import net.cryptic.eminus.Main;
import net.cryptic.eminus.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);


    // regular items
    public static final RegistryObject<Item> ELECTRON = ITEMS.register("electron",()-> new ElectronItem(new Item.Properties()));

    public static final RegistryObject<Item> PROTON = ITEMS.register("proton",()-> new ProtonItem(new Item.Properties()));

    public static final RegistryObject<Item> NEUTRON = ITEMS.register("neutron",()-> new NeutronItem(new Item.Properties()));

    public static final RegistryObject<Item> UP_QUARK = ITEMS.register("up_quark",()-> new QuarkUpItem(new Item.Properties()));

    public static final RegistryObject<Item> DOWN_QUARK = ITEMS.register("down_quark",()-> new QuarkDownItem(new Item.Properties()));

    public static final RegistryObject<Item> CHARM_QUARK = ITEMS.register("charm_quark",()-> new QuarkCharmItem(new Item.Properties()));

    public static final RegistryObject<Item> STRANGE_QUARK = ITEMS.register("strange_quark",()-> new QuarkStrangeItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
