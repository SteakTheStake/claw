package com.summit.claw.claw.commands;

import com.summit.claw.claw.Claw;
import com.summit.claw.claw.ClawGUI;
import com.summit.claw.claw.OpenAIRequestHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.summit.claw.claw.ClawGUI.openInventory;

public class ClawCommand implements CommandExecutor {
    private final Claw plugin;

    private ClawGUI clawGUI;

    // Assuming there's a constructor to pass the plugin instance, adjust as necessary.
    public ClawCommand(Claw plugin) {
        this.plugin = plugin;
        this.clawGUI = new ClawGUI();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (label.equalsIgnoreCase("claw")) {
            if (args.length == 0) {
                // Handle the default command usage
                sender.sendMessage("Usage: /claw generate");
                return true;
            } else if (args[0].equalsIgnoreCase("generate")) {
                // Handle the /claw generate command
                return generateChallenge(sender);
            } else if (args[0].equalsIgnoreCase("open")) {
                // Handle the /claw open command
                openInventory((Player) sender);
                return true;
            }
        }

        return false;
    }

    public boolean generateChallenge(CommandSender sender) {
        // Code to generate and send a challenge to the player
        openInventory((Player) sender);

        return true;
    }

    private boolean openGUI(CommandSender sender) {
        // Code to open the GUI for the player
        return true;
    }

    public Claw getPlugin() {
        return plugin;
    }
}
