package dev.JackaBoi.ReachBlock.Packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import dev.JackaBoi.ReachBlock.Data.PlayerData;
import dev.JackaBoi.ReachBlock.ReachBlock;
import dev.JackaBoi.ReachBlock.Utils.BoundingBox;
import dev.JackaBoi.ReachBlock.Wrappers.WrapperPlayClientUseEntity;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;

public class packetUseEntity extends PacketAdapter {

    private final ReachBlock plugin;
    public packetUseEntity(ReachBlock plugin){
        super(plugin, PacketType.Play.Client.USE_ENTITY);
        this.plugin=plugin;
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE) || event.getPlayer().isInsideVehicle()) return;
        WrapperPlayClientUseEntity packet = new WrapperPlayClientUseEntity(event.getPacket());
        Optional<Entity> entityOp= Optional.empty();
        try{
            entityOp = plugin.getEntitySYNC(packet.getTargetID(), event.getPlayer().getWorld().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!(entityOp.isPresent())) return;
        Entity entity = entityOp.get();
        if(!BoundingBox.ACCEPTED.contains(entity.getType())) return;
        if(event.getPlayer().getLocation().distance(entity.getLocation())<1) return;
        if(!(packet.getType(event).equals(EnumWrappers.EntityUseAction.ATTACK) && entity instanceof LivingEntity)) return;
        PlayerData data = PlayerData.getData(event.getPlayer().getUniqueId());
        if(data==null) return;
        if(data.ping==null)return;
        Long latency = System.currentTimeMillis() - data.ping;
        BoundingBox boundingBox = new BoundingBox(entity);
        double dist = boundingBox.getDistanceToFrom(data,latency,0);
        if(dist==-1) return;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        if(dist >= Double.parseDouble(plugin.getConfig().getString("ReachDistance")) || dist==0){
            plugin.debug.debug("§8[§c"+event.getPlayer().getName()+"§8] §7>> §8[§cDIST§7: §c" + df.format(dist) + "§8] §7>> §8[§cPing§7: §c" + data.ping +"§8] §7>> §8[§cBLOCKED§8]");
            event.setCancelled(true);
        }else plugin.debug.debug("§8[§c"+event.getPlayer().getName()+"§8] §7>> §8[§cDIST§7: §c" + df.format(dist) + "§8] §7>> §8[§cPing§7: §c" + data.ping +"§8] §7>> §8[§2ACCEPTED§8]");
    }
}
