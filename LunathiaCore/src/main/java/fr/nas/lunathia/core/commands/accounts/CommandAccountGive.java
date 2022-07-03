package fr.nas.lunathia.core.commands.accounts;

import fr.nas.lunathia.commands.Command;
import fr.nas.lunathia.core.commands.accounts.credit.CommandCreditGive;
import fr.nas.lunathia.core.commands.accounts.mana.CommandManaGive;
import fr.nas.lunathia.core.commands.accounts.money.CommandMoneyGive;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.exception.NoPermissionException;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class CommandAccountGive extends Command {

    public CommandAccountGive(Plugin plugin) {
        super(plugin);
        this.setAliases("give", "g");
        this.setPermission("lunathia.account.give");
        this.setAllowConsole(false);
        this.setChildren(new CommandMoneyGive(plugin), new CommandCreditGive(plugin), new CommandManaGive(plugin));
        this.setUsage("/account give");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws PlayerNotFoundException {
        LPlayer player = this.getLPlayer(((Player) sender).getUniqueId());

        player.getPlayer().sendMessage(
                "Gestion des compte \n" +
                        "/account give money <player> <value> \n" +
                        "/account give credit <player> <value> \n" +
                        "/account give mana <player> <value> \n"
        );
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return Arrays.asList("money", "credit", "mana");
    }
}
