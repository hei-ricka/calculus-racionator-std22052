package racionator;

import com.ricka.princy.racionator.Affirmation;
import com.ricka.princy.racionator.Reponse;
import com.ricka.princy.racionator.TypeDAffirmation;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationEtTest {
    @Test
    public void doit_etre_vraie_si_les_deux_affirmation_sont_verite(){
        Affirmation verite = new Affirmation("Lou est beau.", TypeDAffirmation.VERITE);
        assertEquals(verite.et(verite).calculer(), Reponse.vrai);
    }

    @Test
    public void doit_etre_fausse_si_une_des_affirmation_est_mensonge(){
        Affirmation verite = new Affirmation("Lou est beau.", TypeDAffirmation.VERITE);
        Affirmation affirmation = new Affirmation("Lou est généreux.", TypeDAffirmation.AFFIRMATION);
        Affirmation mensonge = new Affirmation("Lou est pauvre", TypeDAffirmation.MENSONGE);
        assertEquals(verite.et(verite).et(mensonge.et(verite)).calculer(), Reponse.faux);
        assertEquals(verite.et(mensonge).et(affirmation.et(verite)).calculer(), Reponse.faux);
    }

    @Test
    public void doit_etre_je_sais_pas_si_une_affirmation_est_inconue(){
        Affirmation verite = new Affirmation("Lou est beau.", TypeDAffirmation.VERITE);
        Affirmation affirmation = new Affirmation("Lou est généreux.", TypeDAffirmation.AFFIRMATION);
        assertEquals(verite.et(affirmation).calculer(), Reponse.jenesaispas);
        assertEquals(verite.et(affirmation.et(verite)).calculer(), Reponse.jenesaispas);
    }
}