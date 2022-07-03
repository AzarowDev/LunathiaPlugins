package fr.nas.lunathia.core.listeners;

import fr.nas.lunathia.LunathiaLibs;
import fr.nas.lunathia.core.LunathiaCore;
import fr.nas.lunathia.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private LunathiaCore plugin;
    private PlayerManager playerManager;

    public PlayerQuit(LunathiaCore plugin) {
        this.plugin = plugin;
        this.playerManager = LunathiaLibs.getInstance().getPlayerManager();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.playerManager.saveAccount(player.getUniqueId(), true);
    }

    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        this.playerManager.saveAccount(player.getUniqueId(), true);
    }
}
