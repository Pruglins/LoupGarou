package fr.program.cmds;

import fr.program.LoupGarouMain;
import fr.program.utility.ConstructorItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommencerCMD implements CommandExecutor {

    LoupGarouMain plugin;
    public CommencerCMD(LoupGarouMain pl) {
        this.plugin = pl;
    }

    private void GiveTime(Player plr, String role) {
        plr.removePotionEffect(PotionEffectType.BLINDNESS);
        plr.setLevel(16);
        Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" + " " + role + " se réveille !");
        Bukkit.getScheduler().runTaskTimer(plugin, (task) -> {
            if (plr.getLevel() > 0) {
                plr.setLevel(plr.getLevel() - 1);
            }

            if (plr.getLevel() == 0) {
                plr.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 3));
                Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" + " " + role + " s'endort !");
                plr.closeInventory();
                task.cancel();
            }
        }, 0L, 20L);
    }



    private void UI_Sorciere(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "§5 Sorcière");

        ItemStack killSorciere = new ItemStack( Material.LAVA_BUCKET,1);
        ItemMeta killSorciere_m = killSorciere.getItemMeta();
        killSorciere_m.setDisplayName("Tuer");
        killSorciere_m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        killSorciere_m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        killSorciere_m.getPersistentDataContainer().set(new NamespacedKey(plugin, "sorciere-kill"), PersistentDataType.DOUBLE, Math.PI);
        killSorciere.setItemMeta(killSorciere_m);

        ItemStack RessusciterSorciere = new ItemStack( Material.WATER_BUCKET,1);
        ItemMeta RessusciterSorciere_m = RessusciterSorciere.getItemMeta();
        RessusciterSorciere_m.setDisplayName("Ressusciter");
        RessusciterSorciere_m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        RessusciterSorciere_m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        RessusciterSorciere_m.getPersistentDataContainer().set(new NamespacedKey(plugin, "sorciere-ressusciter"), PersistentDataType.DOUBLE, Math.PI);
        RessusciterSorciere.setItemMeta(RessusciterSorciere_m);

        ItemStack VoidSorciere = new ItemStack( Material.DARK_OAK_DOOR,1);
        ItemMeta VoidSorciere_m = VoidSorciere.getItemMeta();
        VoidSorciere_m.setDisplayName("Ne rien faire");
        VoidSorciere_m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        VoidSorciere_m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        VoidSorciere_m.getPersistentDataContainer().set(new NamespacedKey(plugin, "sorciere-void"), PersistentDataType.DOUBLE, Math.PI);
        VoidSorciere.setItemMeta(VoidSorciere_m);

        inv.setItem(0, killSorciere);
        inv.setItem(1, RessusciterSorciere);
        inv.setItem(2, VoidSorciere);
        p.openInventory(inv);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player plr) {
            if (args.length > 0) {
                String possibleRole = args[0];
                if (possibleRole.equalsIgnoreCase("loup") || possibleRole.equalsIgnoreCase("loups")) {

                    for (int i = 1; i < 4; i++) {
                        String config_p = plugin.getConfig().getString("roles.loup-"+ i);
                        if (config_p != null && !config_p.equals("None")) {
                            Player p = Bukkit.getPlayer(config_p);
                            if (p != null) {
                                GiveTime(p, "Le(s) loup(s)");
                            }
                        }
                    }
                    return true;
                } else if (possibleRole.equalsIgnoreCase("pere") || possibleRole.equalsIgnoreCase("loup-pere")) {
                    String config_p = plugin.getConfig().getString("roles.loup-pere");
                    if (config_p != null && !config_p.equals("None")) {
                        Player p = Bukkit.getPlayer(config_p);
                        if (p != null) {GiveTime(p, "Le père des loups");}
                    }
                    return true;
                } else if (possibleRole.equalsIgnoreCase("mechant") || possibleRole.equalsIgnoreCase("mechant-loup")) {
                    String config_p = plugin.getConfig().getString("roles.mechant-loup");
                    if (config_p != null && !config_p.equals("None")) {
                        Player p = Bukkit.getPlayer(config_p);
                        if (p != null) {GiveTime(p, "Le méchant loup");}
                    }
                    return true;
                } else if (possibleRole.equalsIgnoreCase("blanc") || possibleRole.equalsIgnoreCase("loup-blanc")) {
                    String config_p = plugin.getConfig().getString("roles.loup-blanc");
                    if (config_p != null && !config_p.equals("None")) {
                        Player p = Bukkit.getPlayer(config_p);
                        if (p != null) {GiveTime(p, "Le loup blanc");}
                    }
                    return true;
                } else if (possibleRole.equalsIgnoreCase("voyante")) {
                    String config_p = plugin.getConfig().getString("roles.voyante");
                    if (config_p != null && !config_p.equals("None")) {
                        Player p = Bukkit.getPlayer(config_p);
                        if (p != null) {GiveTime(p, "La voyante");}
                    }
                    return true;
                } else if (possibleRole.equalsIgnoreCase("sorciere")) {
                    String config_p = plugin.getConfig().getString("roles.sorciere");
                    if (config_p != null && !config_p.equals("None")) {
                        Player p = Bukkit.getPlayer(config_p);
                        if (p != null) {
                            GiveTime(p, "La sorcière");
                            UI_Sorciere(p);
                        }
                    }
                    return true;
                } else if (possibleRole.equalsIgnoreCase("ange")) {
                    String config_p = plugin.getConfig().getString("roles.ange");
                    if (config_p != null && !config_p.equals("None")) {
                        Player p = Bukkit.getPlayer(config_p);
                        if (p != null) {GiveTime(p, "L'ange");}
                    }
                    return true;
                } else {
                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            " Merci de préciser un argument valide: " +
                                    ChatColor.ITALIC + "loup" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "pere-loup" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "mechant-loup" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "loup-blanc" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "voyante" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "sorciere" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "ange" + ChatColor.RESET + ", "
                            );
                    return false;
                }
            } else {
                plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                        " Merci de préciser le rôle du joueur / du camp.");
                return false;
            }
        }
        return false;
    }
}
