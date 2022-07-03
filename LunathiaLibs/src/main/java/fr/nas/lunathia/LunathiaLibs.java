package fr.nas.lunathia;

import fr.nas.lunathia.data.mongo.MongoConnect;
import fr.nas.lunathia.inventory.InventoryManager;
import fr.nas.lunathia.managers.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LunathiaLibs extends JavaPlugin {

    private static LunathiaLibs instance;
    private MongoConnect mongoConnect;
    private InventoryManager inventoryManager;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;
        this.mongoConnect = new MongoConnect();
        this.playerManager = new PlayerManager();
        this.inventoryManager = new InventoryManager(this);
        this.inventoryManager.init();

    }

    @Override
    public void onDisable() {

    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public MongoConnect getMongoConnect() {
        return mongoConnect;
    }

    public static LunathiaLibs getInstance() {
        return instance;
    }
}
