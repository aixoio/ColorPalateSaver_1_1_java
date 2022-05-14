package saving;

import java.io.Serializable;
import java.util.ArrayList;

import main.ColorPanel;

public class SavableObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6364685599356993168L;
	
	private ArrayList<ColorPanel> list;
	
	public SavableObject(ArrayList<ColorPanel> list) {
		
		this.list = list;
		
	}

	public ArrayList<ColorPanel> getList() {
		return this.list;
	}

	public void setList(ArrayList<ColorPanel> list) {
		this.list = list;
	}

}
