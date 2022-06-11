package dev.JackaBoi.ReachBlock.Commands;

import dev.JackaBoi.ReachBlock.Data.PlayerData;
import dev.JackaBoi.ReachBlock.ReachBlock;
import dev.JackaBoi.ReachBlock.Utils.MathUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class rbCMD {

    public boolean execute(CommandSender sender, Command cmd, String[]args, ReachBlock plugin){
        if(args.length==0){
            sender.sendMessage("§8§m-----------------");
            sender.sendMessage("§cVersion§3: §7" + plugin.ver + " (" + plugin.newver + ")");
            sender.sendMessage("§cReachDistance§3: §7" + plugin.getConfig().getString("ReachDistance"));
            sender.sendMessage("§cUpdateChecker§3: §7" + plugin.getConfig().getString("UpdateChecker"));
            sender.sendMessage("§8§m-----------------");
            return true;
        }
        if(args.length==2 && args[0].equalsIgnoreCase("ReachDistance")){
            if(!MathUtils.isDouble(args[0])) {sender.sendMessage("§cThat is not a valid number");return false;}
            sender.sendMessage("§cReachDistance§3: §7" + plugin.getConfig().getString("ReachDistance"));
            plugin.saveConfig();
            return true;
        }
        if(args[0].equalsIgnoreCase("debug")){
            if(!(sender instanceof Player)) return false;
            if(plugin.debug.debuggers.contains(((Player) sender).getUniqueId())){
                plugin.debug.debuggers.remove(((Player) sender).getUniqueId());
            }else plugin.debug.debuggers.add(((Player) sender).getUniqueId());
            sender.sendMessage("§cDebug§3: §7" + plugin.debug.debuggers.contains(((Player) sender).getUniqueId()));
            return true;
        }
        return false;
    }

    private static final List<String> TABS = new ArrayList<>(Arrays.asList("ReachDistance", "Debug"));

    public static List<String> tabComplete(String[] args){
        final List<String> completions = new ArrayList<>();
        if(args.length==1) StringUtil.copyPartialMatches(args[0],TABS,completions);
        Collections.sort(completions);
        return completions;
    }

}
