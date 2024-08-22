package BudgetMaster;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
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

    public String getTopCategories(){
        /*
        Map<Double, CategorieDepense> depenseParCategories = new HashMap<>();
        for (Depense depenseEnCour : listeDepense) {
            depenseParCategories.put(depenseEnCour.getMontant(),depenseEnCour.getCategorieDepense());
        }

        Map<CategorieDepense, Double> totalParCategories = new HashMap<>();
        for (Map.Entry<Double, CategorieDepense> doubleCategorieDepenseEntry :
                depenseParCategories.entrySet()) {
            CategorieDepense categorieDepenses = doubleCategorieDepenseEntry.getValue();
            Double montantDepense = doubleCategorieDepenseEntry.getKey();
            totalParCategories.put(categorieDepenses,totalParCategories
                    .getOrDefault(categorieDepenses,0.0)+montantDepense);
        }
        for (Map.Entry<CategorieDepense, Double> categorieDepenseDoubleEntry :
                totalParCategories.entrySet()) {
            System.out.println("categorie de depense : " + categorieDepenseDoubleEntry.getKey()
                                + " total :" + categorieDepenseDoubleEntry.getValue()
            );
        }
        */
        Map<CategorieDepense, Double> totalParCategories = new HashMap<>();
        for (Depense depenseEnCour : listeDepense) {
            totalParCategories.put(depenseEnCour.getCategorieDepense(),depenseEnCour.getMontant());
        }
        //mettre Map dans List
        List<Map.Entry<CategorieDepense,Double>> topCategorie = totalParCategories.entrySet().stream()
                .sorted(Map.Entry.<CategorieDepense, Double>comparingByValue().reversed())
                .limit(3)
                .toList();
        for (Map.Entry<CategorieDepense, Double> categorieDepenseDoubleEntry : topCategorie) {
            System.out.println("Categorie: " +categorieDepenseDoubleEntry.getKey() +
                                " /Total : " + categorieDepenseDoubleEntry.getValue()
            );
        }
        return topCategorie.stream()
                .map(categorieDepenseDoubleEntry -> categorieDepenseDoubleEntry.getKey().toString() +": "+
                        categorieDepenseDoubleEntry.getValue().toString())
                .collect(Collectors.joining(" "));
    }


    public static void main(String[] args) {
        User user1 = new User("max",240000.00);
        Depense depense = new Depense("Nourriture",4500.00,CategorieDepense.NOURRITURE, LocalDateTime.now());
        Depense depense1 = new Depense("Frais",6000.00,CategorieDepense.TRANSPORT, LocalDateTime.now());
        Depense depense2 = new Depense("Autre",1500.00,CategorieDepense.AUTRES, LocalDateTime.now());
        Depense depense3 = new Depense("Nourriture",1800.00,CategorieDepense.NOURRITURE, LocalDateTime.now());
        Depense depense4 = new Depense("divertissement",2400.00,CategorieDepense.DIVERTISSEMENT, LocalDateTime.now());
        Depense depense5 = new Depense("Frais",1200.00,CategorieDepense.TRANSPORT, LocalDateTime.now());
        Depense depense6 = new Depense("divertissement",3100.00,CategorieDepense.DIVERTISSEMENT, LocalDateTime.now());

        user1.addExpense(depense);
        user1.addExpense(depense1);
        user1.addExpense(depense2);
        user1.addExpense(depense3);
        user1.addExpense(depense4);
        user1.addExpense(depense5);
        user1.addExpense(depense6);

        System.out.println(user1.getTotalSpentThisMonth());
        System.out.println(user1.getRemainingBudget(24000.00));
        System.out.println(user1.getTopCategories());
    }
}

