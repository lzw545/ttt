import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyPressListener implements KeyListener
	{
		//Ignored methods
		public void keyPressed(KeyEvent arg0) {}
		public void keyReleased(KeyEvent arg0) {}

		@Override
		public void keyTyped(KeyEvent keyTyped) 
		{
			char typedKey = keyTyped.getKeyChar();
		}
	}