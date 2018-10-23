package eric.peterson.myCommonMethods;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Eric Peterson
 * Date Created: 10/09/18
 * Date Updated: 10/16/18
 */
public class FileIO
{
	/**
	 * Creates a <code>Clip</code> of the audio file represented by the <code>fileName</code> argument.
	 *
	 * @param requester
	 * 		The <code>Object</code> requesting the audio file to be played.
	 * @param fileName
	 * 		The path of the file containing the requested audio.
	 *
	 * @return A <code>Clip</code> of the requested audio file.
	 */
	public static Clip playClip(Object requester, String fileName)
	{
		Clip clip = null;
		try
		{
			URL url = requester.getClass().getResource(fileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(IOException e)
		{
			String msg = "File " + fileName + " could not be opened.";
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(UnsupportedAudioFileException e)
		{
			String msg = "File " + fileName + " is not a supported file type.";
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(LineUnavailableException e)
		{
			String msg = "File " + fileName + " could not be opened at this time.";
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return clip;
	}
	
	/**
	 *
	 * @param requester
	 * @param fileName
	 * @return
	 */
	public static BufferedImage readImageFile(Object requester, String fileName)
	{
		BufferedImage image = null;
		try
		{
			InputStream input = requester.getClass().getResourceAsStream(fileName);
			image = ImageIO.read(input);
		}
		catch(IOException e)
		{
			String msg = "File " + fileName + " could not be opened.";
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return image;
	}
}
