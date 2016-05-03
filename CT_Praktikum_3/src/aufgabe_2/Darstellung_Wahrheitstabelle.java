package aufgabe_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Darstellung_Wahrheitstabelle {
	private ArrayList<Wahrheitstupel> requiredTuple = new ArrayList<>();
	
	private HashMap<ArrayList<Integer>, Integer> wahrheitstabelle = new HashMap<>();
	private Set<ArrayList<Integer>> mehrfacheUebergangsueberdeckung = new HashSet<ArrayList<Integer>>();
	private Set<ArrayList<Integer>> einfacheBedinungsueberdeckungInklEntscheidungsueberdeckung = new HashSet<ArrayList<Integer>>();
	
	public void checkNeighbours() {
		for(Entry<ArrayList<Integer>, Integer> entry : wahrheitstabelle.entrySet()) {
			ArrayList<Integer> vector = entry.getKey();
			for(int i  = 0; i < vector.size(); i++) {
				ArrayList<Integer> tempV = (ArrayList<Integer>) vector.clone();
				if(vector.get(i) == 0) {
					tempV.set(i, 1);
				}else{ 
					tempV.set(i, 0);
				}
				if (wahrheitstabelle.get(tempV) != entry.getValue()) {
					mehrfacheUebergangsueberdeckung.add(vector);
				}
				
			}
		}
	}
	
	public void findBedingungsUeberdeckung(){
		for(Entry<ArrayList<Integer>, Integer> entry : wahrheitstabelle.entrySet()) {
			ArrayList<Integer> vector = entry.getKey();
			ArrayList<Integer> tempV = (ArrayList<Integer>) vector.clone();
			for(int i  = 0; i < vector.size(); i++) {
				if(tempV.get(i) == 1){
					tempV.set(i, 0);
				}else{
					tempV.set(i, 1);
				}	
			}
			if (wahrheitstabelle.get(tempV) != entry.getValue()) {
				einfacheBedinungsueberdeckungInklEntscheidungsueberdeckung.add(vector);
				einfacheBedinungsueberdeckungInklEntscheidungsueberdeckung.add(tempV);
				break;
			}
		}
	}
	
	public void readWahrheitstabelle(String dateiName) throws IOException{
		ArrayList<String[]> allLines = new ArrayList<>();
		FileReader fr = new FileReader(dateiName);
		BufferedReader br = new BufferedReader(fr);
		
		//Collect all lines from text file;
		String line = br.readLine(); 
		int i = 0;
		while(line != null) {
			allLines.add(line.split(" "));
			line = br.readLine();
			
			i++;
		}
		br.close();
		
		//Create all tuple and results and add them to the table
		for(int index = 1; index < allLines.get(0).length; index++) {
			ArrayList<Integer> eingabetupel = new ArrayList<>();
			int result = -1;
			int index2 = 0;
			for(String[] a : allLines) {
				//check if the element is the result or part of the tuple
				if(index2 == allLines.size() -1) {
					result = Integer.parseInt(a[index]);
				}else{
					eingabetupel.add(Integer.parseInt(a[index])); 
				}
				index2 ++;
			}
			
			//Create new tuple and add to the table
			wahrheitstabelle.put(eingabetupel, result);
		}
	}
	
	public void writeWahrheitstabelle() {
			System.out.println("Wahrheitstabelle: " + wahrheitstabelle.toString());
			System.out.println("MehrfacheUebergangsueberdeckung: " + mehrfacheUebergangsueberdeckung.toString());
			System.out.println("EinfacheBedinungsueberdeckung inkl. Entscheidungsueberdeckung"+einfacheBedinungsueberdeckungInklEntscheidungsueberdeckung.toString());
	}
	
	public static void main(String[] args) {
		Darstellung_Wahrheitstabelle dw = new Darstellung_Wahrheitstabelle();
		try {
			dw.readWahrheitstabelle("/Users/Steffen/Desktop/Wahrheitstabelle.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		dw.checkNeighbours();
		dw.findBedingungsUeberdeckung();
		dw.writeWahrheitstabelle();
	}
}
