package Observer;
// Interface implémentée par toutes les classes souhaitant avoir des observateurs à leur écoute.
public interface ISimpleChangeModel
{
    // Méthode permettant d'ajouter (abonner) un observateur.
    public void ajouterObservateur(SimpleChangeListener o);
    // Méthode permettant de supprimer (résilier) un observateur.
    public void supprimerObservateur(SimpleChangeListener o);
    // Méthode qui permet d'avertir tous les observateurs lors d'un changement d'état.
    public void notifierObservateurs();
}