package fr.nas.lunathia.core.commands.money;

import fr.nas.lunathia.LunathiaLibs;
import fr.nas.lunathia.commands.Command;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.exception.NoPermissionException;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import fr.nas.lunathia.utils.MessagePrefix;
import fr.nas.lunathia.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandMoneySet extends Command {

    public CommandMoneySet(Plugin plugin) {
        super(plugin);
        this.setAliases("set", "s");
        this.setAllowConsole(false);
        this.setUsage("/money set");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws PlayerNotFoundException, NoPermissionException {
        LPlayer target = this.getLPlayer(args[0]);
        LPlayer player = this.getLPlayer(((Player) sender).getUniqueId());
        if (!player.getPlayer().hasPermission("lunathia.money.set")) {
            throw new NoPermissionException();
        }

        target.setMoney(Double.parseDouble(args[1]));
        player.sendMessage(MessagePrefix.BANQUE, Utils.color("#ffffff") + " Vous vener de definir l'argent a " + Utils.color("#17cbf8") + args[1] + Utils.color("#ffffff") + "§r sur " + Utils.color("#17cbf8") + "le compte en banque de " + Utils.color("#ff1828") + args[0] + Utils.color("#ffffff") + "!");
        target.sendMessage(MessagePrefix.BANQUE, Utils.color("#ffffff") + " Le Banquier vous a definir votre argent a " + Utils.color("#17cbf8") + args[1] + Utils.color("#ffffff") + "§r sur " + Utils.color("#17cbf8") + "votre compte en banque " + Utils.color("#ffffff") + "!");
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> players = new ArrayList<>();
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            players.add(online.getName());
        }
        return players;
    }
}