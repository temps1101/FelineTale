package com.temps1101.felinetale.felinetale.commands;

import com.temps1101.felinetale.felinetale.utils.InventoryUtils;
import com.temps1101.felinetale.felinetale.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MenuCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtils.message("このコマンドはプレイヤーのみが実行できます！そしてあなたは実行できません！人間になって出直しだ！", true));
            return true;
        }

        Player player = (Player) sender;

        Inventory mainMenu = InventoryUtils.createMainMenu(player);

        player.openInventory(mainMenu);

        return true;
    }
}
