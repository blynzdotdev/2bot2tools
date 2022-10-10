package me.permafreez.Commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Queue extends ListenerAdapter {
	
	// Normal queue method (returning solid number)
	private static String Normal() {
		try {
			// Getting document
			final Document doc = Jsoup.connect("https://2b2t.io//api/queue?last=true").ignoreContentType(true).get();
			
			// Cleaning up document
			String normal = doc.select("body").text();
			normal = normal.replace("]", "");
			String[] normalA = normal.split(",");
			
			// Returning answer
			return normalA[1];
			
		} // Catch
		catch (Exception ex) {
			ex.printStackTrace();
		}
		// If something goes really wrong
		return "Normal queue scrape error";
	}
	
	// Prio queue method (returning solid number)
	private static String Prio() {
		try {
			
			// Getting document
			final Document doc = Jsoup.connect("https://api.2b2t.dev/prioq").ignoreContentType(true).get();
			
			// Cleaning up document
			String prio = doc.select("body").text();
			String[] prioA = prio.split(",");
			
			// Returning answer
			return prioA[1];
			
			} //Catch
			catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// error message
		return "Prio queue scrape error";
	}
	
	
	// Setting variables
	String CommandName = "Queue";
	Main main = new Main();
	
	// 
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting message
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		// Testing for command
		if (args[0].equalsIgnoreCase(Main.prefix + "queue") || args[0].equalsIgnoreCase(Main.prefix + "q")) {
			
			// Building embed
			EmbedBuilder queue = new EmbedBuilder();
			queue.setTitle("Current 2b2t queue");
			queue.setColor(main.uColor);
			queue.addField("Normal queue: ", Normal(), false);
			queue.addField("Prio queue: ", Prio(), false);
			queue.setThumbnail(main.icon);
			
			// Answering to command
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(queue.build()).queue();
			
			// Sending command info
			main.CommandInfo(CommandName, event);
			// Clearing Embed
			queue.clear();
		}
	}
}
