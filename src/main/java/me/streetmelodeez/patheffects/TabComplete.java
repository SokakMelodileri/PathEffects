package me.streetmelodeez.patheffects;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    PathEffects plugin;
    public TabComplete(PathEffects plugin){
        this.plugin = plugin;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (sender instanceof Player) {
            if (args.length == 1) {
                completions.add("reload");
                completions.add("start");
            }
        }
        return completions;

    }
}

