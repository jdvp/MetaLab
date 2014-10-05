package model;

import java.util.ArrayList;

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
}
