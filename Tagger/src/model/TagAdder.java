package model;

import java.io.RandomAccessFile;

import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v2_3;

public class TagAdder {

	MP3File myFile = null;
	ID3v2_3 myTag = null;
	String song = "";
	
	public TagAdder(String song)
	{
		this.song = song;
		
		/*
		this.song = song;
		//Retrieve song
		try 
		{
			audioFile = AudioFileIO.read(new File(song));
		}
		catch(CannotReadException e)
		{
			System.err.println("Error reading song " + song);
		}*/
	}

	public void commitComment(String comment)
	{
		
		myTag = new ID3v2_3();
		myTag.setSongComment(comment);
		try 
		{
			RandomAccessFile me = new RandomAccessFile(song, "rw");
			myTag.write(me);
			me.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
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
		}*/
	}
}
