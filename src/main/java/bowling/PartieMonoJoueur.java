package bowling;
import java.util.ArrayList;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	private ArrayList<Tour> tours = new ArrayList<Tour>(10);
	private int numeroTourCourant = 1;

 
	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		initialiserTours();
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */

	public void initialiserTours(){
		for(int i=0;i<9;i++){
			tours.add(new Tour());
		}
		Tour dernierTour = new DernierTour();
		tours.add(dernierTour);
		for(int i=0;i<9;i++){
			tours.get(i).setNextTour(tours.get(i+1));
		}

	}

	public boolean enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException{ 
		if(estTerminee()){ 
			throw new IllegalStateException("Le jeu est terminé"); 
		} 

		Tour courant = tours.get(numeroTourCourant - 1);
		courant.ajouterLancer(new Lancer(nombreDeQuillesAbattues));

		if(courant.estTermine()) {
			if(!(courant instanceof DernierTour)) {
				numeroTourCourant++;
			} else {
				numeroTourCourant = 0;
			}
			return false;
		}
		
		return true;
	}
	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {
		boolean perfectGame = true;
		for(Tour t : tours){
			if(!t.isStrike()){
				perfectGame = false;
				break;
			}
		}
		if(perfectGame){
			return 300;
		}
		int totale = 0;
		for(Tour t : tours){
			totale += t.getScore();
		}
		
		return totale;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		return tours.getLast().getNumProchainLancer() == 0;
	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		return numeroTourCourant;
	}


	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		if(numeroTourCourant == 0) {
			return 0;
		}
		return tours.get(numeroTourCourant-1).getNumProchainLancer();
	}

}
