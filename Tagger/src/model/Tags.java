package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;

public class Tags {
	
	private ArrayList<String> defaultTags = new ArrayList<String>();
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
	}
	
	public ArrayList<String> getDefaultTags()
	{
		return defaultTags;
	}
	
	public void addTag(String tag)
	{
		//Makes sure there are no duplicate tags
		removeTag(tag);
		
		
		outputTags += (" " + tag);
		outputTags = outputTags.trim();
		System.out.println(outputTags);
	}

	public void removeTag(String tag)
	{
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
		ArrayList<String> myTags = new ArrayList<String>(defaultTags);
		
		//Open the mp3 metadata
		Mp3File me = null;
		try {
			me = new Mp3File(filename);
		} catch (Exception e) {
			e.printStackTrace();
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
		Scanner input = new Scanner(origComm);
		while(input.hasNext())
		{
			myTags.add(input.next());
		}
		input.close();
		
		//Get rid of duplicates
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(myTags);
		myTags.clear();
		myTags.addAll(hs);
		
		for(String a: myTags)
			System.out.println(a);
		
		return defaultTags;
	}
}
