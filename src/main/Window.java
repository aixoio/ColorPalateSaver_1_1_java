package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import random.RandomColor;
import saving.SavableObject;

public class Window implements ActionListener {
	
	public static Font FONT;
	public static ArrayList<ColorPanel> LIST = new ArrayList<>();
	
	private JFrame frame;
	private JButton addButton;
	private JPanel panel;
	private JButton randomColorButton;
	
	private JButton saveBtn;
	private JButton loadBtn;
	private JButton resetBtn;
	
	private JButton chooseColorBtn;
	
	
	public Window() {
		
		try {
			
			Window.FONT = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/fonts/Roboto-Light.ttf"));
			
		} catch (FontFormatException ffe) {
			
			ffe.printStackTrace();
			
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
			
		}
		
		this.frame = new JFrame("Colors");
		this.addButton = new JButton("Add");
		this.addButton.addActionListener(this);
		this.panel = new JPanel();
		this.panel.setSize(780, 412);
		this.addButton.setFocusable(false);
		this.randomColorButton = new JButton("Random color");
		this.saveBtn = new JButton("Save");
		this.loadBtn = new JButton("Load");
		this.resetBtn = new JButton("Reset");
		this.chooseColorBtn = new JButton("Choose color");
		
		
		this.saveBtn.addActionListener(this);
		this.loadBtn.addActionListener(this);
		this.resetBtn.addActionListener(this);
		this.chooseColorBtn.addActionListener(this);
		
		this.saveBtn.setFocusable(false);
		this.loadBtn.setFocusable(false);
		this.resetBtn.setFocusable(false);
		this.chooseColorBtn.setFocusable(false);
		
		
		this.setup();
		
		
		
	}
	
	private void setup() {
		
		this.frame.setSize(512, 512);
		this.frame.setMinimumSize(new Dimension(780, 512));
		this.frame.setLayout(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel title = new JLabel("Colors");
		title.setFont(Window.FONT.deriveFont(24f));
		title.setBounds(15, 15, 128, 25);
		
		this.frame.add(title);
		
		
		this.addButton.setBounds(200, 15, 50, 25);
		
		this.frame.add(this.addButton);
		
		this.randomColorButton.setBounds(275, 15, 175, 25);
		this.randomColorButton.setFocusable(false);
		this.randomColorButton.addActionListener(this);
		this.frame.add(this.randomColorButton);
		
		this.saveBtn.setBounds(475, 15, 75, 25);
		this.frame.add(this.saveBtn);
		
		this.loadBtn.setBounds(545, 15, 75, 25);
		this.frame.add(this.loadBtn);
		
		this.resetBtn.setBounds(125, 15, 75, 25);
		this.frame.add(this.resetBtn);
		
		this.chooseColorBtn.setBounds(625, 15, 125, 25);
		this.frame.add(this.chooseColorBtn);
		
		this.panel.setBounds(0, 50, 780, 412);
		
		this.frame.add(this.panel);
		
		this.panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.panel.setPreferredSize(new Dimension(680, 412));
		
		this.frame.setResizable(false);
		
		
		
		this.frame.setVisible(true);
		
	}

	private void sizeC(Runnable r) {
		
		if (Window.LIST.size() < 36) {
			
			r.run();
			
		} else {
			
			JOptionPane.showMessageDialog(this.panel, "You have the maximum amount of colors", "Warning", JOptionPane.WARNING_MESSAGE);
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(this.addButton)) {
			
			try {
				
				this.sizeC(() -> ColorPanel.addANewColor(this.panel));
				
				this.panel.repaint();
				this.panel.revalidate();
				this.panel.updateUI();
			
				
				
			} catch (NumberFormatException nfe) {
				
				System.out.println("Error: " + nfe.toString());
				
			} catch (IndexOutOfBoundsException ioobe) {
				
				System.out.println("Error: " + ioobe.toString());
				
			}
			
		} else if (e.getSource().equals(this.randomColorButton)) {
			
			this.sizeC(() -> {
			
				ColorPanel cp = RandomColor.randomColor(this.panel);
				
				Window.LIST.add(cp);
				this.panel.add(cp);
			
			});
			
			this.panel.repaint();
			this.panel.revalidate();
			this.panel.updateUI();
			
			
		
			
		} else if (e.getSource().equals(this.saveBtn)) {
			
			Window.saveGUI(this);
			
		} else if (e.getSource().equals(this.loadBtn)) {
			
			Window.loadGUI(this);
			
		} else if (e.getSource().equals(this.resetBtn)) {
			
			this.reset();
			
		} else if (e.getSource().equals(this.chooseColorBtn)) {
			
			this.sizeC(() -> {
				
				ColorPanel cp = Pick.pickColorMakePanel(this.panel);
				
				if (cp == null) return;
				
				Window.LIST.add(cp);
				this.panel.add(cp);
			
			});
			
			this.panel.repaint();
			this.panel.revalidate();
			this.panel.updateUI();
			
		}
		
	}

	public static Font getFONT() {
		return FONT;
	}

	public static void setFONT(Font fONT) {
		FONT = fONT;
	}

	public static ArrayList<ColorPanel> getLIST() {
		return LIST;
	}

	public static void setLIST(ArrayList<ColorPanel> lIST) {
		LIST = lIST;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	
	public static void saveGUI(Window window) {
		
		JFileChooser fileChooser = new JFileChooser();
		
		int res = fileChooser.showSaveDialog(window.getPanel());
		
		if (res == JFileChooser.APPROVE_OPTION) {
			
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
			Window.save(file.getPath());
			
		}
		
	}
	
	public static void loadGUI(Window window) {
		
		JFileChooser fileChooser = new JFileChooser();
		
		int res = fileChooser.showOpenDialog(window.getPanel());
		
		if (res == JFileChooser.APPROVE_OPTION) {
			
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			
				
			SavableObject obj = Window.load(file.getPath());
			
			if (obj == null) return;
			
			Window.LIST = obj.getList();
			
			JPanel panel = window.getPanel();
			
			panel.removeAll();
			
			for (int i = 0; i < Window.LIST.size(); i++) {
				
				ColorPanel cp = Window.LIST.get(i);
				
				cp.update(window);
				
				panel.add(cp);
				
				
			}
			
			window.getPanel().repaint();
			window.getPanel().revalidate();
			window.getPanel().updateUI();
			

					
					
		}
				
				
			
		
	}
	
	
	
	public static void save(String path) {
		
		SavableObject object = new SavableObject(Window.LIST);
		
		try {
			
			FileOutputStream fileOut = new FileOutputStream(path);
			
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(object);
			
			out.close();
			fileOut.close();
			
			
		} catch (IOException e) {
			
			System.out.println("Error: " + e.toString());
			
		}

		
	}
	
	public static SavableObject load(String path) {
		
		SavableObject object = null;
		
		try {
			
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			object = (SavableObject) in.readObject();
			
			in.close();
			fileIn.close();
			
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.toString());
			
		}
		
		return object;
		
	}
	
	public void reset() {
		
		Window.LIST.clear();
		this.panel.removeAll();
		
		this.panel.repaint();
		this.panel.revalidate();
		this.panel.updateUI();
		
	}

	public JButton getRandomColorButton() {
		return randomColorButton;
	}

	public void setRandomColorButton(JButton randomColorButton) {
		this.randomColorButton = randomColorButton;
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(JButton saveBtn) {
		this.saveBtn = saveBtn;
	}

	public JButton getLoadBtn() {
		return loadBtn;
	}

	public void setLoadBtn(JButton loadBtn) {
		this.loadBtn = loadBtn;
	}

	public JButton getResetBtn() {
		return resetBtn;
	}

	public void setResetBtn(JButton resetBtn) {
		this.resetBtn = resetBtn;
	}

	public JButton getChooseColorBtn() {
		return chooseColorBtn;
	}

	public void setChooseColorBtn(JButton chooseColorBtn) {
		this.chooseColorBtn = chooseColorBtn;
	}

}
