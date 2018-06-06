package me.qiooip.buster.commands;

import me.qiooip.buster.Buster;
import me.qiooip.buster.config.Config;
import me.qiooip.buster.config.ConfigFile;
import me.qiooip.buster.config.Language;
import me.qiooip.buster.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChunkBusterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {

            if(args[0].equalsIgnoreCase("reload")) {

                if(!sender.hasPermission("chunkbuster.reload")) {
                    sender.sendMessage(Language.NO_PERMISSION);
                    return true;
                }

                Buster.getInstance().setConfig(new ConfigFile("config.yml"));
                new Config();
                new Language();

                sender.sendMessage(Language.RELOADED_MESSAGE);

            } else {
                this.sendUsage(sender);
            }

        } else if(args.length == 3) {

            if(args[0].equalsIgnoreCase("give")) {

                if(!sender.hasPermission("chunkbuster.give")) {
                    sender.sendMessage(Language.NO_PERMISSION);
                    return true;
                }

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null) {
                    sender.sendMessage(Language.PLAYER_NOT_ONLINE.replace("<player>", args[1]));
                    return true;
                }

                if(!StringUtils.isNumber(args[2])) {
                    sender.sendMessage(Language.INVALID_NUMBER);
                    return true;
                }

                int amount = Integer.valueOf(args[2]);

                ItemStack item = Config.BUSTER_ITEM.clone();
                item.setAmount(amount);

                target.getInventory().addItem(item);

                sender.sendMessage(Language.GIVE_MESSAGE.replace("<amount>", String.valueOf(amount))
                .replace("<player>", target.getName()));

            } else {
                this.sendUsage(sender);
            }

        } else {
            this.sendUsage(sender);
        }

        return true;
    }

    private void sendUsage(CommandSender sender) {
        Language.COMMAND_USAGE.forEach(sender::sendMessage);
    }
}
