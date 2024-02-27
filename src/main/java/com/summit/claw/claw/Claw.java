package com.summit.claw.claw;

import com.summit.claw.claw.commands.ClawCommand;
import net.kyori.adventure.inventory.Book;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Claw extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveResource("api_key.yml", false);
        OpenAIRequestHandler.initialize(this);
        System.out.println("C.L.A.W. has started successfully");
        //Register commands
        if (this.getCommand("claw") == null) {
            getLogger().severe("Command 'claw' not found. Check plugin.yml");
        } else {
            Objects.requireNonNull(this.getCommand("claw")).setExecutor(new ClawCommand(this));
        }
        getServer().getPluginManager().registerEvents(new ClawGUI(), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("C.L.A.W. has stopped");
    }
}
