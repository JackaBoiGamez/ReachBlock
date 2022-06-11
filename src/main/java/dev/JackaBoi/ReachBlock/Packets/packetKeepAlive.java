package dev.JackaBoi.ReachBlock.Packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import dev.JackaBoi.ReachBlock.Data.PlayerData;
import dev.JackaBoi.ReachBlock.ReachBlock;

import java.util.Arrays;

public class packetKeepAlive extends PacketAdapter {

    public packetKeepAlive(ReachBlock plugin){
        super(plugin, Arrays.asList(PacketType.Play.Client.KEEP_ALIVE,PacketType.Play.Server.KEEP_ALIVE));
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        PlayerData data = PlayerData.getData(event.getPlayer().getUniqueId());
        data.ping = System.currentTimeMillis() - data.lastServerKP;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PlayerData data = PlayerData.getData(event.getPlayer().getUniqueId());
        data.lastServerKP = System.currentTimeMillis();
    }

}
