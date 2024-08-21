package BudgetMaster;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String nom;
    private double budgetMensuel;
    private List<Depense> listeDepense;

    public User(String nom, double budgetMensuel) {
        this.nom = nom;
        this.budgetMensuel = budgetMensuel;
        this.listeDepense = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }
    public double getBudgetMensuel() {
        return budgetMensuel;
    }
    public List<Depense> getListeDepense() {
        return listeDepense;
    }

    public void addExpense(Depense depenseAdd){
        listeDepense.add(depenseAdd);
    }
    public void getExpenseByCategory( CategorieDepense categorie){
        listeDepense.stream().filter(
                Depense -> Depense.getCategorieDepense().equals(categorie));
    }
    public double getTotalSpentThisMonth(){
        Month todayMonth = LocalDateTime.now().getMonth();
        List<Depense> depenseMois = listeDepense
                .stream()
                .filter(Depense -> Depense.getDateDepense().getMonth().equals(todayMonth))
                .toList();
        double sum = 0;
        for (Depense depenseEnCours : depenseMois) {
            sum += depenseEnCours.getMontant();
        }
        return sum;
    }
    public double getRemainingBudget(double budgetMensuel){
        return budgetMensuel-getTotalSpentThisMonth() ;
    }


    public static void main(String[] args) {
        User user1 = new User("max",12000.00);
        Depense depense = new Depense("sakafo",4500.00,CategorieDepense.NOURRITURE, LocalDateTime.now());
        user1.addExpense(depense);
        System.out.println(user1.getTotalSpentThisMonth());
        System.out.println(user1.getRemainingBudget(12000.00));
    }
}

