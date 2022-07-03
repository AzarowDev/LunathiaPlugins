package fr.nas.lunathia.core.commands.accounts.mana;

import com.google.common.collect.Lists;
import fr.nas.lunathia.commands.Command;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.exception.NoPermissionException;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import fr.nas.lunathia.utils.MessagePrefix;
import fr.nas.lunathia.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.stream.Collectors;

public class CommandManaRemove extends Command {

    public CommandManaRemove(Plugin plugin) {
        super(plugin);
        this.setAliases("mana");
        this.setAllowConsole(false);
        this.setPermission("lunathia.account.remove");
        this.setUsage("/account remove mana");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws PlayerNotFoundException, NoPermissionException {
        LPlayer target = this.getLPlayer(args[0]);
        LPlayer player = this.getLPlayer(((Player) sender).getUniqueId());

        if (!target.hasMana(Integer.parseInt(args[1]))) {
            player.sendMessage(MessagePrefix.BANQUE, Utils.color("#ff1828") + args[0] + Utils.color("#ffffff") + " n'a que " + Utils.color("#17cbf8") + target.getMana() + Utils.color("#ffffff") + " sur " + Utils.color("#17cbf8") + "sont compte en banque " + Utils.color("#ffffff") + "!");
            return;
        }

        target.withdrawMoney(Double.parseDouble(args[1]));
        player.sendMessage(MessagePrefix.BANQUE, Utils.color("#ffffff") + " Vous avez supprimé " + Utils.color("#17cbf8") + args[1] + Utils.color("#ffffff") + "§r sur " + Utils.color("#17cbf8") + "le compte en banque de " + Utils.color("#ff1828") + args[0] + Utils.color("#ffffff") + "!");
        target.sendMessage(MessagePrefix.BANQUE, Utils.color("#ffffff") + " Le Banquier vous a retirer " + Utils.color("#17cbf8") + args[1] + Utils.color("#ffffff") + "§r sur " + Utils.color("#17cbf8") + "votre compte en banque " + Utils.color("#ffffff") + "!");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if(args.length <= 1) return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
        return Lists.newArrayList();
    }
}
