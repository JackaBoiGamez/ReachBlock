package dev.JackaBoi.ReachBlock.Utils;

import dev.JackaBoi.ReachBlock.ReachBlock;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    private final ReachBlock plugin;

    public UpdateChecker(ReachBlock plugin){
        this.plugin=plugin;
    }

    public void getLatestVersion(Consumer<String> consumer){
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try{
                InputStream inputStream = new URL("https://pastebin.com/raw/ksZJpdzi").openStream();
                Scanner scanner = new Scanner(inputStream);
                if (scanner.hasNext()){
                    consumer.accept(scanner.next());
                }
            }catch (IOException e){
                Bukkit.getLogger().info("[ReachBlock] >> Update Checker [Failed]");
            }
        });
    }

}
