import java.io.FileNotFoundException;

public class TypingTutor 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Game.initializeScreen();
		
		Game.openFile("text.txt");
		
		Game.printText();
		
		Game.play();
		
	}
}
