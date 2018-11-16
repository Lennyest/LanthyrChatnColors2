package lanthyrchatnsigns.lanthyrchatnsigns;

import lanthyrchatnsigns.lanthyrchatnsigns.ChatSecurity.VersionTabCompleteCancel;
import lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat.Markdown;
import lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat.MarkdownQSelection;
import lanthyrchatnsigns.lanthyrchatnsigns.Signs.Signs;
import lanthyrchatnsigns.lanthyrchatnsigns.Signs.SignsCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LanthyrChatnSigns extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " <<< LanthyrChatnSigns has been enabled! >>>");

        this.getCommand("editsign").setExecutor(new SignsCommand());
        this.getCommand("chatcolor").setExecutor(new MarkdownQSelection());

        this.getServer().getPluginManager().registerEvents(new Markdown(), this);
        this.getServer().getPluginManager().registerEvents(new Signs(), this);
        this.getServer().getPluginManager().registerEvents(new VersionTabCompleteCancel(), this);


    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "LanthyrChatnSigns has been disabled!");
    }

}
