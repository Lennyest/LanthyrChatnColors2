package lanthyrchatnsigns.lanthyrchatnsigns;

import lanthyrchatnsigns.lanthyrchatnsigns.ChatSecurity.VersionTabCompleteCancel;
import lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat.Markdown;
import lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat.MarkdownHerochatListener;
import lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat.MarkdownVanillaListener;
import lanthyrchatnsigns.lanthyrchatnsigns.Signs.SignsCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LanthyrChatnSigns extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + " <<< LanthyrChatnSigns has been enabled! >>>");

        this.getCommand("editsign").setExecutor(new SignsCommand());

        this.getServer().getPluginManager().registerEvents(new Markdown(), this);
        this.getServer().getPluginManager().registerEvents(new VersionTabCompleteCancel(), this);
        if (this.getServer().getPluginManager().isPluginEnabled("Herochat")) {
            this.getServer().getPluginManager().registerEvents(new MarkdownHerochatListener(), this);
        } else {
            this.getServer().getPluginManager().registerEvents(new MarkdownVanillaListener(), this);
        }


    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "LanthyrChatnSigns has been disabled!");
    }

}
