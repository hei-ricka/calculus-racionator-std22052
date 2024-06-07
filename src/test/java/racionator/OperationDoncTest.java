package racionator;

import com.ricka.princy.racionator.Affirmation;
import com.ricka.princy.racionator.Reponse;
import com.ricka.princy.racionator.TypeDAffirmation;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationDoncTest {
    @Test
    public void doit_etre_vraie_si_le_premier_est_mensonge_et_deuxieme_verite(){
        Affirmation verite = new Affirmation("Lou est beau.", TypeDAffirmation.VERITE);
        Affirmation mensonge = new Affirmation("Lou est pauvre", TypeDAffirmation.MENSONGE);

        assertEquals(mensonge.donc(verite).calculer(), Reponse.vrai);
        assertEquals(mensonge.donc(mensonge).calculer(), Reponse.vrai);
        assertEquals(verite.donc(verite).donc(mensonge.donc(verite)).calculer(), Reponse.vrai);
    }

    @Test
    public void doit_etre_fausse_si_premier_verite_et_lautre_mensonge(){
        Affirmation verite = new Affirmation("Lou est beau.", TypeDAffirmation.VERITE);
        Affirmation mensonge = new Affirmation("Lou est pauvre", TypeDAffirmation.MENSONGE);
        assertEquals(mensonge.donc(verite).donc(verite.donc(mensonge)).calculer(), Reponse.faux);
        assertEquals(verite.donc(mensonge).calculer(), Reponse.faux);
    }

    @Test
    public void doit_etre_je_sais_pas_si_une_affirmation_est_inconue(){
        Affirmation verite = new Affirmation("Lou est beau.", TypeDAffirmation.VERITE);
        Affirmation affirmation = new Affirmation("Lou est généreux.", TypeDAffirmation.AFFIRMATION);
        assertEquals(verite.donc(affirmation).calculer(), Reponse.jenesaispas);
        assertEquals(affirmation.donc(affirmation.donc(affirmation)).calculer(), Reponse.jenesaispas);
    }
}
