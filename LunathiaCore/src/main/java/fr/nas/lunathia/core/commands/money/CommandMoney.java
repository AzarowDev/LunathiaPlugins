package fr.nas.lunathia.core.commands.money;

import fr.nas.lunathia.commands.Command;
import fr.nas.lunathia.core.commands.accounts.money.CommandMoneyRemove;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import fr.nas.lunathia.utils.MessagePrefix;
import fr.nas.lunathia.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class CommandMoney extends Command {

    private Plugin plugin;

    public CommandMoney(Plugin plugin) {
        super(plugin);
        this.plugin = plugin;
        this.setAliases("money", "m");
        this.setAllowConsole(false);
        this.setChildren(
                new CommandMoneyRemove(plugin),
                new CommandMoneySet(plugin),
                new CommandMoneyClear(plugin));
        this.setUsage("/money");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws PlayerNotFoundException {
        LPlayer player = this.getLPlayer(sender.getName());
        player.sendMessage(MessagePrefix.BANQUE, Utils.color("#ffffff") + "Vous avez " + Utils.color("#17cbf8") + player.getMoney() + Utils.color("#ffffff") + "§r sur " + Utils.color("#17cbf8") + "votre compte en banque §r" + Utils.color("#ffffff") + "!");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> children = new ArrayList<>();
        if (sender.hasPermission("lunathia.money.give")) {
            children.add("give");
        }
        if (sender.hasPermission("lunathia.money.remove")) {
            children.add("remove");
        }

        if (sender.hasPermission("lunathia.money.set")) {
            children.add("set");
        }

        if (sender.hasPermission("lunathia.money.clear")) {
            children.add("clear");
        }
        return children;
    }
}
