package main;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class Pick {
	
	public static ColorPanel pickColorMakePanel(JPanel panel) {
		

		Color color = JColorChooser.showDialog(panel, "Colors", new Color(0xffffff));

		if (color == null) return null;
		
		
		String hex = Integer.toHexString(color.getRGB());

		hex = String.format("#%s", hex.substring(2, hex.length()));
		
		color = ColorPanel.removeTrans(color);
		
		ColorPanel cp = new ColorPanel(hex, color, panel);
		
		return cp;
		
	}
	
	

}
