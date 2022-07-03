package fr.nas.lunathia.core.commands.accounts;

import fr.nas.lunathia.commands.Command;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.exception.NoPermissionException;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class CommandAccount extends Command {

    public CommandAccount(Plugin plugin) {
        super(plugin);
        this.setAliases("account", "a");
        this.setPermission("lunthia.account");
        this.setAllowConsole(false);
        this.setChildren(new CommandAccountInfo(plugin), new CommandAccountGive(plugin));
        this.setUsage("/account");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws NoPermissionException, PlayerNotFoundException {
        LPlayer player = this.getLPlayer(((Player) sender).getUniqueId());
        if (!player.getPlayer().hasPermission("lunathia.account")) throw new NoPermissionException();

        player.getPlayer().sendMessage(
                "Gestion des compte \n" +
                        "/account info <player> \n" +
                        "/account give <money/credit> <player> <value> \n" +
                        "/account remove <money/credit> <player> <value> \n" +
                        "/account set <money/credit> <player> <value> \n" +
                        "/account clear <money/credit> <player> <value> \n"
        );
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return Arrays.asList("info", "give", "remove", "set", "clear");
    }
}
