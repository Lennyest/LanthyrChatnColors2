package lanthyrchatnsigns.lanthyrchatnsigns.Signs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SignsCommand implements CommandExecutor {

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            if (label.equalsIgnoreCase("editsign")) {
                if (sender.hasPermission("lanthyr.signs.edit")) {
                    Player player = (Player) sender;
                    Set<Material> tmpSet = new HashSet<>();

                                if(args.length >= 1){
                                    StringBuilder message = new StringBuilder();
                                    for(int i = 1; i < args.length; i++){
                                        message.append(" ").append(args[i]);
                                    }

                                    tmpSet.add(Material.AIR);
                                    if (player.getTargetBlock(tmpSet, 100).getType() == (Material.SIGN)) {

                                        Block b = player.getTargetBlock(tmpSet, 8);
                                        Sign sign = (Sign) b.getLocation().getBlock().getState();

                                        if ((Integer.parseInt(args[0])) <= 3) {
                                            if (isInt(args[0])) {
                                            sign.setLine(Integer.parseInt(args[0]), ChatColor.translateAlternateColorCodes('&', message.toString()));
                                            sign.update();
                                        } else if (!isInt(args[0])){
                                            player.sendMessage(ChatColor.RED + "Wrong usage, /editsign <0-3> <text>");
                                        }
                                    }
                                }

                                    if (player.getTargetBlock(tmpSet, 5).getType() == (Material.WALL_SIGN)) {

                                        Block b = player.getTargetBlock(tmpSet, 8);
                                        Sign sign = (Sign) b.getLocation().getBlock().getState();

                                        if ((Integer.parseInt(args[0])) <= 3) {
                                            if (isInt(args[0])) {
                                                sign.setLine(Integer.parseInt(args[0]), ChatColor.translateAlternateColorCodes('&', message.toString()));
                                                sign.update();
                                            } else {
                                                player.sendMessage(ChatColor.RED + "Wrong usage, /editsign <0-3> <text>");
                                            }
                                        }
                                    }
                            }
                        } else {
                        sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!.");
                    }
                }
            }
        return false;
    }
}
