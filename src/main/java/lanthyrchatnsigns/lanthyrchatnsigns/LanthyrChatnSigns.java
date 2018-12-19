package lanthyrchatnsigns.lanthyrchatnsigns;

import lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat.Markdown;
import lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat.MarkdownHerochatListener;
import lanthyrchatnsigns.lanthyrchatnsigns.Signs.SignsCommand;
import lanthyrchatnsigns.lanthyrchatnsigns.TabCancel.VersionTabCompleteCancel;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LanthyrChatnSigns extends JavaPlugin {

    /*
    Source code by md5
    Edited by Lenny & GreatThane to work with Herochat.
     */

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + " <<< LanthyrChatnSigns has been enabled! >>>");

        this.getCommand("editsign").setExecutor(new SignsCommand());

        this.getServer().getPluginManager().registerEvents(new Markdown(), this);
        this.getServer().getPluginManager().registerEvents(new MarkdownHerochatListener(), this);
        this.getServer().getPluginManager().registerEvents(new VersionTabCompleteCancel(), this);
    }


}
