package fr.nas.lunathia.core.commands.accounts;

import fr.nas.lunathia.commands.Command;
import fr.nas.lunathia.core.commands.accounts.credit.CommandCreditRemove;
import fr.nas.lunathia.core.commands.accounts.mana.CommandManaRemove;
import fr.nas.lunathia.core.commands.accounts.money.CommandMoneyRemove;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.exception.NoPermissionException;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class CommandAccountRemove extends Command {

    public CommandAccountRemove(Plugin plugin) {
        super(plugin);
        this.setAliases("remove", "r");
        this.setPermission("lunathia.account.remove");
        this.setAllowConsole(false);
        this.setChildren(new CommandMoneyRemove(plugin), new CommandCreditRemove(plugin), new CommandManaRemove(plugin));
        this.setUsage("/account remove");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws NoPermissionException, PlayerNotFoundException {
        LPlayer player = this.getLPlayer(((Player) sender).getUniqueId());

        player.getPlayer().sendMessage(
                "Gestion des compte \n" +
                        "/account remove money <player> <value> \n" +
                        "/account remove credit <player> <value> \n" +
                        "/account remove mana <player> <value> \n"
        );
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return Arrays.asList("money", "credit", "mana");
    }
}
