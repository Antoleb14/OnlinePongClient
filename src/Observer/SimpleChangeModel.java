package Observer;

import java.util.ArrayList;

/**
 * Created by Clement on 30/11/2015.
 */
public class SimpleChangeModel implements ISimpleChangeModel {

    private ArrayList<SimpleChangeListener> olist= new ArrayList<SimpleChangeListener>();

    @Override
    public void ajouterObservateur(SimpleChangeListener o) {
        olist.add(o);
    }

    @Override
    public void notifierObservateurs() {
        for(int i=0;i<olist.size();i++)
        {
            SimpleChangeListener o = olist.get(i);
            o.actualiser(this);// On utilise la méthode "tiré".
        }
    }

    @Override
    public void supprimerObservateur(SimpleChangeListener o) {
        olist.remove(o);
    }
}
