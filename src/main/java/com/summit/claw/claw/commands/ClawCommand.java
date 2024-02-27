package com.summit.claw.claw.commands;

import com.summit.claw.claw.Claw;
import com.summit.claw.claw.OpenAIRequestHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClawCommand implements CommandExecutor {
    private final Claw plugin;

    // Assuming there's a constructor to pass the plugin instance, adjust as necessary.
    public ClawCommand(Claw plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        // Ensure that this executor only handles the "claw" command
        if (!label.equalsIgnoreCase("claw")) {
            return false;
        }

        // Handle no args or other subcommands
        if (!(args.length != 0 && args[0].equalsIgnoreCase("generate"))) {
            sender.sendMessage("Usage: /claw generate");
            return false;
        }

        // Now handling "/claw generate"
        if (args[0].equalsIgnoreCase("generate")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command.");
                return true;
            }

            Player player = (Player) sender;
            // Call OpenAI API to generate challenge (simplified for example)
            String challenge = OpenAIRequestHandler.generateChallenge();
            player.sendMessage("Minecraft Challenge: " + challenge);
            return true;
        }

        return false;
    }

    public Claw getPlugin() {
        return plugin;
    }
}
