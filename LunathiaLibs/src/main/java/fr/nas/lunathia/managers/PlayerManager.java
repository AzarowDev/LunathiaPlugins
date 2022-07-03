package fr.nas.lunathia.managers;

import com.mongodb.client.model.Filters;
import fr.nas.lunathia.entity.LPlayer;
import fr.nas.lunathia.module.MongoModule;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerManager extends MongoModule<LPlayer> {

    private HashMap<UUID, LPlayer> players = new HashMap<>();

    public PlayerManager() {
        super("Users", LPlayer.class);
    }

    /**
     * Check si le compte exist ou pas
     *
     * @return boolean
     */
    public CompletableFuture<Boolean> isExist(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            LPlayer player = this.getCollection().find(Filters.eq("_id", uuid)).first();
            return player != null;
        });
    }

    /**
     * Recupérer un compte dans la bases de donnée !
     * et mise en cache du compte
     */
    public void loadAccount(UUID uuid) {
        CompletableFuture.runAsync(() -> {
            this.players.put(uuid, this.getCollection().find(Filters.eq("_id", uuid)).first());
        });
    }

    /**
     * Crée un Compte Utilisateur
     * et mise en cache du compte
     */
    public void createAccount(UUID uuid, String username, int credit, double money) {
        CompletableFuture.runAsync(() -> {
            LPlayer player = new LPlayer(uuid);
            player.create(username, credit, money);
            this.getCollection().insertOne(player);
            this.players.put(uuid, player);
        });
    }

//    /**
//     * Sauvegarder des information dans ma bases de donnée
//     * @param uuid du joueur
//     * @param data champ a modifier en bases de donnée
//     * @param tItem la valeur réél du champ data
//     */
//    public <TItem>  CompletableFuture<Void> update(UUID uuid, String data, TItem tItem) {
//        return CompletableFuture.runAsync(() -> this.collection.updateOne(Filters.eq("_id", uuid), Updates.set(data, tItem)));
//    }

    /**
     * Sauvegarder le compte en database
     */
    public void saveAccount(UUID uuid, boolean cache) {
        CompletableFuture.runAsync(() -> {
            LPlayer player = this.players.get(uuid);
            this.getCollection().replaceOne(Filters.eq("_id", uuid), player);
            this.players.remove(uuid);
        });
    }

    public CompletableFuture<Void> saveAllAccount() {
        return CompletableFuture.runAsync(() -> {
            Bukkit.getOnlinePlayers().forEach(item -> {
                saveAccount(item.getUniqueId(), false);
            });
        });
    }

    public Optional<LPlayer> getPlayer(Player player) {
        return Optional.ofNullable(players.get(player.getUniqueId()));
    }

    public void registerPlayer(Player player) {
        players.put(player.getUniqueId(), new LPlayer(player.getUniqueId()));
    }

    public void unregisterPlayer(Player player) {
        players.remove(player.getUniqueId());
    }

    public HashMap<UUID, LPlayer> getPlayers() {
        return players;
    }
}
