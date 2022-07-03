package fr.nas.lunathia.core.managers;

import fr.nas.lunathia.core.commands.accounts.CommandAccount;
import org.bukkit.plugin.Plugin;

public class CommandManager {

    private Plugin plugin;

    public CommandManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        new CommandAccount(plugin).onRegister();
    }
}
