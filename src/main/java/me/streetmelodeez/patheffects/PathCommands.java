package me.streetmelodeez.patheffects;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PathCommands implements CommandExecutor {
    PathEffects plugin;

    PathCommands(PathEffects plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("patheffects.admin")) {
            plugin.sendMessage(sender, "no-permission");
            return true;
        }

        if (args.length == 0) {
            plugin.sendMessage(sender, "path.usage");
            return true;
        }

        if (args.length > 0) {
            switch (args[0]) {
                case "reload":
                    plugin.configYenile();
                    plugin.sendMessage(sender, "config-reloaded");
                    break;
                case "start":
                    Boolean currentValue = plugin.pathStatus.getOrDefault(((Player) sender).getUniqueId(), false);
                    if (!currentValue) {
                        plugin.pathStatus.put(((Player) sender).getUniqueId(), true);
                        plugin.sendMessage(sender, "path.path-started");

                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            plugin.pathStatus.put(((Player) sender).getUniqueId(), false);
                            plugin.sendMessage(sender, "path.path-ended");
                        }, plugin.getConfig().getInt("pathDuration") * 20L);
                    } else {
                        plugin.sendMessage(sender, "path.path-already-started");
                        break;
                    }

            }
        }
        return true;
    }
}
