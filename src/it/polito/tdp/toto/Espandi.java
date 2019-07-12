package it.polito.tdp.toto;

import java.util.ArrayList;
import java.util.List;

public class Espandi {
	
	private List<Schedina> soluzioni;
	
	//Livello della ricorsione = singola partita
	//livello = 0 -> schedina con 0 risultati
	//livello = 1 -> schedina con 1 risultato e cosi' via
	
	public List<Schedina> espandiPronostico(Pronostico p) {
		// la ricorsione e' difficile non la faccio chiamare pubblicamente
		
		Schedina parziale = new Schedina(p.getN());
		//chiama una nuova schedina con dimensione massima pari al pronostico
		
		this.soluzioni = new ArrayList<Schedina>();
		
		espandi(p, parziale, 0);
		//quando la chiamo la prima volta le passo il livello 0 e la schedina vuota appena creata
		
		return this.soluzioni;

	}
	
	private void espandi(Pronostico p, Schedina parziale, int livello) {
	// Il pronostico e' che cosa ha barrato l'utente
	// Schedina parziale e' il risultato a cui sono arrivata fin'ora
	//a cui i livelli precedenti sono gia' arrivati
	// livello -> livello a cui sono arrivato
	
	
	// parziale contiene gia' (livello) valori
	//messi nelle posizioni da 0 a livello-1
	// io devo determinare parziale[livello] che e' la (livello+1) riga
	// sulla base della previsione p[livello]
	
	
		if(livello == p.getN()) {
			//System.out.println(parziale);
			this.soluzioni.add(new Schedina(parziale));
			return;
			// se il livello e' uguale al numero di previsioni(partite sui cui ho scommesso)
			// non ho altri casi da guardare perche parto dal livello 0,primo elemento delle previsioni e' in posizione 0
			// l'elemento N della previsione e' in posizione N-1
		}
	
		Previsione prev = p.get(livello);
		// la priva volta livello vale zero e vado a prendere il pronostico p[0] che 
		//e' la prima riga del pronostico
		// la previsione e' un insieme di valori che il giocatore indica come valori possibili per la partita livello-1
	
	
		// Prova le varie alternative
		for(Risultato r : prev.getValori()) {
			//provo ad aggiungere 'r' alla soluzine parziale 
			parziale.add(r);
		
			// faccio espandere la stessa schedina parziale al livello+1
			espandi(p, parziale, livello+1);
		
			// devo togliere!!! ->  backtrack
			parziale.removeLast();
			// dopo che ho provato una delle alternative devo tornare indietro per provare una delle altre alternative
			// faccio la prova ma prima di ripetere il ciclo la soluzione parziale deve tornare ad essere quella di quando
			// entro nel ciclo!
			
		}
	
	}	
	
}
