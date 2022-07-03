package fr.nas.lunathia.entity;

import fr.nas.lunathia.utils.MessagePrefix;
import fr.nas.lunathia.utils.Utils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LPlayer {

    @BsonId
    private UUID uuid;
    private String name;
    private int credit;
    private double money;

    private int mana;

    public LPlayer() {
    }
    public LPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public void create(String name, int credit, double money, int mana) {
        this.name = name;
        this.credit = credit;
        this.money = money;
        this.mana = mana;
    }

    public void sendMessage(MessagePrefix messagePrefix, String message) {
        getPlayer().sendMessage(Utils.color(messagePrefix.getHex()) + messagePrefix.getPrefix() + " " + Utils.color("#6e7f80") + "♦ " + message);
    }

    public boolean hasMoney(double money) {
        if (money <= this.money) {
            return true;
        }
        return false;
    }

    /**
     * Methods money
     */
    public void clearMoney() {
        this.money = 0.0;
    }

    public void depositMoney(double money) {
        this.money += money;
    }

    public void withdrawMoney(double money) {
        this.money -= money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    /**
     * Methods credit
     */
    public boolean hasCredit(int credit) {
        if (credit <= this.credit) {
            return true;
        }
        return false;
    }

    public void clearCredit() {
        this.credit = 0;
    }

    public void depositCredit(int credit) {
        this.credit += credit;
    }

    public void withdrawCredit(int credit) {
        this.credit -= credit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    /**
     * Methods mana
     */
    public boolean hasMana(int mana) {
        if (mana <= this.mana) {
            return true;
        }
        return false;
    }

    public void clearMana() {
        this.mana = 0;
    }

    public void depositMana(int mana) {
        this.mana += mana;
    }

    public void withdrawMana(int mana) {
        this.mana -= mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Methods
     */

    @BsonIgnore
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LPlayer{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                ", money=" + money +
                '}';
    }
}