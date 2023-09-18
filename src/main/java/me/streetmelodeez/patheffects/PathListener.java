package me.streetmelodeez.patheffects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PathListener implements Listener {
    PathEffects plugin;
    public PathListener(PathEffects plugin){
        this.plugin = plugin;
        }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Boolean pathActivated = plugin.pathStatus.getOrDefault(event.getPlayer().getUniqueId(),false);
        if(!pathActivated) return;
        Player player = event.getPlayer();
        Block blockBelow = player.getLocation().subtract(0, 1, 0).getBlock();

        Material blockTypeBelow = player.getLocation().subtract(0, 1, 0).getBlock().getType();
        Material blockType = Material.valueOf(plugin.getConfig().getString("blockType"));

            if (blockBelow.getType() != Material.AIR) {
                blockBelow.setType(blockType);
            }
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                if(blockBelow.getType() == blockType) {
                    blockBelow.setType(blockTypeBelow);
                }
            }, 60L);



    }
}
