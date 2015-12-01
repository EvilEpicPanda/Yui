package net.dv8tion.discord.commands;

import java.util.Arrays;
import java.util.List;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;
import net.dv8tion.discord.util.GoogleSearch;

import org.apache.commons.lang3.StringUtils;

public class SearchCommand extends Command
{
	@Override
	public void onChat(UserChatEvent e) {
		String filter = null;
		String[] splitMessage = commandArgs(e.getMsg());
		switch (splitMessage[0]) {
			case ".google":
			case ".g":
				break;
			case ".wiki":
				filter = "wiki";
				break;
			case ".urban":
				filter = "site:urbandictionary.com";
				break;
			default:
				return;
		}

		GoogleSearch search = new GoogleSearch(
		        String.format("%s+%s",
		                StringUtils.join(splitMessage, "+", 1, splitMessage.length),
		                filter));

		e.getGroup().sendMessage(new MessageBuilder()
            .addUserTag(e.getUser(), e.getGroup())
            .addString(": " + search.getSuggestedReturn())
            .build());
	}

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(".google", ".g", ".wiki", ".urban");
    }

    @Override
    public String getDescription()
    {
        return "Allows you to search Google. The aliases search Wiki sites (not wikipedia exclusive) and Urban Dictionary.";
    }

    @Override
    public String getName()
    {
        return "Google Search";
    }

    @Override
    public String getUsageInstructions()
    {
        return ".google *<search terms>  **OR** .wiki *<search terms>*  **OR**  .urban *<search terms>*\n";
    }
}