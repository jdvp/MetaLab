package model;

import java.io.File;

import entagged.audioformats.AudioFile;
import entagged.audioformats.AudioFileIO;
import entagged.audioformats.Tag;
import entagged.audioformats.exceptions.CannotReadException;
import entagged.audioformats.exceptions.CannotWriteException;

public class TagAdder {

	public TagAdder() throws CannotWriteException, CannotReadException
	{
		AudioFile audioFile = AudioFileIO.read(new File("C:/Users/JD Porterfield/Documents/Summer 2014/Kerr PSP/PSP/Music/Rap/001 Ridin' Dirty.mp3")); //Reads the given file.
		int bitrate = audioFile.getBitrate(); //Retreives the bitrate of the file.
		Tag t = audioFile.getTag();
		String artist =  t.getFirstArtist();//Retreive the artist name.
		audioFile.getTag().setComment("Yee yee muthafucka"); //Sets the genre to Prog. Rock, note the file on disk is still unmodified.
		audioFile.commit();
	}
	
}
