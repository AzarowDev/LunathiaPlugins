package fr.nas.lunathia.core;

import fr.nas.lunathia.core.listeners.PlayerJoin;
import fr.nas.lunathia.core.listeners.PlayerQuit;
import fr.nas.lunathia.core.managers.CommandManager;
import fr.nas.lunathia.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LunathiaCore extends JavaPlugin {

    private PlayerManager playerManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(this), this);
        this.commandManager = new CommandManager(this);
        this.commandManager.init();
    }

    @Override
    public void onDisable() {
    }

}
