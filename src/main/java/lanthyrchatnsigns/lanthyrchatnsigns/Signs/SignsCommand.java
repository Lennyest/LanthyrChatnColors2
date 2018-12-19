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

    //This is a method to check if a string is an integer, Integer.parseInt is the main component which does the work.
    private static boolean isInt(String s) {
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
            //^^ We only want to run the command if it is a player.
            if (command.getName().equalsIgnoreCase("editsign")) {
                //Check if it matches command name.
                if (sender.hasPermission("lanthyr.signs.edit")) {
                    Player player = (Player) sender;
                    Set<Material> tmpSet = new HashSet<>();

                    if (args.length >= 1) {
                        //Adds a space, then appends the arguments at the end of the space.
                        //This will create a normal sentence.
                        StringBuilder message = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            message.append(" ").append(args[i]);
                        }
                        /*
                          Checks if hit block is air and max distance 100, should reduce.
                          tmpSet is a reference to the tmpSet material hashset, think of it as a collection of all materials but you have to add them yourself.
                        */
                        tmpSet.add(Material.AIR);
                        if (player.getTargetBlock(tmpSet, 100).getType() == (Material.SIGN)) {

                            Block b = player.getTargetBlock(tmpSet, 8);
                            /*
                            Defines sign from the block we got above.
                            getState gets the state of the block, which is a sign.
                            */
                            Sign sign = (Sign) b.getLocation().getBlock().getState();

                            //Parse the first argument, check if it is a number.
                            if ((Integer.parseInt(args[0])) <= 3) {
                                if (isInt(args[0])) {
                                    //If it is, set the lines and translate colorcodes.
                                    sign.setLine(Integer.parseInt(args[0]), ChatColor.translateAlternateColorCodes('&', message.toString()));
                                    //update sign.
                                    sign.update();
                                } else if (!isInt(args[0])) {
                                    //If it isn't a number, send a message.
                                    player.sendMessage(ChatColor.RED + "Wrong usage, /editsign <0-3> <text>");
                                }
                            }
                        }

                        //Do the same as above, but with wall signs as they are two different blocks.
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
