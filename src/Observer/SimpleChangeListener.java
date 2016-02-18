package Observer;
// Interface implémentée par tous les observateurs.
public interface SimpleChangeListener
{
    // Méthode appelée automatiquement lorsque l'état change.
    public void actualiser(ISimpleChangeModel o);
}
