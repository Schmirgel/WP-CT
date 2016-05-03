package aufgabe_2;

import java.util.ArrayList;

public class Wahrheitstupel {
	private ArrayList<Integer> eingabetupel = new ArrayList<>();
	private int result;
	
	private ArrayList<Wahrheitstupel> neigbours = new ArrayList<>();
	
	public Wahrheitstupel(ArrayList<Integer> eingabetupel, int result) {
		this.eingabetupel = eingabetupel;
		this.result = result;
	}
	
	//Getter
	public ArrayList<Integer> getEingabetupel() {
		return eingabetupel;
	}
	
	public int getResult() {
		return result;
	}
	
	public ArrayList<Wahrheitstupel> getNeighbours() {
		return neigbours;
	}
	
	//Setter
	public void setEingabetupel(ArrayList<Integer> eingabetupel) {
		this.eingabetupel = eingabetupel;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
	public void setNeighbours(ArrayList<Wahrheitstupel> neighbours) {
		this.neigbours = neighbours;
	}
	
	public void addNeighbours(Wahrheitstupel neighbour) {
		neigbours.add(neighbour);
	}
	
	@Override
	public boolean equals(Object o){
		if(this.getClass() == o.getClass()){
			Wahrheitstupel obj = (Wahrheitstupel) o;
			if(this.getEingabetupel().containsAll(obj.getEingabetupel())){
				if(this.result == obj.getResult()) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	public String toString() {
		return eingabetupel.toString() + "B " + result + "Neigbours: " + neigbours.toString();
	}

}
