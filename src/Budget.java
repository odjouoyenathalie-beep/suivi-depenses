/**
 * Représente un budget mensuel alloué à une catégorie.
 */
public class Budget {

    private Categorie categorie;
    private double montantAlloue;

    public Budget(Categorie categorie, double montantAlloue) {
        this.categorie     = categorie;
        this.montantAlloue = montantAlloue;
    }

    // ── Getters / Setters ─────────────────────────────────────────────────────

    public Categorie getCategorie()           { return categorie; }
    public double getMontantAlloue()          { return montantAlloue; }
    public void setMontantAlloue(double m)    { this.montantAlloue = m; }

    /**
     * Calcule le montant restant après avoir dépensé "totalDepense".
     */
    public double getMontantRestant(double totalDepense) {
        return montantAlloue - totalDepense;
    }

    /**
     * Vérifie si le budget est dépassé.
     */
    public boolean estDepasse(double totalDepense) {
        return totalDepense > montantAlloue;
    }

    @Override
    public String toString() {
        return String.format("Budget %-12s : %.2f FCFA", categorie, montantAlloue);
    }
}
