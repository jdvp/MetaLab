package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;

public class Tags {
	
	private ArrayList<String> defaultTags = new ArrayList<String>();
	private ArrayList<String> allTags = new ArrayList<String>();
	private ArrayList<String> currentTags = new ArrayList<String>();
	private static String outputTags = "";
	
	public Tags()
	{
		defaultTags.add("Chill");
		defaultTags.add("Workout");
		defaultTags.add("Upbeat");
		defaultTags.add("Downbeat");
		defaultTags.add("Driving");
		defaultTags.add("Studying");
		defaultTags.add("Vocals");
		defaultTags.add("Happy");
		defaultTags.add("Sad");
		allTags = new ArrayList<String>(defaultTags);
	}
	
	public ArrayList<String> getDefaultTags()
	{
		return defaultTags;
	}
	
	public ArrayList<String> getAllTags()
	{
		return allTags;
	}
	
	public ArrayList<String> getCurrentTags()
	{
		return currentTags;
	}
	
	public void addTag(String tag)
	{
		//Makes sure there are no duplicate tags
		removeTag(tag);
		
		currentTags.add(tag);
		allTags.add(tag);
		outputTags += (" " + tag);
		outputTags = outputTags.trim();
		System.out.println(outputTags);
	}

	public void removeTag(String tag)
	{
		allTags.remove(tag);
		currentTags.remove(tag);
		System.out.println(tag);
		outputTags = outputTags.replaceAll(tag,"");
		outputTags = outputTags.replaceAll("\\s+", " ").trim();
		System.out.println(outputTags);
	}
	
	public String outputTag()
	{
		return outputTags;
	}
	
	public ArrayList<String> checkOriginalTags(String filename)
	{
		ArrayList<String> myTags = new ArrayList<String>();
		
		//Open the mp3 metadata
		Mp3File me = null;
		try {
			me = new Mp3File(filename);
		} catch (Exception e) {
			return new ArrayList<String>();
		}
		
		
		//Get the mp3 tag
		ID3v2 tag;
		if (me.hasId3v2Tag()) {
		  tag = me.getId3v2Tag();
		} else {
		  // mp3 does not have an ID3v2 tag, let's create one..
		  tag = new ID3v24Tag();
		  me.setId3v2Tag(tag);
		}
		
		//Parse the comments for separate words
		String origComm = tag.getComment();
		System.out.println("BEFORE");
		System.out.println(origComm);
		System.out.println("AFTER");
		Scanner input = new Scanner(origComm);
		while(input.hasNext())
		{
			String token =input.next();
			if(token.trim().length()>0)
				myTags.add(token);
		}
		input.close();
		
		//Get rid of duplicates
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(myTags);
		myTags.clear();
		myTags.addAll(hs);
		
		hs.addAll(allTags);
		allTags.clear();
		allTags.addAll(hs);
		
		currentTags.addAll(myTags);
		
		if (myTags.isEmpty())
			return defaultTags;
		return myTags;
	}
	
	public String getTitle(String filename)
	{
		Mp3File me = null;
		try {
			me = new Mp3File(filename);
		} catch (Exception e) {
			return "";
		}
		
		
		//Get the mp3 tag
		ID3v1 tag;
		if (me.hasId3v1Tag()) {
		  tag = me.getId3v1Tag();
		} else {
		  // mp3 does not have an ID3v2 tag, let's create one..
		  tag = new ID3v1Tag();
		  me.setId3v1Tag(tag);
		}
		
		String title = tag.getTrack();
		System.out.println(title);
		
		return title;
	}
}
