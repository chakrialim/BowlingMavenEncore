package bowling;

public class DernierTour extends Tour{
    private int nbBonusLancers = 0;

    @Override
    public boolean ajouterLancer(Lancer lancer) {
        int nb = lancer.getNombreQuillesAbattues();
        lancers.add(lancer);
        nbQuilles += nb;

        if (numeroProchainLancer == 1) {
            if (nb == 10) {
                strike = true;
                nbBonusLancers = 2;
            }
            numeroProchainLancer = 2;
        }
        else if (numeroProchainLancer == 2) {
            
            if (!strike && lancers.get(0).getNombreQuillesAbattues() + nb == 10) {
                spare = true;
                nbBonusLancers = 1;
            }

            if (!strike && !spare) {
                numeroProchainLancer = 0;
                return true;
            }

            numeroProchainLancer = 3;
            return true;
        }
        
        else if (numeroProchainLancer == 3) {
            nbBonusLancers = 0;
            numeroProchainLancer = 0;
            return true;
        }

        return true;
    }

    public int getNbBonusLancers() {
        return nbBonusLancers;
    }

    @Override
    public int getScore(){
        return nbQuilles;
    }

    @Override
    public boolean estTermine(){
        return (numeroProchainLancer==0);
    }

}
