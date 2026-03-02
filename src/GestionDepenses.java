import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gère toutes les opérations sur les dépenses et les budgets.
 */
public class GestionDepenses {

    private List<Depense> depenses;
    private Map<Categorie, Budget> budgets;

    public GestionDepenses() {
        this.depenses = new ArrayList<>();
        this.budgets  = new HashMap<>();
    }

    // ══════════════════════════════════════════════════════════════════
    //  GESTION DES DÉPENSES
    // ══════════════════════════════════════════════════════════════════

    /**
     * Ajoute une nouvelle dépense.
     */
    public void ajouterDepense(double montant, LocalDate date, Categorie categorie, String description) {
        Depense d = new Depense(montant, date, categorie, description);
        depenses.add(d);
        System.out.println("✔ Dépense ajoutée avec succès (ID #" + d.getId() + ")");

        // Vérification du budget après ajout
        verifierBudget(categorie);
    }

    /**
     * Retourne toutes les dépenses.
     */
    public List<Depense> getDepenses() {
        return depenses;
    }

    /**
     * Recherche une dépense par son ID.
     */
    public Depense trouverParId(int id) {
        for (Depense d : depenses) {
            if (d.getId() == id) return d;
        }
        return null;
    }

    /**
     * Modifie une dépense existante.
     */
    public boolean modifierDepense(int id, double montant, LocalDate date,
                                   Categorie categorie, String description) {
        Depense d = trouverParId(id);
        if (d == null) return false;

        d.setMontant(montant);
        d.setDate(date);
        d.setCategorie(categorie);
        d.setDescription(description);

        System.out.println("✔ Dépense #" + id + " modifiée avec succès.");
        verifierBudget(categorie);
        return true;
    }

    /**
     * Supprime une dépense par son ID.
     */
    public boolean supprimerDepense(int id) {
        Depense d = trouverParId(id);
        if (d == null) return false;
        depenses.remove(d);
        System.out.println("✔ Dépense #" + id + " supprimée.");
        return true;
    }

    /**
     * Retourne les dépenses filtrées par catégorie.
     */
    public List<Depense> getDepensesParCategorie(Categorie categorie) {
        List<Depense> resultat = new ArrayList<>();
        for (Depense d : depenses) {
            if (d.getCategorie() == categorie) resultat.add(d);
        }
        return resultat;
    }

    /**
     * Retourne les dépenses d'un mois donné (ex: 2026-03).
     */
    public List<Depense> getDepensesParMois(int annee, int mois) {
        List<Depense> resultat = new ArrayList<>();
        for (Depense d : depenses) {
            if (d.getDate().getYear() == annee && d.getDate().getMonthValue() == mois) {
                resultat.add(d);
            }
        }
        return resultat;
    }

    /**
     * Calcule le total des dépenses d'une catégorie.
     */
    public double getTotalParCategorie(Categorie categorie) {
        double total = 0;
        for (Depense d : getDepensesParCategorie(categorie)) {
            total += d.getMontant();
        }
        return total;
    }

    /**
     * Calcule le total général de toutes les dépenses.
     */
    public double getTotalGeneral() {
        double total = 0;
        for (Depense d : depenses) total += d.getMontant();
        return total;
    }

    // ══════════════════════════════════════════════════════════════════
    //  GESTION DES BUDGETS
    // ══════════════════════════════════════════════════════════════════

    /**
     * Définit ou met à jour le budget d'une catégorie.
     */
    public void definirBudget(Categorie categorie, double montant) {
        budgets.put(categorie, new Budget(categorie, montant));
        System.out.println("✔ Budget défini : " + montant + " FCFA pour " + categorie);
    }

    /**
     * Retourne le budget d'une catégorie (null si non défini).
     */
    public Budget getBudget(Categorie categorie) {
        return budgets.get(categorie);
    }

    /**
     * Retourne tous les budgets.
     */
    public Map<Categorie, Budget> getBudgets() {
        return budgets;
    }

    /**
     * Vérifie si le budget d'une catégorie est dépassé et affiche une alerte.
     */
    public void verifierBudget(Categorie categorie) {
        Budget b = budgets.get(categorie);
        if (b == null) return;

        double total = getTotalParCategorie(categorie);
        if (b.estDepasse(total)) {
            System.out.println("⚠  ALERTE : Budget DEPASSE pour " + categorie
                    + " ! (Dépensé : " + total + " FCFA / Budget : " + b.getMontantAlloue() + " FCFA)");
        }
    }
}
