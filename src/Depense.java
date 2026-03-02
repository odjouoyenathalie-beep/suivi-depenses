import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Représente une dépense personnelle.
 */
public class Depense {

    private static int compteurId = 1;

    private int id;
    private double montant;
    private LocalDate date;
    private Categorie categorie;
    private String description;

    // Formateur de date commun
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructeur principal.
     */
    public Depense(double montant, LocalDate date, Categorie categorie, String description) {
        this.id          = compteurId++;
        this.montant     = montant;
        this.date        = date;
        this.categorie   = categorie;
        this.description = description;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getId()              { return id; }
    public double getMontant()      { return montant; }
    public LocalDate getDate()      { return date; }
    public Categorie getCategorie() { return categorie; }
    public String getDescription()  { return description; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setMontant(double montant)         { this.montant = montant; }
    public void setDate(LocalDate date)            { this.date = date; }
    public void setCategorie(Categorie categorie)  { this.categorie = categorie; }
    public void setDescription(String description) { this.description = description; }

    // ── Affichage ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format("[#%d] %s | %-12s | %10.2f FCFA | %s",
                id,
                date.format(FORMATTER),
                categorie,
                montant,
                description);
    }
}
