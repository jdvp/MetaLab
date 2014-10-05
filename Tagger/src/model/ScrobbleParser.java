package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScrobbleParser 
{
	String scrobblog = "C:/Users/JD Porterfield/AppData/Local/Last.fm/Last.fm Scrobbler.log";
	
	public ScrobbleParser()
	{
		
	}
	public String getLatestRequest()
	{
		File f = new File(scrobblog);
		Scanner in = null;
		try {
			in = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text ="";
		while(in.hasNextLine())
		{
			String temp = in.nextLine();
			if(temp.contains("PlayerCommandParser::extractTrack()"))
				text = temp;
		}
		in.close();
		int start = text.lastIndexOf("QUrl");
		int end = text.length();
		String qurl = text.substring(start,end);
		start = qurl.indexOf("file:///");
		end = qurl.lastIndexOf("\"");
		return qurl.substring(start+8,end);
	}
}
