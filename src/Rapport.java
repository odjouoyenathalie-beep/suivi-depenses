import java.util.List;
import java.util.Map;

/**
 * Génère les rapports de dépenses dans le terminal.
 */
public class Rapport {

    private GestionDepenses gestion;

    public Rapport(GestionDepenses gestion) {
        this.gestion = gestion;
    }

    // ══════════════════════════════════════════════════════════════════
    //  RAPPORT PAR CATÉGORIE
    // ══════════════════════════════════════════════════════════════════

    public void afficherRapportCategories() {
        System.out.println("\n" + ligne('=', 60));
        System.out.println("       RAPPORT PAR CATEGORIE");
        System.out.println(ligne('=', 60));

        double totalGeneral = 0;

        for (Categorie cat : Categorie.values()) {
            double total = gestion.getTotalParCategorie(cat);
            if (total == 0) continue;  // on n'affiche que les catégories utilisées

            totalGeneral += total;
            Budget budget = gestion.getBudget(cat);

            System.out.printf("%-12s : %10.2f FCFA", cat, total);

            if (budget != null) {
                double restant = budget.getMontantRestant(total);
                String statut = budget.estDepasse(total) ? "  ⚠ DEPASSE" : "  OK";
                System.out.printf("  |  Budget: %10.2f  |  Restant: %10.2f%s",
                        budget.getMontantAlloue(), restant, statut);
            }
            System.out.println();
        }

        System.out.println(ligne('-', 60));
        System.out.printf("TOTAL GENERAL  : %10.2f FCFA%n", totalGeneral);
        System.out.println(ligne('=', 60) + "\n");
    }

    // ══════════════════════════════════════════════════════════════════
    //  RAPPORT PAR MOIS
    // ══════════════════════════════════════════════════════════════════

    public void afficherRapportMois(int annee, int mois) {
        List<Depense> liste = gestion.getDepensesParMois(annee, mois);

        System.out.println("\n" + ligne('=', 60));
        System.out.printf("   RAPPORT DU MOIS %02d/%d%n", mois, annee);
        System.out.println(ligne('=', 60));

        if (liste.isEmpty()) {
            System.out.println("  Aucune dépense enregistrée pour ce mois.");
        } else {
            double total = 0;
            for (Depense d : liste) {
                System.out.println("  " + d);
                total += d.getMontant();
            }
            System.out.println(ligne('-', 60));
            System.out.printf("  TOTAL : %.2f FCFA (%d dépense(s))%n", total, liste.size());
        }

        System.out.println(ligne('=', 60) + "\n");
    }

    // ══════════════════════════════════════════════════════════════════
    //  LISTE COMPLÈTE DES DÉPENSES
    // ══════════════════════════════════════════════════════════════════

    public void afficherToutesDepenses() {
        List<Depense> liste = gestion.getDepenses();

        System.out.println("\n" + ligne('=', 70));
        System.out.println("         LISTE DE TOUTES LES DEPENSES");
        System.out.println(ligne('=', 70));

        if (liste.isEmpty()) {
            System.out.println("  Aucune dépense enregistrée.");
        } else {
            for (Depense d : liste) {
                System.out.println("  " + d);
            }
            System.out.println(ligne('-', 70));
            System.out.printf("  TOTAL : %.2f FCFA (%d dépense(s))%n",
                    gestion.getTotalGeneral(), liste.size());
        }

        System.out.println(ligne('=', 70) + "\n");
    }

    // ══════════════════════════════════════════════════════════════════
    //  UTILITAIRE
    // ══════════════════════════════════════════════════════════════════

    private String ligne(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}
