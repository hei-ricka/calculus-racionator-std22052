package com.ricka.princy.racionator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Affirmation {
    private final String contenu;
    private final TypeDAffirmation typeDAffirmation;

    public Affirmation et(Affirmation affirmation){
        final String nouveau_contenu = combinerContenu(this, affirmation, "ou");

        if(estVrai(this) && estVrai(affirmation)){
            return new Affirmation(nouveau_contenu, TypeDAffirmation.VERITE);
        }
        if(estFaux(this) || estFaux(affirmation)){
            return new Affirmation(nouveau_contenu, TypeDAffirmation.MENSONGE);
        }

        return new Affirmation(nouveau_contenu, TypeDAffirmation.AFFIRMATION);
    }

    public Affirmation ou(Affirmation affirmation){
        throw new Error("Not implemented");
    }

    public Affirmation donc(Affirmation affirmation){
        throw new Error("Not implemented");
    }

    public Reponse calculer(){
        return switch (this.typeDAffirmation){
            case AFFIRMATION -> Reponse.jenesaispas;
            case MENSONGE -> Reponse.faux;
            case VERITE -> Reponse.vrai;
        };
    }

    private static boolean estVrai(Affirmation affirmation){
        return affirmation.typeDAffirmation.equals(TypeDAffirmation.VERITE);
    }

    private static boolean estJenesaispas(Affirmation affirmation){
        return affirmation.typeDAffirmation.equals(TypeDAffirmation.AFFIRMATION);
    }

    private static boolean estFaux(Affirmation affirmation){
        return affirmation.typeDAffirmation.equals(TypeDAffirmation.MENSONGE);
    }

    private String combinerContenu(Affirmation a,Affirmation b, String separateur){
        return String.format("%s %s %s", a, b, separateur);
    }
}