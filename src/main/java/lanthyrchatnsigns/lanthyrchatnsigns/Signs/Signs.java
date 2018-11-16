package lanthyrchatnsigns.lanthyrchatnsigns.Signs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Signs implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getPlayer().hasPermission("sign.color")) {
            for (int i = 0; i < 4; i++) { // Makes it so the player doesn't have to write on every line.

                String line = event.getLine(i);
                if (line != null && !line.equals("")) {
                    event.setLine(i, ChatColor.translateAlternateColorCodes('&', line));
                }
            }

        }
    }

    @EventHandler
    public void signInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

            if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
                return;
            }

            if (player.hasPermission("sign.edit")) {
                Block b = event.getClickedBlock();
                if (b.getType().equals(Material.WALL_SIGN) || b.getType().equals(Material.SIGN_POST)) {
                    Sign sign = (Sign) b.getState();


                }
            }
        }
    }
