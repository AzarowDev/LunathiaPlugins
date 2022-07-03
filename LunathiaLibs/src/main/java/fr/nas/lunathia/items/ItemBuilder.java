package fr.nas.lunathia.items;

import fr.nas.lunathia.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.UUID;

/*
 @Author Anto' | SwartZ#0001
 @create 14/01/2022
 */
public class ItemBuilder extends ItemStack {

    /**
     * Constructs a new ItemBuilder2. An ItemBuilder2 extends ItemStack, an can be used as such.
     *
     * @param material The type of the item
     * @param amount   The amount of the item
     */
    public ItemBuilder(Material material, int amount) {
        super(material, amount);
    }

    /**
     * Constructs a new ItemBuilder2. An ItemBuilder2 extends ItemStack, an can be used as such.
     *
     * @param material The type of the item
     */
    public ItemBuilder(Material material) {
        super(material);
    }

    /**
     * Constructs an ItemBuilder2 using a pre-existing item
     *
     * @param item The item to copy
     */
    public ItemBuilder(ItemStack item) {
        super(item);
    }

    public ItemBuilder type(Material material){
        setType(material);
        return this;
    }

    /**
     * Sets the stack size of this ItemBuilder2
     *
     * @param amount The number of items in the stack
     * @return The ItemBuilder2 with the new stack size
     */
    public ItemBuilder amount(int amount) {
        setAmount(amount);
        return this;
    }

    public ItemBuilder dye(DyeColor color) {
        this.setDurability(color.getDyeData());
        return this;
    }

    /**
     * Adds an enchantment to this ItemBuilder2
     *
     * @param enchant The enchantment to add
     * @param level   The level of the enchantment
     * @return The enchanted ItemBuilder2
     */
    public ItemBuilder enchant(Enchantment enchant, int level) {
        ItemUtils.addEnchant(this, enchant, level);
        return this;
    }

    /**
     * Converts this ItemBuilder2 to a normal ItemStack. Useful because there are some inconsistencies within Spigot using this class.
     *
     * @return An ItemStack copy of this ItemBuilder2
     */
    public ItemStack build() {
        return new ItemStack(this);
    }

    /**
     * Set the lore of this ItemBuilder2
     *
     * @param lore The lines of lore
     * @return The ItemBuilder2 with lore added
     */
    public ItemBuilder lore(String... lore) {
        ItemUtils.setLore(this, lore);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemUtils.setLore(this, lore);
        return this;
    }

    public ItemBuilder addLore(String...lore){
        for (String s : lore) {
            lore(s);
        }
        return this;
    }

    /**
     * Add a line of lore to this ItemBuilder2
     *
     * @param line The line of lore
     * @return The ItemBuilder2 with lore added
     */
    public ItemBuilder lore(String line) {
        ItemUtils.addLore(this, line);
        return this;
    }

    /**
     * Add multiple lines of lore to this ItemBuilder2
     *
     * @param lines The lines of lore
     * @return The ItemBuilder2 with lore added
     */
    public ItemBuilder lore(Iterable<String> lines) {
        ItemUtils.addLore(this, lines);
        return this;
    }

    /**
     * Renames this ItemBuilder2
     *
     * @param name The name to set
     * @return The renamed ItemBuilder2
     */
    public ItemBuilder displayname(String name) {
        ItemUtils.rename(this, Utils.color(name));
        return this;
    }

    /**
     * Set the durability (damage) of the ItemBuilder2
     *
     * @param durability The durability to set
     * @return The ItemBuilder2 with its durability changed
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder durability(int durability) {
        this.setDurability((short) durability);
        return this;
    }

    /**
     * Adds an attribute to this ItemBuilder2
     *
     * @param attribute The Attribute to be added
     * @param modifier  The AttributeModifier to be added
     * @return The ItemBuilder2 with the attribute added
     */
    public ItemBuilder addAttribute(Attribute attribute, AttributeModifier modifier) {
        ItemUtils.addAttribute(this, attribute, modifier);
        return this;
    }

    public ItemBuilder applySkin(String identifier){
        ItemUtils.applySkin(this, identifier);
        return this;
    }

    public ItemBuilder applySkin(UUID identifier){
        ItemUtils.applySkin(this, identifier);
        return this;
    }

    /**
     * Adds an attribute to this ItemBuilder2
     *
     * @param attribute The attribute to be added
     * @param amount    The amount of the modifier
     * @param operation The operation of the modifier
     * @return The ItemBuilder2 with the attribute added
     */
    public ItemBuilder addAttribute(Attribute attribute, double amount, AttributeModifier.Operation operation) {
        ItemUtils.addAttribute(this, attribute, amount, operation);
        return this;
    }

    /**
     * Adds an attribute to this ItemBuilder2
     *
     * @param attribute The attribute to be added
     * @param amount    The amount of the modifier
     * @param operation The operation of the modifier
     * @param slot      The slot the modifier affects
     * @return The ItemBuilder2 with the attribute added
     */
    public ItemBuilder addAttribute(Attribute attribute, double amount, AttributeModifier.Operation operation, EquipmentSlot slot) {
        ItemUtils.addAttribute(this, attribute, amount, operation, slot);
        return this;
    }

    /**
     * Adds ItemFlags to this ItemBuilder2
     *
     * @param flags The ItemFlags to add
     * @return The ItemBuilder2 with the flags added
     */
    public ItemBuilder addItemFlags(ItemFlag... flags) {
        ItemUtils.addItemFlags(this, flags);
        return this;
    }

    /**
     * Adds damage to this ItemBuilder2
     *
     * @param damage The amount of damage to apply
     * @return The ItemBuilder2 with the damage applied
     */
    public ItemBuilder damage(int damage) {
        ItemUtils.damage(this, damage);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {

        try {

            SkullMeta im = (SkullMeta) this.getItemMeta();
            im.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
            this.setItemMeta(im);

        } catch (ClassCastException expected) {

        }
        return this;
    }

    /**
     * Sets the custom model data of this ItemBuilder2
     *
     * @param customModelData The custom model data to set
     * @return The ItemBuilder2 with the custom model data set
     */
    public ItemBuilder setCustomModelData(int customModelData) {
        ItemUtils.setCustomModelData(this, customModelData);
        return this;
    }

    /**
     * Add persistent tags to this ItemBuilder2
     *
     * @param key  The key to add the data under
     * @param type The type of the data
     * @param data The data to store
     * @param <T>  The primary object type
     * @param <Z>  The retrieved object type
     * @return The ItemBuilder2 with the persistent data added
     */
    public <T, Z> ItemBuilder addPersistentTag(NamespacedKey key, PersistentDataType<T, Z> type, Z data) {
        ItemUtils.addPersistentTag(this, key, type, data);
        return this;
    }

    /**
     * Sets this ItemBuilder2 to be unbreakable
     *
     * @return The ItemBuilder2 with the unbreakable tag added
     */
    public ItemBuilder unbreakable() {
        ItemUtils.setUnbreakable(this);
        return this;
    }

    public ItemBuilder glow() {
        this.enchant((this.getType() != Material.BOW) ? Enchantment.ARROW_INFINITE : Enchantment.LUCK, 10);
        this.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }
}