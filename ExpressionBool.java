/*
* auteur Khalil chafik
 */
import java.util.Scanner;

public class ExpressionBool { 

    private Noeud racine; 
    private static int k = 26; // Déclaration d'une variable entière statique et appelée k, initialisée à 26

    // Constructeur par défaut 
    public ExpressionBool() {
        racine = null; 
    }

    // Constructeur avec un argument
    public ExpressionBool(Noeud racine) {
        this.racine = racine; 
    }

    // getter de la racine
    public Noeud getRacine() {
        return racine;
    }

    // Méthode pour vérifier si l'expression est vide
    public boolean estVide() {
        return racine == null; // Retourne vrai si la racine est null, sinon faux
    }

    // Méthode pour effectuer la négation de l'expression
    public ExpressionBool negation() {
        if (estVide()) { // Vérifie si l'expression est vide
            System.out.println("L'expression est vide, la négation ne peut pas être effectuée.");
            return new ExpressionBool(); // Retourne une expression vide
        }
        Noeud noeudNegation = new Noeud('!'); // Crée un nœud avec l'opérateur de négation
        noeudNegation.setFilsDroit(racine); // Définit le fils droit du nœud avec la racine de l'expression actuelle
        return new ExpressionBool(noeudNegation); // Retourne une nouvelle expression avec le nœud de négation
    }

    // Méthode pour effectuer la disjonction avec une autre expression
    public ExpressionBool disjonction(ExpressionBool e) {
        if (estVide() || e.estVide()) { // Vérifie si l'une des expressions (ou les deux) est vide
            System.out.println("L'une des expressions (ou les deux) est vide, la disjonction ne peut pas être effectuée."); // Affiche un message d'erreur
            return new ExpressionBool(); // Retourne une expression vide
        }
        Noeud noeudDisjonction = new Noeud('+'); // Crée un nœud avec l'opérateur de disjonction
        noeudDisjonction.setFilsGauche(racine); // Définit le fils gauche du nœud avec la racine de l'expression actuelle
        noeudDisjonction.setFilsDroit(e.getRacine()); // Définit le fils droit du nœud avec la racine de l'expression passée en argument
        return new ExpressionBool(noeudDisjonction); // Retourne une nouvelle expression avec le nœud de disjonction
    }

    // Méthode pour effectuer la conjonction avec une autre expression
    public ExpressionBool conjonction(ExpressionBool e) {
        if (estVide() || e.estVide()) { // Vérifie si l'une des expressions (ou les deux) est vide
            System.out.println("L'une des expressions (ou les deux) est vide, la conjonction ne peut pas être effectuée."); // Affiche un message d'erreur
            return new ExpressionBool(); // Retourne une expression vide
        }
        Noeud noeudConjonction = new Noeud('.'); // Crée un nœud avec l'opérateur de conjonction
        noeudConjonction.setFilsGauche(racine); // Définit le fils gauche du nœud avec la racine de l'expression actuelle
        noeudConjonction.setFilsDroit(e.getRacine()); // Définit le fils droit du nœud avec la racine de l'expression passée en argument
        return new ExpressionBool(noeudConjonction); // Retourne une nouvelle expression avec le nœud de conjonction
    }

    // Méthode pour afficher l'expression en notation infixe
    public String afficher() {
        return afficherInfixe(racine); // Appel à la méthode privée afficherInfixe avec la racine de l'expression comme argument
    }

    // Méthode récursive pour afficher l'expression en notation infixe
    private String afficherInfixe(Noeud noeud) {
        if (noeud == null) { // Vérifie si le nœud est null
            return ""; // Retourne une chaîne vide
        }

        String expression = ""; // Initialise une chaîne vide pour stocker l'expression

        if (noeud.getFilsGauche() != null) { // Vérifie si le fils gauche du nœud n'est pas null
            expression += "(" + afficherInfixe(noeud.getFilsGauche()) + ")";// Ajoute l'expression infixée du fils gauche entre parenthèses
        }

        expression += "" + noeud.getElement() + ""; // Ajoute l'élément du nœud à l'expression

        if (noeud.getFilsDroit() != null) { // Vérifie si le fils droit du nœud n'est pas null
            expression += "(" + afficherInfixe(noeud.getFilsDroit()) + ")"; // Ajoute l'expression infixée du fils droit entre parenthèses
        }

        return expression; // Retourne l'expression infixée
    }
    // Méthode pour évaluer l'expression booléenne avec un vecteur de valeurs booléennes
    public boolean evaluer(boolean[] valeurs) {
        return evaluerNoeud(racine, valeurs); // Appel à la méthode privée evaluerNoeud avec la racine de l'expression et le vecteur de valeurs comme arguments
    }

    // Méthode récursive pour évaluer un nœud de l'arbre d'expression booléenne
    private boolean evaluerNoeud(Noeud noeud, boolean[] valeurs) {
        if (noeud == null) { // Vérifie si le nœud est null
            return false; // Retourne faux
        }

        char element = noeud.getElement(); // Récupère l'élément du nœud

        switch (element) { // Démarre l'instruction switch sur l'élément du nœud
            case '!': // Cas où l'élément est '!'
                return !evaluerNoeud(noeud.getFilsDroit(), valeurs);
                 // Retourne la négation de l'évaluation du fils droit du nœud
            case '+': // Cas où l'élément est '+'
                return evaluerNoeud(noeud.getFilsGauche(), valeurs) || evaluerNoeud(noeud.getFilsDroit(), valeurs); 
                // Retourne le résultat de la disjonction entre l'évaluation des fils gauche et droit du nœud
            case '.': // Cas où l'élément est '.'
                return evaluerNoeud(noeud.getFilsGauche(), valeurs) && evaluerNoeud(noeud.getFilsDroit(), valeurs); 
                // Retourne le résultat de la conjonction entre l'évaluation des fils gauche et droit du nœud
            default: // Cas par défaut
                if (element >= 'a' && element < 'a' + k) { // Vérifie si l'élément est une variable (lettre minuscule)
                    int index = element - 'a'; // Calcule l'index de la variable dans le vecteur de valeurs
                    return index >= 0 && index < valeurs.length && valeurs[index]; // Retourne la valeur de la variable dans le vecteur de valeurs
                } else { // Cas où l'élément n'est pas reconnu
                    System.out.println("Opérateur '" + element + "' non valide."); // Affiche un message d'erreur
                    return false; // Retourne faux
                }
        }
    }

    // Méthode main pour tester le fonctionnement de la classe ExpressionBool
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int k; // Déclaration d'une variable entière k pour stocker le nombre de variables

        do {
            System.out.print("Entrez le nombre de variables (k): "); 
            k = scanner.nextInt(); 
            // Vérifie si la valeur de k est dans la plage autorisée
            if (k < 1 || k > 26) { 
                System.out.println("La valeur de k doit être comprise entre 1 et 26."); 
            }
        } while (k < 1 || k > 26); 

        Noeud[] noeuds = new Noeud[k]; // Crée un tableau de nœuds de taille k pour représenter les variables
        for (int i = 0; i < k; i++) { 
            noeuds[i] = new Noeud((char)('a' + i)); // Crée un nœud avec le caractère correspondant à la variable (a, b, c, ...)
        }

        ExpressionBool[] expressions = new ExpressionBool[k]; // Crée un tableau d'expressions booléennes de taille k
        for (int i = 0; i < k; i++) { 
            expressions[i] = new ExpressionBool(noeuds[i]); // Crée une expression booléenne avec le nœud correspondant à la variable
        }
        //  f= ¬(( ¬a ∧ (c ∨ a)) ∨¬(b ∧  d))
        // Définition de l'expression f à partir des expressions élémentaires
        ExpressionBool part1_exp1 = expressions[0].negation(); // Négation de la variable a, (!a)
        ExpressionBool part2_exp1 = expressions[2].disjonction(expressions[0]); // Disjonction entre la variable c et a , (c + a)
        ExpressionBool exp1 = part1_exp1.conjonction(part2_exp1); // Conjonction entre la négation de la première et la disjonction ,(!a . (c+a) ) 
        ExpressionBool part1_exp2 = expressions[1].conjonction(expressions[3]); // Conjonction entre b et la d , (b . d)
        ExpressionBool exp2 = part1_exp2.negation(); // Négation de la conjonction précédente , ! (b . d)
        ExpressionBool f_demo = exp1.disjonction(exp2); // Disjonction entre les deux expressions précédentes (exp1 et exp2)
        ExpressionBool f_final = f_demo.negation(); // Négation de la disjonction précédente, et c'est l'expression finale.

        // Affichage de l'expression f
        System.out.println("Expression f: " + f_final.afficher());

        // Demande à l'utilisateur de saisir les valeurs pour le vecteur de test (f)
        boolean[] valeursF = new boolean[k]; // Crée un tableau de valeurs booléennes de taille k
        System.out.println("Entrez les valeurs pour le vecteur de test (f): ");
        for (int i = 0; i < k; i++) {
            boolean valeurCorrecte = false; // Initialise un booléen pour vérifier si la valeur saisie est correcte
            do { //  une boucle pour répéter la saisie jusqu'à ce qu'une valeur correcte soit saisie
                System.out.print("Variable " + (char)('a' + i) + " (true/false): "); // Affiche un message pour demander la saisie de la valeur de la variable
                String valeur = scanner.next(); 
                if (valeur.equalsIgnoreCase("true")) { 
                    valeursF[i] = true; // 
                    valeurCorrecte = true; // Affecte vrai à la variable de contrôle pour indiquer une saisie correcte
                } else if (valeur.equalsIgnoreCase("false")) { 
                    valeursF[i] = false; // 
                    valeurCorrecte = true; // 
                } else { 
                    System.out.println("Veuillez entrer 'true' ou 'false'."); 
                }
            } while (!valeurCorrecte); // Répète la saisie tant que la valeur saisie n'est pas correcte
        }

        // Évaluation de l'expression f avec le vecteur de test
        System.out.println("Résultat de l'évaluation de f avec v: " + f_final.evaluer(valeursF));

    }
}
