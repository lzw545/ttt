import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;


public class TextComp extends JComponent
{
	private String[] textToType;
	private String[] textSoFar;
	private static int currLineTyping;
	private static String WPM;
	private static String acc;
	
	public TextComp()
	{
		textToType = new String[8];
		textSoFar = new String[8];
		currLineTyping = 0;
		for (int i = 0; i < 8; i++)
		{
			textToType[i] = "";
			textSoFar[i] = "";
		}
		
	}
	
	public void paintComponent(Graphics g)
	{
		System.out.println("paintComp called\n");
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		
		
		for (int i = 0; i < textToType.length; i++)
			g2.drawString(textToType[i], 10, 20+46*i);
		
		for (int i = 0; i < textSoFar.length; i++)
			g2.drawString(textSoFar[i], 10, 40+46*i);
		
		g2.drawString(WPM, 750, 100);
		g2.drawString(acc, 750, 200);
		
	}
	
	public void addText(String line, int lineNum)
	{
		textToType[lineNum] = line;
		repaint();
	}
	
	public void addChar(char charTyped)
	{
		if (textToType[currLineTyping].length() == textSoFar[currLineTyping].length())
			currLineTyping++;
		textSoFar[currLineTyping] = textSoFar[currLineTyping] + charTyped;
		repaint();
	}
	
	public void printTypingInfo(int wpm, int accuracy)
	{
		WPM = "WPM: " + wpm;
		acc = "Accuracy: " + accuracy + "%";
	}
}

