package fr.nas.lunathia.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.util.List;

public class CommandBukkit extends org.bukkit.command.Command {

    private static final Field COMMAND_MAP = getField(Bukkit.getServer().getClass(), "commandMap");
    private final Command command;

    public static SimpleCommandMap getCommandMap() {
        return getFieldValue(COMMAND_MAP, Bukkit.getServer());
    }

    protected CommandBukkit(Command command) {
        super(command.getName(), command.getDescription(), command.getUsage(), command.getAliases());
        this.command = command;
    }

    public boolean execute(CommandSender sender, String label, String[] args) {
        try {
            this.command.executeCalled(sender, args);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        List<String> result = this.command.tabCalled(sender, alias, args);
        if(result == null) super.tabComplete(sender, alias, args);
        return result;
    }

    public Command getCommand() {
        return this.command;
    }

    private static Field getField(Class<?> clazz, String name) {
        Field field = null;
        Class c = clazz;
        while(c != null && c != Object.class) {
            try {
                field = c.getDeclaredField(name);
                setAccessible(field, true);
                break;
            } catch(Exception ignored) {
                c = c.getSuperclass();
            }
        }
        return field;
    }

    private static <T> T getFieldValue(Field field, Object object) {
        setAccessible(field, true);
        Object value = null;
        try {
            value = field.get(object);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return (T) value;
    }

    private static void setAccessible(Field field, boolean accessible) {
        try {
            field.setAccessible(accessible);
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(accessible);
            if (accessible) {
                modifiers.setInt(field, field.getModifiers() & -17);
            }
        } catch (Exception var3) {
        }

    }
}
