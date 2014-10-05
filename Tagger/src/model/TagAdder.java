package model;

import java.io.RandomAccessFile;

import org.farng.mp3.MP3File;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.ID3v2_3;

public class TagAdder {

	MP3File myFile = null;
	ID3v2_3 myTag = null;
	String song = "";
	
	public TagAdder(String song)
	{
		this.song = song;
	}

	public void commitComment(String comment)
	{
		myTag = new ID3v2_3();
		ID3v1 myV1Tag = new ID3v1();
		
		myTag.setSongComment(comment);
		myV1Tag.setComment(comment);
		
		try 
		{
			RandomAccessFile me = new RandomAccessFile(song, "rw");
			MP3File myFile = new MP3File(song);
			
			AbstractID3v2 myID = myFile.getID3v2Tag();
			myTag.append(myID);
			myTag.write(me);
			
			ID3v1 my1 = myFile.getID3v1Tag();
			myV1Tag.append(my1);
			myV1Tag.write(me);
			
			//myTag.write(me);
			//myV1Tag.write(me);
			me.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
