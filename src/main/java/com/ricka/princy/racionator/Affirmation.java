package com.ricka.princy.racionator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Affirmation {
    private final String contenu;
    private final TypeDAffirmation typeDAffirmation;

    public final Affirmation et(Affirmation affirmation){
        final String nouveauContenu = combinerContenu(this, affirmation, "ou");

        if(estVrai(this) && estVrai(affirmation)){
            return new Affirmation(nouveauContenu, TypeDAffirmation.VERITE);
        }
        if(estFaux(this) || estFaux(affirmation)){
            return new Affirmation(nouveauContenu, TypeDAffirmation.MENSONGE);
        }
        return new Affirmation(nouveauContenu, TypeDAffirmation.AFFIRMATION);
    }

    public final Affirmation ou(Affirmation affirmation){
        final String nouveauContenu = combinerContenu(this, affirmation, "ou");

        if(estVrai(this) || estVrai(affirmation)){
            return new Affirmation(nouveauContenu, TypeDAffirmation.VERITE);
        }
        if(estJenesaispas(this) || estJenesaispas(affirmation)){
            return new Affirmation(nouveauContenu, TypeDAffirmation.AFFIRMATION);
        }
        return new Affirmation(nouveauContenu, TypeDAffirmation.MENSONGE);
    }

    public final Affirmation donc(Affirmation affirmation){
        final String nouveauContenu = combinerContenu(this, affirmation, "donc");

        if(estVrai(this) && estFaux(affirmation)){
            return new Affirmation(nouveauContenu, TypeDAffirmation.MENSONGE);
        }
        if(estFaux(this) || estVrai(affirmation)){
            return new Affirmation(nouveauContenu, TypeDAffirmation.VERITE);
        }
        return new Affirmation(nouveauContenu, TypeDAffirmation.AFFIRMATION);
    }

    public final Reponse calculer(){
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
        return String.format("%s %s %s", a.contenu, b.contenu, separateur);
    }
}