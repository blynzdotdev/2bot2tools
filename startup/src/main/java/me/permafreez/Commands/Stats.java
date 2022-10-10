package me.permafreez.Commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Stats extends ListenerAdapter {
	
	// Getting stats method
	private String[] getStats(String[] args) {
		try {
			
			// Getting document
			final Document doc = Jsoup.connect("https://api.2b2t.dev/stats?username=" + args[1]).ignoreContentType(true).get();
			
			// Cleaning up document
			String stats = doc.select("body").text();
			stats = stats.replace("[{", "");
			stats = stats.replace("}]", "");
			stats = stats.replace("\"", "");
			stats = stats.replace(":", ": ");
			String[] statsA = stats.split(",");
			
			// Returning data; Answering the summon
			return statsA;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// ERROR sign
		String[] error = null;
		return error;
	}
	
	// Setting variables
	Main main = new Main();
	String CommandName = "Stats";
	
	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Checking for command
		if (args[0].equals(Main.prefix + "stats")) {
			
			// Building embed
			EmbedBuilder stats = new EmbedBuilder();
			stats.setColor(main.uColor);
				
				// If the command was perfect
				if (args.length == 2 && getStats(args)[0].equals("[]") == false) {
					
					// Transforming method array to simple array
					String[] statsA = getStats(args);
					
					// Editing embed
					stats.setTitle("Infos about " + args[1]);
					stats.setThumbnail("https://cravatar.eu/helmavatar/" + args[1] + "/190.png");
					
					// Adding embed fields with the simple array
					for (String i : statsA) {
						innerloop:
						// Removing useless info
						if (i.startsWith("id") || i.startsWith("adminlevel")) {
							// Breaking out of if-else
							break innerloop;
						} // Adding the embed without the usless info
						else {
							
						// Splitting info in two; articulating it
						String[] dString = i.split(" ");
						stats.addField(dString[0], dString[1], false);
						}
					}
					
					// Answering command
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(stats.build()).queue();
				} // User not found but command being used properly
				else if (args.length == 2) {
					// Editing embed
					stats.setTitle("User wasn't found");
					stats.setDescription("I've never seen **" + args[1] + "**");
					stats.setThumbnail(main.icon);
					stats.setColor(main.eColor);
					
					// Answering command
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(stats.build()).queue();
				}
				// Answering if command was used incorrectly
				if (args.length == 1) {
					
					// Answering command
					event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
					event.getAuthor().openPrivateChannel().complete()
					.sendMessage(":x: Error occured while using command! Use the command like this: \"!stats PLAYER\"").queue();
				}
				
			// Sending command infos back
			main.CommandInfo(CommandName, event);
			
			// Clearing embed
			stats.clear();
		}
	}

}
