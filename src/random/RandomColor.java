package random;

import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;

import main.ColorPanel;

public class RandomColor {
	
	public static ColorPanel randomColor(JPanel panel) {
		
		char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		
		String randomhexcode = new String("");
		
		Random random = new Random();
		
		for (int i = 0; i < 6; i++) {
			
			randomhexcode = randomhexcode.concat(String.valueOf(chars[random.nextInt(chars.length)]));
			
		}
		
		int rgb = Integer.parseInt(randomhexcode, 16);
		
		Color color = new Color(rgb);
		
		return new ColorPanel(String.format("#%s", randomhexcode), color, panel);
		
	}

}
