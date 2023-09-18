package me.streetmelodeez.patheffects;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class PathEffects extends JavaPlugin {
    FileConfiguration config = this.getConfig();
    HashMap <UUID, Boolean> pathStatus = new HashMap<>();
    @Override
    public void onEnable() {
        configYenile();
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("§aPathEffects is enabled");
        getServer().getPluginManager().registerEvents(new PathListener(this), this);
        getCommand("patheffects").setExecutor(new PathCommands(this));
        getCommand("patheffects").setTabCompleter(new TabComplete(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("§4PathEffects is disabled");

    }

    public void configYenile(){
        reloadConfig();
        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }
    public void sendMessage (CommandSender receiver, String path, String... args){
        String rawMessage = getConfig().getString("pluginTag") + " " + getConfig().getString("messages."+path);
        String formattedMessage = ChatColor.translateAlternateColorCodes('&', String.format(rawMessage, (Object) args));
        receiver.sendMessage(formattedMessage);
    }
}
