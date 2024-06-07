package com.ricka.princy.racionator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Affirmation {
    private final String contenu;
    private final TypeDAffirmation typeDAffirmation;

    public Affirmation et(Affirmation affirmation){
        final String nouveauCotenu = combinerContenu(this, affirmation, "ou");

        if(estVrai(this) && estVrai(affirmation)){
            return new Affirmation(nouveauCotenu, TypeDAffirmation.VERITE);
        }
        if(estFaux(this) || estFaux(affirmation)){
            return new Affirmation(nouveauCotenu, TypeDAffirmation.MENSONGE);
        }

        return new Affirmation(nouveauCotenu, TypeDAffirmation.AFFIRMATION);
    }

    public Affirmation ou(Affirmation affirmation){
        final String nouveauCotenu = combinerContenu(this, affirmation, "ou");

        if(estVrai(this) || estVrai(affirmation)){
            return new Affirmation(nouveauCotenu, TypeDAffirmation.VERITE);
        }
        if(estJenesaispas(this) || estJenesaispas(affirmation)){
            return new Affirmation(nouveauCotenu, TypeDAffirmation.AFFIRMATION);
        }

        return new Affirmation(nouveauCotenu, TypeDAffirmation.MENSONGE);
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