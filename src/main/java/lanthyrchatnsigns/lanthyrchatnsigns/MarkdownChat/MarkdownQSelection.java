package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MarkdownQSelection implements CommandExecutor {

    Inventory colorSelect = Bukkit.createInventory(null, 54, "Lanthyr Color Select");


    public ItemStack green = new ItemStack(Material.STAINED_CLAY, 1, (byte) 5);
    ItemMeta greenMeta = green.getItemMeta();

    public ItemStack darkgray = new ItemStack(Material.CONCRETE, 1, (byte) 7);
    ItemMeta darkgrayMeta = green.getItemMeta();

    public ItemStack gray = new ItemStack(Material.CONCRETE, 1, (byte) 8);
    ItemMeta grayMeta = green.getItemMeta();

    public ItemStack white = new ItemStack(Material.STAINED_CLAY, 1);
    ItemMeta whiteMeta = green.getItemMeta();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (label.equalsIgnoreCase("chatcolor")) {

                greenMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "GREEN");
                green.setItemMeta(greenMeta);
                darkgrayMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "DARK GRAY");
                darkgray.setItemMeta(darkgrayMeta);
                grayMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "GRAY");
                gray.setItemMeta(grayMeta);
                whiteMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "WHITE | RESET");
                white.setItemMeta(whiteMeta);



                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.WHITE + "Click me to change color!");
                grayMeta.setLore(lore);
                whiteMeta.setLore(lore);
                darkgrayMeta.setLore(lore);
                greenMeta.setLore(lore);

                colorSelect.setItem(1, darkgray);
                colorSelect.setItem(2, gray);
                colorSelect.setItem(3, green);
                colorSelect.setItem(4, white);

                Player player = (Player) sender;

                player.openInventory(colorSelect);
            }
        }


        return false;
    }
}
