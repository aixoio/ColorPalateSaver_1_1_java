
package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ColorPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4969701538840899224L;
	
	private String colorText;
	private Color color;
	
	private JLabel displayColorText;
	private JPanel displayColor;
	
	private JPanel superPanel;
	
	private JButton removeButton;
	
	public ColorPanel(String colorText, Color color, JPanel superPanel) {
		
		super();
		
		this.color = color;
		this.colorText = colorText;
		
		super.setLayout(new FlowLayout());
		
		this.displayColorText = new JLabel();
		this.displayColorText.setFont(Window.FONT.deriveFont(16f));
		this.displayColorText.setText(this.colorText);
		this.displayColor = new JPanel();
		
		this.removeButton = new JButton("Remove");
		this.removeButton.addActionListener(this);
		this.displayColor.setBackground(this.color);
		this.removeButton.setFocusable(false);
		super.add(this.displayColor);
		super.add(this.displayColorText);
		super.add(this.removeButton);
		
		
		this.superPanel = superPanel;
		
		
	}
	
	public void update(Window window) {
		
		this.displayColorText.setFont(Window.FONT.deriveFont(16f));
		this.removeButton.addActionListener(this);
		this.superPanel = window.getPanel();
		
	}
	
	
	public void remove() {
		
		Window.LIST.remove(this);
		this.superPanel.remove(this);
		this.superPanel.repaint();
		this.superPanel.revalidate();
		this.superPanel.updateUI();
		
	}
	
	public static void addANewColor(JPanel panel) throws NumberFormatException, IndexOutOfBoundsException {
		
		String color = JOptionPane.showInputDialog(panel, "Enter a hex code", "Input", JOptionPane.QUESTION_MESSAGE);
		
		if (color == null || color.equals("") || color.trim().isEmpty() || color.isEmpty()) return;
		
		String hexCodeWithNoAddedThings = color.replace("#", "").replace("0x", "").toLowerCase();
		
		char[] hexCodeWithNoAddedThingschars = hexCodeWithNoAddedThings.toCharArray();
		
		String newhexCodeWithNoAddedThings = new String("");
		
		for (int i = 0; i < 6; i++) {
			
			newhexCodeWithNoAddedThings = newhexCodeWithNoAddedThings.concat(String.valueOf(hexCodeWithNoAddedThingschars[i]));
			
		}
		
		hexCodeWithNoAddedThings = newhexCodeWithNoAddedThings;
		
		int colorrgbvalue = Integer.parseInt(hexCodeWithNoAddedThings, 16);
		
		String colorhextext = String.format("#%s", hexCodeWithNoAddedThings);
		
		ColorPanel cp = new ColorPanel(colorhextext, new Color(colorrgbvalue), panel);
		
		Window.LIST.add(cp);
		panel.add(cp);
		
		panel.repaint();
		panel.revalidate();
		
		
	}
	
	public static Color removeTrans(Color color1) {
		
		String hex = Integer.toHexString(color1.getRGB());

		hex = hex.substring(2, hex.length());
		
		char[] hexCodeWithNoAddedThingschars = hex.toCharArray();
		
		String newhexCodeWithNoAddedThings = new String("");
		
		for (int i = 0; i < 6; i++) {
			
			newhexCodeWithNoAddedThings = newhexCodeWithNoAddedThings.concat(String.valueOf(hexCodeWithNoAddedThingschars[i]));
			
		}
		
		int colorrgbvalue = Integer.parseInt(newhexCodeWithNoAddedThings, 16);
		
		
		return new Color(colorrgbvalue);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(this.removeButton)) {
			
			this.remove();
			
		}
		
	}


	public String getColorText() {
		return colorText;
	}


	public void setColorText(String colorText) {
		this.colorText = colorText;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public JLabel getDisplayColorText() {
		return displayColorText;
	}


	public void setDisplayColorText(JLabel displayColorText) {
		this.displayColorText = displayColorText;
	}


	public JPanel getDisplayColor() {
		return displayColor;
	}


	public void setDisplayColor(JPanel displayColor) {
		this.displayColor = displayColor;
	}


	public JPanel getSuperPanel() {
		return superPanel;
	}


	public void setSuperPanel(JPanel superPanel) {
		this.superPanel = superPanel;
	}


	public JButton getRemoveButton() {
		return removeButton;
	}


	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

	
	
	

}
