import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Point d'entrée de l'application.
 * Gère le menu principal en mode console.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GestionDepenses gestion = new GestionDepenses();
    private static Rapport rapport = new Rapport(gestion);

    public static void main(String[] args) {
        afficherBienvenue();
        boolean continuer = true;

        while (continuer) {
            afficherMenuPrincipal();
            int choix = lireEntier("Votre choix : ");

            switch (choix) {
                case 1: ajouterDepense();       break;
                case 2: afficherDepenses();     break;
                case 3: modifierDepense();      break;
                case 4: supprimerDepense();     break;
                case 5: definirBudget();        break;
                case 6: menuRapports();         break;
                case 7:
                    System.out.println("\n  Au revoir ! Bonne gestion de vos finances. 👋\n");
                    continuer = false;
                    break;
                default:
                    System.out.println("  ⚠ Choix invalide. Veuillez entrer un nombre entre 1 et 7.");
            }
        }

        scanner.close();
    }

    // ══════════════════════════════════════════════════════════════════
    //  MENUS
    // ══════════════════════════════════════════════════════════════════

    private static void afficherBienvenue() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║   OUTIL DE SUIVI DES DEPENSES PERSONNELLES  ║");
        System.out.println("║         Université de Natitingou - FAST      ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");
    }

    private static void afficherMenuPrincipal() {
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│         MENU PRINCIPAL          │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│  1. Ajouter une dépense         │");
        System.out.println("│  2. Afficher toutes les dépenses│");
        System.out.println("│  3. Modifier une dépense        │");
        System.out.println("│  4. Supprimer une dépense       │");
        System.out.println("│  5. Définir un budget           │");
        System.out.println("│  6. Rapports                    │");
        System.out.println("│  7. Quitter                     │");
        System.out.println("└─────────────────────────────────┘");
    }

    private static void menuRapports() {
        System.out.println("\n--- RAPPORTS ---");
        System.out.println("  1. Rapport par catégorie");
        System.out.println("  2. Rapport par mois");
        System.out.println("  3. Retour");

        int choix = lireEntier("Votre choix : ");
        switch (choix) {
            case 1:
                rapport.afficherRapportCategories();
                break;
            case 2:
                int annee = lireEntier("Année (ex: 2026) : ");
                int mois  = lireEntier("Mois (1-12)      : ");
                rapport.afficherRapportMois(annee, mois);
                break;
            case 3:
                break;
            default:
                System.out.println("  Choix invalide.");
        }
    }

    // ══════════════════════════════════════════════════════════════════
    //  ACTIONS DÉPENSES
    // ══════════════════════════════════════════════════════════════════

    private static void ajouterDepense() {
        System.out.println("\n--- AJOUTER UNE DEPENSE ---");

        double montant = lireDouble("Montant (FCFA) : ");
        LocalDate date = lireDate("Date (jj/mm/aaaa) : ");

        System.out.println("Choisissez une catégorie :");
        Categorie.afficherCategories();
        int choixCat = lireEntier("Votre choix : ");
        Categorie categorie;
        try {
            categorie = Categorie.fromChoix(choixCat);
        } catch (IllegalArgumentException e) {
            System.out.println("  ⚠ Catégorie invalide.");
            return;
        }

        System.out.print("Description : ");
        scanner.nextLine();
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) description = "(aucune description)";

        gestion.ajouterDepense(montant, date, categorie, description);
    }

    private static void afficherDepenses() {
        rapport.afficherToutesDepenses();
    }

    private static void modifierDepense() {
        rapport.afficherToutesDepenses();
        if (gestion.getDepenses().isEmpty()) return;

        int id = lireEntier("ID de la dépense à modifier : ");
        if (gestion.trouverParId(id) == null) {
            System.out.println("  ⚠ Aucune dépense avec l'ID #" + id);
            return;
        }

        System.out.println("\n--- NOUVELLES VALEURS pour la dépense #" + id + " ---");
        double montant = lireDouble("Nouveau montant (FCFA) : ");
        LocalDate date = lireDate("Nouvelle date (jj/mm/aaaa) : ");

        System.out.println("Nouvelle catégorie :");
        Categorie.afficherCategories();
        int choixCat = lireEntier("Votre choix : ");
        Categorie categorie;
        try {
            categorie = Categorie.fromChoix(choixCat);
        } catch (IllegalArgumentException e) {
            System.out.println("  ⚠ Catégorie invalide.");
            return;
        }

        System.out.print("Nouvelle description : ");
        scanner.nextLine();
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) description = "(aucune description)";

        gestion.modifierDepense(id, montant, date, categorie, description);
    }

    private static void supprimerDepense() {
        rapport.afficherToutesDepenses();
        if (gestion.getDepenses().isEmpty()) return;

        int id = lireEntier("ID de la dépense à supprimer : ");
        if (!gestion.supprimerDepense(id)) {
            System.out.println("  ⚠ Aucune dépense avec l'ID #" + id);
        }
    }

    private static void definirBudget() {
        System.out.println("\n--- DEFINIR UN BUDGET ---");
        System.out.println("Choisissez une catégorie :");
        Categorie.afficherCategories();

        int choixCat = lireEntier("Votre choix : ");
        Categorie categorie;
        try {
            categorie = Categorie.fromChoix(choixCat);
        } catch (IllegalArgumentException e) {
            System.out.println("  ⚠ Catégorie invalide.");
            return;
        }

        double montant = lireDouble("Budget mensuel (FCFA) : ");
        gestion.definirBudget(categorie, montant);
    }

    // ══════════════════════════════════════════════════════════════════
    //  UTILITAIRES DE SAISIE (avec validation)
    // ══════════════════════════════════════════════════════════════════

    private static int lireEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                int val = scanner.nextInt();
                scanner.nextLine(); // vider le buffer
                return val;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("  ⚠ Veuillez entrer un nombre entier valide.");
            }
        }
    }

    private static double lireDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double val = scanner.nextDouble();
                scanner.nextLine();
                if (val <= 0) {
                    System.out.println("  ⚠ Le montant doit être supérieur à 0.");
                    continue;
                }
                return val;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("  ⚠ Veuillez entrer un montant valide (ex: 1500.50).");
            }
        }
    }

    private static LocalDate lireDate(String message) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim();
            try {
                return LocalDate.parse(saisie, Depense.FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("  ⚠ Format invalide. Utilisez jj/mm/aaaa (ex: 01/03/2026).");
            }
        }
    }
}
