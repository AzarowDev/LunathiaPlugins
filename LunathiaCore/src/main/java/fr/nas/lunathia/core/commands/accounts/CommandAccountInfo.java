package fr.nas.lunathia.core.commands.accounts;

import fr.nas.lunathia.commands.Command;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.exception.NoPermissionException;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import fr.nas.lunathia.utils.MessagePrefix;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandAccountInfo extends Command {


    public CommandAccountInfo(Plugin plugin) {
        super(plugin);
        this.setAliases("info", "i");
        this.setAllowConsole(false);
        this.setUsage("/account info");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws NoPermissionException, PlayerNotFoundException {
        LPlayer target = this.getLPlayer(args[0]);
        LPlayer player = this.getLPlayer(((Player) sender).getUniqueId());

        //TODO: git a faire
        player.getPlayer().sendMessage(target.toString());
    }
}
