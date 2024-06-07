package com.ricka.princy.racionator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Affirmation {
    private final String contenu;
    private final TypeDAffirmation typeDAffirmation;

    public Affirmation ou(Affirmation affirmation){
        throw new Error("Not implemented");
    }

    public Affirmation et(Affirmation affirmation){
        throw new Error("Not implemented");
    }

    public Affirmation donc(Affirmation affirmation){
        throw new Error("Not implemented");
    }

    public Reponse calculer(){
        throw new Error("Not implemented");
    }
}
