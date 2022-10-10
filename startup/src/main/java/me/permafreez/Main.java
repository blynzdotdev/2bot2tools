package me.permafreez;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.security.auth.login.LoginException;

import org.jsoup.Jsoup;

import me.permafreez.Commands.Alternatives;
import me.permafreez.Commands.Help;
import me.permafreez.Commands.Info;
import me.permafreez.Commands.LastDeath;
import me.permafreez.Commands.LastKD;
import me.permafreez.Commands.LastKill;
import me.permafreez.Commands.LongId;
import me.permafreez.Commands.Queue;
import me.permafreez.Commands.Revaler;
import me.permafreez.Commands.Seen;
import me.permafreez.Commands.Stats;
import me.permafreez.Commands.Tablist;
import me.permafreez.Commands.Thirdparty;
import me.permafreez.Loggers.Fucker;
import me.permafreez.Loggers.Fucker2;
import me.permafreez.Loggers.PrivateMessages;
import me.permafreez.Loggers.ServerJL;
import me.permafreez.player.Register;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Main {
	
	// InterClass variables
	public static Color uColor = new Color(0x9ccfe6);
	public static Color eColor = new Color(0xd4180b);
	public static String icon = "https://cravatar.eu/helmavatar/troll/190.png";
	public static String botName = "2Bot";
	public static String prefix = "!";
	public static String folder = "./";
	
	
	// CommandInfo: Sends info about people using commands
	
	public static void CommandInfo(String CommandName, MessageReceivedEvent event) {
		// Was sent on server
		try {
			// Console
	    	System.out.println(CommandName + " command was used on '" 
	    			+ event.getGuild().getName() 
	    			+ "/" 
	    			+ event.getChannel().getName()
	    			+ "' by: " + event.getAuthor().getAsTag() 
	    			+ "!");
	    	// File log
	    	try {
	    	FileWriter fout = new FileWriter(Main.folder + "dc-logs/CommandsUsed.txt", true);
			PrintWriter fileout =  new PrintWriter(fout, true);
			
			fileout.print(CommandName + " command was used on '" 
	    			+ event.getGuild().getName() 
	    			+ "/" 
	    			+ event.getChannel().getName()
	    			+ "' by: " + event.getAuthor().getAsTag() 
	    			+ "! \n");
			
			fout.close();
			fileout.close(); } catch (Exception e) {}
	    	return;
		}
		// Was sent in private
		catch (IllegalStateException e) {
			// Console
			System.out.println(CommandName + " command was used by: " 
	    			+ event.getAuthor().getAsTag() 
	    			+ "!");
			// File log
	    	try {
	    	FileWriter fout = new FileWriter(Main.folder + "dc-logs/CommandsUsed.txt", true);
			PrintWriter fileout =  new PrintWriter(fout, true);
			
			fileout.print(CommandName + " command was used by: " 
	    			+ event.getAuthor().getAsTag() 
	    			+ "! \n");
			
			fout.close();
			fileout.close(); } catch (Exception e2) {}
	    return;
		}
	}
	
	// Initialization
	public static void main(String[] args) throws LoginException {
		
		JDABuilder jda = JDABuilder.createDefault(Token.uToken);
		jda.setStatus(OnlineStatus.ONLINE);
		jda.setActivity(Activity.playing("2b2t"));
		// Event Listeners
		jda.addEventListeners(new Info());
		jda.addEventListeners(new Help());
		jda.addEventListeners(new Queue());
		jda.addEventListeners(new Thirdparty());
		jda.addEventListeners(new Seen());
		jda.addEventListeners(new Stats());
		jda.addEventListeners(new Fucker());
		jda.addEventListeners(new LastKD());
		jda.addEventListeners(new Tablist());
		jda.addEventListeners(new LastDeath());
		jda.addEventListeners(new LastKill());
		jda.addEventListeners(new Revaler());
		jda.addEventListeners(new Alternatives());
		jda.addEventListeners(new Fucker2());
		jda.addEventListeners(new PrivateMessages());
		jda.addEventListeners(new ServerJL());
		jda.addEventListeners(new Register());
		//jda.addEventListeners(new LongId());
		// Build
		jda.build();
		
		// Three minutes loop - tablist image
		boolean loop = true;
		while (loop == true) {
			
			try {
				// Making bytestrucure of image
				final byte[] doc = Jsoup.connect("https://tab.2b2t.dev").ignoreContentType(true).execute().bodyAsBytes();
				
				// Saving image using the bytes
				FileOutputStream out = (new FileOutputStream(new java.io.File("tablist.png")));
				out.write(doc);
				out.close();
				
				// If something goes wrong
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			
			// Sleep
			try {
			Thread.sleep(180000);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}
}