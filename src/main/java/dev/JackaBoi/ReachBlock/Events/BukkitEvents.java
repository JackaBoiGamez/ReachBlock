package dev.JackaBoi.ReachBlock.Events;

import dev.JackaBoi.ReachBlock.Data.PlayerData;
import dev.JackaBoi.ReachBlock.ReachBlock;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitEvents implements Listener {

    private final ReachBlock plugin;

    public BukkitEvents(ReachBlock plugin){
        Bukkit.getPluginManager().registerEvents(this,plugin);
        this.plugin=plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        PlayerData.dataPlayers.put(e.getPlayer().getUniqueId(), new PlayerData(e.getPlayer().getUniqueId()));
        if(e.getPlayer().isOp() && !plugin.upToDate){
            e.getPlayer().sendMessage("§7[§cReachBlock§7] §8§l>> §7Update Available §7[§c" + plugin.newver + "§7]");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        PlayerData.dataPlayers.remove(e.getPlayer().getUniqueId());
        plugin.debug.debuggers.remove(e.getPlayer().getUniqueId());
    }

}
