# Description

Le projet **ExpressionBool** permet de manipuler et d'évaluer des expressions booléennes en Java à l'aide d'une structure d'arbre. Chaque expression est représentée par un arbre binaire où chaque nœud peut être un opérateur booléen (comme `!`, `+`, `.`) ou une variable. Le programme permet de construire des expressions booléennes complexes, de les afficher en notation infixe, et de les évaluer avec des valeurs booléennes spécifiques.



 **Création d'expressions booléennes :**
   Le code permet de créer des expressions booléennes à l'aide des opérateurs suivants :
   - **Négation (`!`)** : Effectue une inversion de la valeur d'une expression.
   - **Conjonction (`.`)** : Effectue l'opération logique "ET" entre deux expressions.
   - **Disjonction (`+`)** : Effectue l'opération logique "OU" entre deux expressions.

 **Affichage d'expressions en notation infixe :**
   Une méthode `afficher()` permet d'afficher une expression booléenne sous sa forme infixe, avec des parenthèses pour indiquer l'ordre des opérations.

 **Évaluation d'expressions :**
   Le code permet d'évaluer une expression booléenne en fonction de valeurs booléennes données pour chaque variable (par exemple `a`, `b`, `c`, etc.). La méthode `evaluer()` prend en entrée un tableau de valeurs booléennes et renvoie le résultat de l'évaluation de l'expression.

 **Entrée dynamique des variables :**
   L'utilisateur peut entrer le nombre de variables qu'il souhaite utiliser (jusqu'à 26 variables, de `a` à `z`), ainsi que les valeurs associées à ces variables (true ou false).



 
