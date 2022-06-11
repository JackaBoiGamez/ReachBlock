package dev.JackaBoi.ReachBlock.Commands;

import dev.JackaBoi.ReachBlock.ReachBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandReg implements CommandExecutor, TabCompleter {

    private ReachBlock plugin;
    public CommandReg(ReachBlock plugin){
        plugin.getCommand("rb").setExecutor(this);
        plugin.getCommand("rb").setTabCompleter(this);
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player && !sender.hasPermission(command.getPermission())){
            sender.sendMessage(command.getPermissionMessage());return true;
        }
        if ("rb".equals(command.getName())) {
            return new rbCMD().execute(sender, command, strings, plugin);
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if ("rb".equals(command.getName())) {
            return rbCMD.tabComplete(strings);
        }
        return null;
    }
}
