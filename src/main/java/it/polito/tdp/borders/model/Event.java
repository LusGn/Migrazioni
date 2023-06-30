package it.polito.tdp.borders.model;

public class Event implements Comparable<Event>{
	private int time; //passi di simulazione
	//tipo di ingresso: ingresso (sottointeso)
	private Country destinazione;
	private int dimensioni;
	public Event(int time, Country destinazione, int dimensioni) {
		super();
		this.time = time;
		this.destinazione = destinazione;
		this.dimensioni = dimensioni;
	}
	public int getTime() {
		return time;
	}
	public Country getDestinazione() {
		return destinazione;
	}
	public int getDimensioni() {
		return dimensioni;
	}
	@Override
	public String toString() {
		return "Event [time=" + time + ", destinazione=" + destinazione + ", dimensioni=" + dimensioni + "]";
	}
	@Override
	public int compareTo(Event o) {
		return this.time-o.time;
	}
	
	
}
