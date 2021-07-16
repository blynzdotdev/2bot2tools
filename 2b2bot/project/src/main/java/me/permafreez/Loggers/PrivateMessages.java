package me.permafreez.Loggers;

import java.io.FileWriter;
import java.io.PrintWriter;

import me.permafreez.Main;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PrivateMessages extends ListenerAdapter{

	// MessageReceived method
	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
		
		// Getting message
		String args = event.getMessage().getContentRaw();
		// Author's id
		String authorId = event.getAuthor().getId().toString();
		// Author's name
		String authorName = event.getAuthor().getAsTag().toString();
		
		// Testing for author being yourself / being command
		if (!authorId.equals("859501321404416050") && !args.startsWith(Main.prefix)){
		
		// Saving to file
		try {
		FileWriter fout = new FileWriter(Main.folder + "dc-logs/PrivateMessages.txt", true);
		PrintWriter fileout =  new PrintWriter(fout, true);
		
		fileout.print(authorName + ": " + event.getMessage().getContentRaw() + "\n");
		
		fout.close();
		fileout.close();
		
		// Sending console message 
		System.out.println("Just saved a message private message!"); } catch (Exception e) {}
		}
	}
}
