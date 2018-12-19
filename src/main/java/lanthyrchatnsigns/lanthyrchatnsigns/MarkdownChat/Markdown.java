package lanthyrchatnsigns.lanthyrchatnsigns.MarkdownChat;

import com.dthielke.herochat.Channel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Markdown implements Listener {

    public static String parseMarkdown(Player player, String message, Channel channel) {
        String translated = message;

            /*
            Translated = message.
            quot = regex for checking if it matches at the start and end of the message.
            pre = Color and what should be replaced, colorcharacters are defined below, Color_CHAR is & or § depending on which you use.
            suf = the replacement.
             */
        translated = replaceWith(translated, "(?<!\\\\)\\*\\*", ChatColor.COLOR_CHAR + "z", ChatColor.COLOR_CHAR + "Z");
        translated = replaceWith(translated, "(?<!\\\\)\\*", ChatColor.COLOR_CHAR + "x", ChatColor.COLOR_CHAR + "X");
        translated = replaceWith(translated, "(?<!\\\\)__", ChatColor.COLOR_CHAR + "v", ChatColor.COLOR_CHAR + "V");
        translated = replaceWith(translated, "(?<!\\\\)_", ChatColor.COLOR_CHAR + "q", ChatColor.COLOR_CHAR + "Q");
        translated = replaceWith(translated, "(?<!\\\\)~~", ChatColor.COLOR_CHAR + "m", ChatColor.COLOR_CHAR + "M");
        translated = replaceWith(translated, "(?<!\\\\)~", ChatColor.COLOR_CHAR + "w", ChatColor.COLOR_CHAR + "W");

        //Replacement of the regex, target with replacement.
        translated = translated.replace("\\*", "*").replace("\\_", "_").replace("\\~", "~");

        // Makes the message into a string array, add a space before and split. splits the "" with color character.
        String[] parts = (" " + translated).split("" + ChatColor.COLOR_CHAR);
        StringBuilder builder = new StringBuilder();
        //If string is empty, continue.
        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }
            //Colorcharacter defined at 0.
            char colorCharacter = part.charAt(0);
            ChatColor color = ChatColor.getByChar(colorCharacter);

            String colors = ChatColor.getLastColors(builder.toString());
            if (color != null) {
                StringBuilder colorBuilder = new StringBuilder();
                for (String cc : colors.split(ChatColor.COLOR_CHAR + "")) {
                    if (cc.isEmpty()) {
                        continue;
                    }
                    if (ChatColor.getByChar(cc.charAt(0)).isFormat()) {
                        colorBuilder.append(ChatColor.COLOR_CHAR + cc);
                    }
                }
                builder.append(color + colorBuilder.toString() + ChatColor.RESET);
            } else {
                if (colorCharacter == 'z') {
                    builder.append(ChatColor.BOLD);
                } else if (colorCharacter == 'x') {
                    builder.append(ChatColor.ITALIC);
                } else if (colorCharacter == 'v') {
                    builder.append(ChatColor.UNDERLINE);
                } else if (colorCharacter == 'q') {
                    builder.append(ChatColor.ITALIC);
                } else if (colorCharacter == 'm') {
                    builder.append(ChatColor.STRIKETHROUGH);
                } else if (colorCharacter == 'w') {
                    builder.append(ChatColor.MAGIC);
                } else if (colorCharacter == 'Z') {
                    colors = colors.replace(ChatColor.BOLD.toString(), "");
                } else if (colorCharacter == 'X') {
                    colors = colors.replace(ChatColor.ITALIC.toString(), "");
                } else if (colorCharacter == 'V') {
                    colors = colors.replace(ChatColor.UNDERLINE.toString(), "");
                } else if (colorCharacter == 'Q') {
                    colors = colors.replace(ChatColor.ITALIC.toString(), "");
                } else if (colorCharacter == 'M') {
                    colors = colors.replace(ChatColor.STRIKETHROUGH.toString(), "");
                } else if (colorCharacter == 'W') {
                    colors = colors.replace(ChatColor.MAGIC.toString(), "");
                }
                if (Character.isUpperCase(colorCharacter)) {
                    if (channel.getName().equals("Roleplay")) {
                        builder.append(ChatColor.RESET + colors);
                    } else if (channel.getName().equalsIgnoreCase("Emote")) {
                        builder.append(ChatColor.AQUA + colors);
                    } else if (channel.getName().equalsIgnoreCase("Roleplay2")) {
                        builder.append(ChatColor.RED + colors);
                    }
                }
            }
            if (part.length() > 1) {
                builder.append(part.substring(1));
            }
        }
        return builder.toString();
    }

    //Replace method itself, takes in 4 arguments which is used to replace, returns part which is the message.
    private static String replaceWith(String message, String quot, String pre, String suf) {
        String part = message;
        for (String str : getMatches(message, quot + "(.+?)" + quot)) {
            part = part.replaceFirst(quot + Pattern.quote(str) + quot, pre + str + suf);
        }
        return part;
    }

    //getMatches = amount of matches in the string and regex.
    private static List<String> getMatches(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(1));
        }
        return matches;
    }


}