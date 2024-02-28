package com.summit.claw.claw;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Claw extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register subcommands

        // Claw generate command
        if (this.getCommand("claw generate") == null) {
            getLogger().severe("Command 'claw generate' not found. Check plugin.yml");
        } else {
            getCommand("claw generate").setExecutor(new ClawCommand(this));
        }

        // Claw open command
        if (this.getCommand("claw open") == null) {
            getLogger().severe("Command 'claw open' not found. Check plugin.yml");
        } else {
            getCommand("claw open").setExecutor(new ClawCommand(this));
        }

        getServer().getPluginManager().registerEvents(new ClawGUI(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("C.L.A.W. has stopped");
    }
}