package it.polito.tdp.borders.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import java.util.*;

public class Simulatore {
	
	//Stato del sistema
	
	private Map<Country, Integer> stanziali;
	
	//parametri della simulazione
	private int nPersone=1000;
	private Graph<Country, DefaultEdge> graph;
	private Country partenza;
	
	//output 
	private int nPassi;
	
	//coda degli eventi
	private PriorityQueue<Event> queue;
	
	public Simulatore(Graph<Country, DefaultEdge> graph, Country partenza) {
		this.graph=graph;
		this.partenza=partenza;
		
		this.nPassi=0;
		this.stanziali=new HashMap<>();
		for(Country c: this.graph.vertexSet()) {
			this.stanziali.put(c, 0);
		}
		this.queue=new PriorityQueue<>();
	}
	
	
	private void initialize() {
		this.queue.add(new Event(0, this.partenza, this.nPersone));
	}
	
	private void run() {
		while(!this.queue.isEmpty()) {
			Event e=this.queue.poll();
			int time=e.getTime();
			Country destinazione=e.getDestinazione();
			int dimensione=e.getDimensioni();
			
			this.nPassi=time;
			
			List<Country> vicini=Graphs.neighborListOf(this.graph, destinazione);
			int migranti=dimensione/2/vicini.size();
			
				//dimensione / 2 dividendo stanzili nello stato 'destinazione'
			//geneerando eventi ingrsso con la quota di persone
			if(migranti>0) {
				for(Country c: vicini) {
					this.queue.add(new Event(time+1, c, migranti));
				}
			}
			
			//i rimanenti si divisono negli stati adaicenti
			int nuovistanziali=dimensione*vicini.size();
			this.stanziali.put(destinazione, this.stanziali.get(destinazione)+nuovistanziali);
			
			
		}
	}


	public int getnPassi() {
		return nPassi;
	}


	public void setnPassi(int nPassi) {
		this.nPassi = nPassi;
	}
	
	
	
	
	
	
	
	
	
}
