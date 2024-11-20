/*
* auteur Khalil chafik
 */
public class Noeud {
    private char element;
    private Noeud filsDroit;
    private Noeud filsGauche;

    public Noeud(char element) {
        this.element = element;
        this.filsDroit = null;
        this.filsGauche = null;
    }

    public char getElement() {
        return element;
    }

    public void setElement(char element) {
        this.element = element;
    }

    public Noeud getFilsDroit() {
        return filsDroit;
    }

    public void setFilsDroit(Noeud filsDroit) {
        this.filsDroit = filsDroit;
    }

    public Noeud getFilsGauche() {
        return filsGauche;
    }

    public void setFilsGauche(Noeud filsGauche) {
        this.filsGauche = filsGauche;
    }

    @Override
    public String toString() {
        return Character.toString(element); 
    }
}
