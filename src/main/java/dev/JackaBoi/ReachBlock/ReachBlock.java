package dev.JackaBoi.ReachBlock;

import dev.JackaBoi.ReachBlock.Commands.CommandReg;
import dev.JackaBoi.ReachBlock.Data.Debug;
import dev.JackaBoi.ReachBlock.Data.PlayerData;
import dev.JackaBoi.ReachBlock.Events.BukkitEvents;
import dev.JackaBoi.ReachBlock.Events.PacketEvents;
import dev.JackaBoi.ReachBlock.Utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public final class ReachBlock extends JavaPlugin {

    public final String ver = "a08";
    public String newver;
    public boolean upToDate = true;
    public Debug debug;
    private final Logger log = Bukkit.getLogger();
    @Override
    public void onEnable() {
        saveDefaultConfig();
        log.info("[ReachBlock] >> [Startup]");
        new BukkitEvents(this);
        new PacketEvents(this);
        debug = new Debug();
        new CommandReg(this);
        log.info("[ReachBlock] >> [Enabled]");

        if(Boolean.parseBoolean(getConfig().getString("UpdateChecker"))) {
            new UpdateChecker(this).getLatestVersion(version -> {
                if (ver.equalsIgnoreCase(version)) {
                    newver=ver;
                    upToDate = true;
                } else {
                    Bukkit.getLogger().info("[ReachBlock] >> Plugin Has A Update");
                    newver=version;
                    upToDate = false;
                }
            });
        }else{
            newver=ver;
            upToDate=true;
        }
    }

    @Override
    public void onDisable() {
        log.info("[ReachBlock] >> [Shutdown]");
        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
        PlayerData.dataPlayers.clear();
        log.info("[ReachBlock] >> [Disabled]");
    }

    public synchronized Optional<Entity> getEntitySYNC(int entityID, String worldId) throws ExecutionException, InterruptedException {
        Future<Optional<Entity>> entityFuture = Bukkit.getScheduler().callSyncMethod(this, () -> Bukkit.getWorld(worldId).getEntities().stream().filter(entity -> entity.getEntityId()==entityID).findFirst());
        return entityFuture.get();
    }
}
