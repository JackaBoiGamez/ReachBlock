package dev.JackaBoi.ReachBlock.Utils;

import org.bukkit.Location;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class MathUtils {

    public static Optional<Long> getClosetValue(Map<Long, Location> keys, Long time){
        return keys.keySet().stream().min(Comparator.comparingLong(i -> Math.abs(i - time)));
    }

    public static boolean isDouble(String d){
        try{
            Double.parseDouble(d);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

}
