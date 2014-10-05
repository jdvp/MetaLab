package model;

import java.io.File;

import entagged.audioformats.AudioFile;
import entagged.audioformats.AudioFileIO;
import entagged.audioformats.exceptions.CannotReadException;
import entagged.audioformats.exceptions.CannotWriteException;

public class TagAdder {

	AudioFile audioFile = null;
	String song = "";
	
	public TagAdder(String song)
	{
		this.song = song;
		//Retrieve song
		try 
		{
			audioFile = AudioFileIO.read(new File(song));
		}
		catch(CannotReadException e)
		{
			System.err.println("Error reading song " + song);
		}
	}
	
	public void commitComment(String comment)
	{
		//Set comment field
		audioFile.getTag().setComment(comment); //Sets the genre to Prog. Rock, note the file on disk is still unmodified.
		
		//Commit the change
		try
		{
			audioFile.commit();
		}
		catch(CannotWriteException e)
		{
			System.err.println("Error writing to song " + song);
		}
	}
}
