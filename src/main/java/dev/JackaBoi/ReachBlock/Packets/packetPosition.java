package dev.JackaBoi.ReachBlock.Packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import dev.JackaBoi.ReachBlock.Data.PlayerData;
import dev.JackaBoi.ReachBlock.ReachBlock;

import java.util.Arrays;

public class packetPosition extends PacketAdapter {

    public packetPosition(ReachBlock plugin){
        super(plugin, Arrays.asList(PacketType.Play.Client.POSITION_LOOK,PacketType.Play.Client.POSITION));
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        PlayerData.getData(event.getPlayer().getUniqueId()).addLocation(System.currentTimeMillis(), event.getPlayer().getEyeLocation());
    }

}
