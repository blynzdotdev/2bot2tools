package me.permafreez.player;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.permafreez.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Register extends ListenerAdapter {

	String CommandName = "Register";
	
	public static int wasOnline(String[] args) {
		try {
			
			// Getting document
			final Document doc = Jsoup.connect("https://api.2b2t.dev/seen?username=" + args[1]).ignoreContentType(true).get();
			
			String normal = doc.select("body").text();
			if (normal.equals("[]")) {
				int output = 0;
				return output;
			} else {
				int output = 1;
				return output;
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			int output = 0;
			return output;
		}
	}
	
	public void insert(String dc, String mc, Connection conn, GuildMessageReceivedEvent event) {
		String sql = "INSERT INTO register(dc,mc,chid) VALUES(?,?,?)";
		String update = "update register set mc = ?, chid = ? where dc = ?";
		
		String chid = event.getChannel().getId();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, dc);
			pstmt.setString(2, mc);
			pstmt.setString(3, chid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			try (PreparedStatement pstmt = conn.prepareStatement(update)) {
			pstmt.setString(2, chid);
			pstmt.setString(3, dc);
			pstmt.setString(1, mc);
			pstmt.executeUpdate();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String args[] = event.getMessage().getContentRaw().split(" ");
		
		if (args[0].equalsIgnoreCase(Main.prefix + "register") || args[0].equalsIgnoreCase(Main.prefix + "reg")) {
			if (args.length == 2) {
				
				String url = "jdbc:sqlite:/home/dani1/test/test.sql";
				Connection conn = null;
				
				try {
					conn = DriverManager.getConnection(url);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				String dc = event.getAuthor().getAsTag();
				String mc = args[1];
				
				Register register = new Register();
				
				register.insert(dc, mc, conn, event);
				
				if (wasOnline(args) == 1) {
				EmbedBuilder reg = new EmbedBuilder();
				reg.setTitle("Player was found and is successfuly registered");
				reg.setDescription("You can thank me later");
				reg.setThumbnail("https://cravatar.eu/helmavatar/" + args[1] + "/190.png");
				reg.setColor(Main.uColor);
				
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(reg.build()).queue();
				reg.clear();
				} else if (wasOnline(args) == 0) {
					EmbedBuilder reg = new EmbedBuilder();
					reg.setTitle("Player wasn't found");
					reg.setDescription("This player wasn't seen on 2b2t in the past.");
					reg.setThumbnail("https://cravatar.eu/helmavatar/troll/190.png");
					reg.setColor(Main.eColor);
					
					event.getChannel().sendTyping().queue();
					event.getChannel().sendMessage(reg.build()).queue();
				}
				
			} else {
				event.getAuthor().openPrivateChannel().complete().sendTyping().queue();
				event.getAuthor().openPrivateChannel().complete()
				.sendMessage(":x: Error occured while using command! Use the command like this: \"!register PLAYERNAME\"").queue();
			}
			
			// CommandInfo
	    	System.out.println(CommandName + " command was used on '" 
	    			+ event.getGuild().getName() 
	    			+ "/" 
	    			+ event.getChannel().getName()
	    			+ "' by: " + event.getAuthor().getAsTag() 
	    			+ "!");
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
				fileout.close(); } catch(Exception e) {}
			
		}
	}
	
}
