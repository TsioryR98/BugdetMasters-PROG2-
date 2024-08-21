package BudgetMaster;

import PROG2Exam.Playlist;

import java.time.LocalDateTime;
import java.util.List;

public class Depense {
    private String description;
    private double montant;
    private CategorieDepense categorieDepense;
    private LocalDateTime dateDepense;

    public Depense(String description, double montant, CategorieDepense categorieDepense, LocalDateTime dateDepense) {
        this.description = description;
        this.montant = montant;
        this.categorieDepense = categorieDepense;
        this.dateDepense = dateDepense;
    }
    public String getDescription() {
        return description;
    }
    public double getMontant() {
        return montant;
    }
    public CategorieDepense getCategorieDepense() {
        return categorieDepense;
    }
    public LocalDateTime getDateDepense() {
        return dateDepense;
    }

    public void setMontant(double montant) {
        if (montant > 0) {
            this.montant = montant;
        }
        else{
            System.out.println("depense doit etre positif");
        }
    }


}
