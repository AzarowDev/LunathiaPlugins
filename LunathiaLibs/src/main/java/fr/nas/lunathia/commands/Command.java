package fr.nas.lunathia.commands;

import com.google.common.collect.Lists;
import fr.nas.lunathia.LunathiaLibs;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.exception.NoPermissionException;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import fr.nas.lunathia.managers.PlayerManager;
import fr.nas.lunathia.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public abstract class Command implements CommandExecutor, CommandTabCompleter {

    private Plugin plugin;

    private String name, description, usage, permission, permissionDenyMessage, noArgs;
    private boolean allowConsole;
    private List<String> aliases = Lists.newArrayList();
    private List<Command> children = Lists.newArrayList();
    private boolean sync = true;
    private CommandExecutor executor = this;
    private CommandTabCompleter tabCompleter = this;
    private Command parent;

    private final PlayerManager playerManager;

    private static final String NO_PERM_MESSAGE = Utils.color("#eb3434") + "Vous n'avez pas la permission pour utilisé cette commende " + Utils.color("#ffffff") + "!";
    private static final String NO_USER_MESSAGE = Utils.color("#eb3434") + "Le joueur n'existe pas ou est déconnecté " + Utils.color("#ffffff") + "!";

    private static final String NO_VALUE_MESSAGE = Utils.color("#eb3434") + "Merci de rentrer un montant valide " + Utils.color("#ffffff") + "!";
    public Command(Plugin plugin) {
        this.plugin = plugin;
        this.playerManager = LunathiaLibs.getInstance().getPlayerManager();
    }

    public final void onRegister() {
        this.children.forEach(child -> {
            child.plugin = this.plugin;
            child.parent = this;
            child.onRegister();
        });
        if (isRoot()) CommandBukkit.getCommandMap().register(this.plugin.getName(), new CommandBukkit(this));
    }


    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return null;
    }

    final void executeCalled(CommandSender sender, String[] args) {
        if (!this.children.isEmpty() && args.length > 0)
            for (Command child : this.children) {
                if (child.getAliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(args[0]))) {
                    List<String> list = Lists.newArrayList(args);
                    list.remove(0);
                    child.executeCalled(sender, list.toArray(new String[0]));
                    return;
                }
            }
        if (sender instanceof org.bukkit.command.ConsoleCommandSender && !isAllowConsole()) {
            sender.sendMessage("&c" + getName() + " &rdoes not have console support.");
            return;
        }
        if (this.permission != null && !sender.hasPermission(this.permission)) {
            String pDM = this.permissionDenyMessage != null ? this.permissionDenyMessage : NO_PERM_MESSAGE; // TODO ton message de no permission custom à la place de ""
            sender.sendMessage(pDM);
            return;
        }

        try {
            this.executor.execute(sender, args);
        } catch (Exception exception) {
            if (exception instanceof IndexOutOfBoundsException) {
                sender.sendMessage(getUsage());
            } else if (exception instanceof PlayerNotFoundException) {
                sender.sendMessage(NO_USER_MESSAGE);
            } else if (exception instanceof NumberFormatException) {
                sender.sendMessage(NO_VALUE_MESSAGE);
            }
        }
    }

    final List<String> tabCalled(CommandSender sender, String alias, String[] args) {
        if (this.permission != null && !sender.hasPermission(this.permission))
            return null;
        if (!this.children.isEmpty() && args.length > 0)
            for (Command child : this.children) {
                if (child.getAliases().stream().anyMatch(a -> a.equalsIgnoreCase(args[0]))) {
                    List<String> list = Lists.newArrayList(args);
                    list.remove(0);
                    return child.tabCalled(sender, alias, list.toArray(new String[0]));
                }
            }
        return tabComplete(sender, alias, args);
    }

    public String getName() {
        return (this.name != null) ? this.name : ((this.aliases.size() > 0) ? this.aliases.get(0) : null);
    }

    public String getDescription() {
        return (this.description != null) ? this.description : "";
    }

    public String getUsage() {
        return (this.usage != null) ? this.usage : ("/" + this.name);
    }

    public String getPermission() {
        return this.permission;
    }

    public boolean isAllowConsole() {
        return this.allowConsole;
    }

    public List<String> getAliases() {
        return Collections.unmodifiableList(this.aliases);
    }

    public List<Command> getChildren() {
        return Collections.unmodifiableList(this.children);
    }

    public boolean isSync() {
        return this.sync;
    }

    public CommandExecutor getExecutor() {
        return this.executor;
    }

    public CommandTabCompleter getTabCompleter() {
        return this.tabCompleter;
    }

    public Command getParent() {
        return this.parent;
    }

    public String getPermissionDenyMessage() {
        return this.permissionDenyMessage;
    }

    public Command getRoot() {
        Command command = this;
        while (!command.isRoot())
            command = command.getParent();
        return command;
    }

    public Player getPlayer(String name) throws PlayerNotFoundException {
        Player player = Bukkit.getPlayer(name);
        if (player == null)
            throw new PlayerNotFoundException();
        return player;
    }

    public Player getPlayer(UUID uuid) throws PlayerNotFoundException {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null)
            throw new PlayerNotFoundException();
        return player;
    }

    public LPlayer getLPlayer(String name) throws PlayerNotFoundException {
        return this.playerManager.getPlayers().get(getPlayer(name).getUniqueId());
    }

    public LPlayer getLPlayer(UUID uuid) throws PlayerNotFoundException {
        return this.playerManager.getPlayers().get(getPlayer(uuid).getUniqueId());
    }
    public boolean isRoot() {
        return (this.parent == null);
    }

    public void setParent(Command parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setAllowConsole(boolean allowConsole) {
        this.allowConsole = allowConsole;
    }

    public void setAliases(List<String> aliases) {
        this.aliases.clear();
        aliases.forEach(this::addAlias);
    }

    public void setAliases(String... aliases) {
        setAliases(Arrays.asList(aliases));
    }

    public void addAlias(String alias) {
        if (!this.aliases.contains(alias))
            this.aliases.add(alias);
    }

    public void setChildren(List<Command> children) {
        this.children.clear();
        children.forEach(this::addChild);
    }

    public void setChildren(Command... children) {
        setChildren(Arrays.asList(children));
    }

    public void addChild(Command child) {
        if (!this.children.contains(child)) {
            child.plugin = this.plugin;
            child.parent = this.parent;
            this.children.add(child);
        }
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    public void setTabCompleter(CommandTabCompleter tabCompleter) {
        this.tabCompleter = tabCompleter;
    }

    public void setPermissionDenyMessage(String permissionDenyMessage) {
        this.permissionDenyMessage = permissionDenyMessage;
    }
}
