package me.permafreez.Loggers;

import java.io.FileWriter;
import java.io.PrintWriter;

import me.permafreez.Main;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ServerJL extends ListenerAdapter {

	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		System.out.println("Just joined " + event.getGuild().getName());
		
    	try {
    	FileWriter fout = new FileWriter(Main.folder + "dc-logs/ServerJL.txt", true);
		PrintWriter fileout =  new PrintWriter(fout, true);
		
		fileout.print("Joined: " + event.getGuild().getName() + "\n");
		
		fout.close();
		fileout.close(); } catch (Exception e) {}
    	return;
	}
	
	@Override
	public void onGuildLeave(GuildLeaveEvent event) {
		System.out.println("Just left " + event.getGuild().getName());
		
    	try {
    	FileWriter fout = new FileWriter(Main.folder + "dc-logs/ServerJL.txt", true);
		PrintWriter fileout =  new PrintWriter(fout, true);
		
		fileout.print("Left: " + event.getGuild().getName() + "\n");
		
		fout.close();
		fileout.close(); } catch (Exception e) {}
    	return;
	}
}
