package dev.JackaBoi.ReachBlock.Utils;

import dev.JackaBoi.ReachBlock.Data.Debug;
import dev.JackaBoi.ReachBlock.Data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoundingBox {

    private Vector min, max;
    private Vector origin;

    public static final List<EntityType> ACCEPTED = new ArrayList<>(Arrays.asList(EntityType.PLAYER, EntityType.VILLAGER, EntityType.ZOMBIE, EntityType.WITCH,
            EntityType.SPIDER,EntityType.SKELETON,EntityType.CREEPER,EntityType.PIG,EntityType.SHEEP,EntityType.COW,EntityType.MUSHROOM_COW));

    public BoundingBox(Entity entity){
        origin=entity.getLocation().toVector().clone();
        if(entity.getType().equals(EntityType.PLAYER)){
            min=origin.clone().subtract(new Vector(0.4,0,0.4)); max=origin.clone().add(new Vector(0.4,1.9,0.4));
        }
        if(entity.getType().equals(EntityType.ZOMBIE) || entity.getType().equals(EntityType.VILLAGER) || entity.getType().equals(EntityType.WITCH)){
            min=origin.clone().subtract(new Vector(0.5,0,0.5)); max=origin.clone().add(new Vector(0.5,2.05,0.5));
        }
    }

    public void expand(Vector vector){
        this.min.subtract(vector);
        this.max.add(vector);
    }

    public double getDistanceToFrom(PlayerData data, Long latency, int attempts){
        if(attempts>20) return 0;
        Location attackerLoc = null;
        try{
            attackerLoc = data.getEstLocation(latency);
        }catch (Exception ignored){}
        if(attackerLoc==null) return -1;
        RayTrace rayTrace = new RayTrace(attackerLoc.toVector(),attackerLoc.getDirection());
        try {
            double reach = attackerLoc.toVector().distance(rayTrace.positionOfIntersection(min, max, 6, 0.01));
            reach += 0.08;
            if(reach>3) return getDistanceToFrom(data,(latency-5),(attempts+1));
            return reach;
        }catch (Exception e){return getDistanceToFrom(data,(latency-attempts),(attempts+1));}
    }

    public void showBox(World world){
        world.playEffect(min.toLocation(world), Effect.COLOURED_DUST,1);
        world.playEffect(max.toLocation(world), Effect.COLOURED_DUST,1);
        world.playEffect(origin.toLocation(world),Effect.COLOURED_DUST,1);
    }

}
