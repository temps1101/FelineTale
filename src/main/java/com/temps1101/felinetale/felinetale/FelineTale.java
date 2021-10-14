package com.temps1101.felinetale.felinetale;

import com.temps1101.felinetale.felinetale.commands.MenuCommand;
import com.temps1101.felinetale.felinetale.listeners.InventoryClickListener;
import com.temps1101.felinetale.felinetale.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FelineTale extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("menuitem").setExecutor(new MenuCommand());
        Bukkit.getPluginCommand("menuitem").setPermissionMessage(MessageUtils.message("&c権限がありません!", true));

        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this.getLogger()), this);
    }

    @Override
    public void onDisable() {
    }
}
