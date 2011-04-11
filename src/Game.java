import java.awt.Font;
import java.awt.TextComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;



public class Game 
{
	private static Scanner in;
	private static JFrame frame;
	private static TextComp comp;
	private static int charNum;
	private static int charsWrong;
	private static String bigString;
	
	
	static class KeyPressListener implements KeyListener
	{
		//Ignored methods
		public void keyPressed(KeyEvent arg0) {}
		public void keyReleased(KeyEvent arg0) {}

		@Override
		public void keyTyped(KeyEvent keyTyped) 
		{
			char typedKey = keyTyped.getKeyChar();
			if (typedKey == bigString.charAt(charNum))
				System.out.println("Right");
			else
			{
				charsWrong++;
				System.out.println("Wrong");
			}
			comp.addChar(typedKey);
			charNum++;
		}
	}
	
	public static void initializeScreen()
	{
		frame = new JFrame();
		
		frame.setSize(900,410);
		frame.setTitle("Typing Tutor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public static void openFile(String fileName) throws FileNotFoundException
	{
		FileReader reader = new FileReader(fileName);
		in = new Scanner(reader);		
	}
	
	public static void printText()
	{
		comp = new TextComp();
		frame.add(comp);
		comp.setFont(new Font("Serif", Font.BOLD,  16));
		
		frame.setVisible(true);
		
		ArrayList<String> words = new ArrayList<String>();
		String[] lines = new String[8];
		bigString = "";
		
		while (in.hasNext())
		{
			String next = in.next();
			words.add(next);
			bigString = bigString + next + " ";
		}
		
		int wordIndex = 0;
		for (int i = 0; i < 8; i++)
		{
			 int charsInLine = 0;
			 String line = "";
			 while (true)
			 {
				 String word = words.get(wordIndex);
				 int wordLen = word.length()+1;
				 if (charsInLine + wordLen > 100)
					 break;
				 else
				 {
					 line = line + word + " ";
					 wordIndex++;
					 charsInLine += wordLen;
				 }
			 }
			 lines[i] = line;
		}
				

		for (int i = 0; i < 8; i++)
			comp.addText(lines[i], i);
	}

	public static void play()
	{
		charNum = 0;
		long start = System.currentTimeMillis();
		
		KeyListener listener = new KeyPressListener();
		frame.addKeyListener(listener);
		
		boolean done = false;
		while (!done)
		{
			float timeSoFar = (System.currentTimeMillis()-start)/1000F;
						
			if (timeSoFar > 60)
				done = true;
			else
				comp.printTypingInfo((int) ((charNum/5)/(timeSoFar/60)), (int) 100 * 
							          (charNum-charsWrong)/(charNum+1));
		}
		frame.removeKeyListener(listener);
		
	}
}
