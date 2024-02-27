package com.summit.claw.claw;

import com.summit.claw.claw.commands.ClawCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Claw extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("C.L.A.W. has started successfully");
        //Register commands
        getCommand("claw").setExecutor(new ClawCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("C.L.A.W. has stopped");
    }
}
