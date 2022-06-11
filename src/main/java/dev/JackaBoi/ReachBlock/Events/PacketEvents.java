package dev.JackaBoi.ReachBlock.Events;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.JackaBoi.ReachBlock.Packets.packetUseEntity;
import dev.JackaBoi.ReachBlock.Packets.packetKeepAlive;
import dev.JackaBoi.ReachBlock.Packets.packetPosition;
import dev.JackaBoi.ReachBlock.ReachBlock;

public class PacketEvents {

    public PacketEvents(ReachBlock plugin){
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new packetKeepAlive(plugin));
        manager.addPacketListener(new packetPosition(plugin));
        manager.addPacketListener(new packetUseEntity(plugin));
    }

}
