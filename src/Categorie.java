/**
 * Enumération des catégories de dépenses disponibles.
 */
public enum Categorie {
    LOGEMENT,
    NOURRITURE,
    TRANSPORT,
    SANTE,
    LOISIRS,
    EDUCATION,
    AUTRES;

    /**
     * Affiche toutes les catégories avec leur numéro pour le menu.
     */
    public static void afficherCategories() {
        Categorie[] categories = Categorie.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println("  " + (i + 1) + ". " + categories[i]);
        }
    }

    /**
     * Retourne une catégorie à partir de son numéro (1-based).
     */
    public static Categorie fromChoix(int choix) {
        Categorie[] categories = Categorie.values();
        if (choix < 1 || choix > categories.length) {
            throw new IllegalArgumentException("Choix invalide : " + choix);
        }
        return categories[choix - 1];
    }
}
