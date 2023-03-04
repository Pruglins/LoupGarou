package fr.program.utility;

import com.mojang.datafixers.kinds.Const;
import fr.program.LoupGarouMain;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ConstructorItem extends ItemStack {

    LoupGarouMain plugin;

    public ConstructorItem(LoupGarouMain p) {
        this.plugin = p;
    }
    public ItemStack createItem(String name, Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta item_m = item.getItemMeta();
        item_m.setDisplayName(name);
        item_m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item_m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(item_m);
        return item;
    }
}
