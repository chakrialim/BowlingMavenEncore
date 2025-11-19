package bowling;

import java.util.List;
import java.util.ArrayList;

public class Tour {
    protected boolean spare;
    protected boolean strike;
    protected int nbQuilles = 0;
    protected Tour nextTour;
    protected List<Lancer> lancers = new ArrayList<Lancer>();
    protected int numeroProchainLancer = 1;


    public int getNumProchainLancer(){
        return numeroProchainLancer;
    }

    public void setNextTour(Tour nextTour){
        this.nextTour = nextTour;
    }

    public boolean ajouterLancer(Lancer lancer){
        int nbQuilles = lancer.getNombreQuillesAbattues(); 
        this.nbQuilles += nbQuilles;
        if(nbQuilles == 10 && numeroProchainLancer == 1){ 
            strike = true; 
            numeroProchainLancer = 2;
        }  
        else if(this.nbQuilles == 10 && !strike){ 
            spare = true; 
        } 
        numeroProchainLancer++; 
        return lancers.add(lancer); 
    }

    public boolean isStrike(){
        return strike;
    }
    
    public boolean isSpare(){
        return spare;
    }


    public int getScoreLancer(int index){
        return lancers.get(index).getNombreQuillesAbattues();
    }

    public int getScore(){
        int score = nbQuilles;
        if(strike){
            if(nextTour == null){
                return score;
            }
            score += nextTour.getScoreLancer(0);
            if(nextTour.isStrike()){
                Tour nextnext = nextTour.nextTour;
                if(nextnext == null){
                    return score;
                }
                score += nextnext.getScoreLancer(0);
            } else {
                score += nextTour.getScoreLancer(1);
            }
        }
        if(spare){
            score += nextTour.getScoreLancer(0);
        }
        return score;
    }

 

    public boolean estTermine(){
        return numeroProchainLancer>=3;
    }


}
