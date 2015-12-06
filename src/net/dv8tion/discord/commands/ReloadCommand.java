package net.dv8tion.discord.commands;

import java.util.Arrays;
import java.util.List;

import me.itsghost.jdiscord.events.UserChatEvent;
import net.dv8tion.discord.Bot;
import net.dv8tion.discord.Permissions;

public class ReloadCommand extends Command
{
    @Override
    public void onChat(UserChatEvent e)
    {
        if (!containsCommand(e.getMsg()))
            return;

        if (!Permissions.getPermissions().isOp(e.getUser()))
        {
            sendMessage(e, Permissions.OP_REQUIRED_MESSAGE);
            return;
        }

        sendMessage(e, "Restarting the bot, one moment...");
        System.exit(Bot.RESTART_EXITCODE);
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(".reload");
    }

    @Override
    public String getDescription()
    {
        return "Kills the current instance and launches a fresh instance of this bot.";
    }

    @Override
    public String getName()
    {
        return "Bot Reload/Restart Command";
    }

    @Override
    public String getUsageInstructions()
    {
        return ".reload\n"
                + "If you are running the bot without the bootloader, this command will be disabled.\n"
                + "The bootloader is required to relaunch the bot.";
    }
}
