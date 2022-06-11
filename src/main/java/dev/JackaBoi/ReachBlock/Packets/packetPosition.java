package dev.JackaBoi.ReachBlock.Packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import dev.JackaBoi.ReachBlock.Data.PlayerData;
import dev.JackaBoi.ReachBlock.ReachBlock;
import dev.JackaBoi.ReachBlock.Wrappers.WrapperPlayClientPosition;
import dev.JackaBoi.ReachBlock.Wrappers.WrapperPlayClientPositionLook;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;

public class packetPosition extends PacketAdapter {

    public packetPosition(ReachBlock plugin){
        super(plugin, Arrays.asList(PacketType.Play.Client.POSITION_LOOK,PacketType.Play.Client.POSITION));
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        Location plrLoc = null;
        if(event.getPacket().getType().equals(PacketType.Play.Client.POSITION_LOOK)){
            WrapperPlayClientPositionLook packet = new WrapperPlayClientPositionLook(event.getPacket());
            plrLoc = new Location(event.getPlayer().getWorld(), packet.getX(),packet.getY(),packet.getZ());
            plrLoc.setYaw(packet.getYaw());plrLoc.setPitch(packet.getPitch());
        }
        if(event.getPacket().getType().equals(PacketType.Play.Client.POSITION)){
            WrapperPlayClientPosition packet = new WrapperPlayClientPosition(event.getPacket());
            plrLoc = new Location(event.getPlayer().getWorld(), packet.getX(),packet.getY(),packet.getZ());
            plrLoc.setYaw(event.getPlayer().getLocation().getYaw());plrLoc.setPitch(event.getPlayer().getLocation().getPitch());
        }
        if(plrLoc==null) return;
        plrLoc.add(0,1.62,0);
        PlayerData.getData(event.getPlayer().getUniqueId()).addLocation(System.currentTimeMillis(), plrLoc);
    }

}
