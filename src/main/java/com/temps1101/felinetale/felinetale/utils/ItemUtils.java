package com.temps1101.felinetale.felinetale.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemUtils {
    public static ItemStack createItem(Material type, String name, Enchantment enchantment) {
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();

        if (name != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }

        if (enchantment != null) {
            EnchantmentStorageMeta storageMeta = (EnchantmentStorageMeta) meta;
            storageMeta.addStoredEnchant(enchantment, 1, true);
            item.setItemMeta(storageMeta);
        }

        return item;
    }
}
