package dev.JackaBoi.ReachBlock.Utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoundingBox {

    private Vector min, max;

    public static final List<EntityType> ACCEPTED = new ArrayList<>(Arrays.asList(EntityType.PLAYER, EntityType.VILLAGER, EntityType.ZOMBIE, EntityType.WITCH,
            EntityType.SPIDER,EntityType.SKELETON,EntityType.CREEPER,EntityType.PIG,EntityType.SHEEP,EntityType.COW,EntityType.MUSHROOM_COW));

    public BoundingBox(Entity entity){
        switch(entity.getType()){
            case PLAYER:
            {min=entity.getLocation().toVector().add(new Vector(-0.4,0,-0.4)); max=entity.getLocation().toVector().add(new Vector(0.4,1.9,0.4));}
            case ZOMBIE: case VILLAGER: case WITCH:
            {min=entity.getLocation().toVector().add(new Vector(-0.5,0,-0.5)); max=entity.getLocation().toVector().add(new Vector(0.5,2.05,0.5));}
            case SKELETON:
            {min=entity.getLocation().toVector().add(new Vector(-0.5,0,-0.5)); max=entity.getLocation().toVector().add(new Vector(0.5,2.09,0.5));}
            case CREEPER:
            {min=entity.getLocation().toVector().add(new Vector(-0.5,0,-0.5)); max=entity.getLocation().toVector().add(new Vector(0.5,1.8,0.5));}
            case SPIDER:
            {min=entity.getLocation().toVector().add(new Vector(-1.3,0,-1.3)); max=entity.getLocation().toVector().add(new Vector(1.3,1.0,1.3));}
            case PIG:
            {min=entity.getLocation().toVector().add(new Vector(-0.8,0,-0.8)); max=entity.getLocation().toVector().add(new Vector(0.8,1.0,0.8));}
            case COW: case MUSHROOM_COW:
            {min=entity.getLocation().toVector().add(new Vector(-0.8,0,-0.8)); max=entity.getLocation().toVector().add(new Vector(0.8,1.5,0.8));}
            case SHEEP:
            {min=entity.getLocation().toVector().add(new Vector(-0.8,0,-0.8)); max=entity.getLocation().toVector().add(new Vector(0.8,1.4,0.8));}
        }
    }

    public double getDistanceToFrom(Vector origin, Vector direction){
        RayTrace rayTrace = new RayTrace(origin,direction);
        try {
            return origin.distance(rayTrace.positionOfIntersection(min, max, 6, 0.01));
        }catch (Exception e){
            return 0;
        }
    }

}
