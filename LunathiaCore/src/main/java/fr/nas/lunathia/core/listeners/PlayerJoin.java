package fr.nas.lunathia.core.listeners;

import fr.nas.lunathia.LunathiaLibs;
import fr.nas.lunathia.core.LunathiaCore;
import fr.nas.lunathia.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private LunathiaCore plugin;
    private final PlayerManager playerManager;

    public PlayerJoin(LunathiaCore plugin) {
        this.plugin = plugin;
        this.playerManager = LunathiaLibs.getInstance().getPlayerManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        this.playerManager.isExist(player.getUniqueId()).thenAccept(aBoolean -> {
            if (!aBoolean) {
                this.playerManager.createAccount(player.getUniqueId(), player.getName(), 0, 100.0);
                return;
            }
            this.playerManager.loadAccount(player.getUniqueId());
        });

    }
}
