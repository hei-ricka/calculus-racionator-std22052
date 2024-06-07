package racionator;

import com.ricka.princy.racionator.Affirmation;
import com.ricka.princy.racionator.Reponse;
import com.ricka.princy.racionator.TypeDAffirmation;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationOuTest {
    @Test
    public void doit_etre_vrai_si_une_affirmation_est_verite(){
        Affirmation verite = new Affirmation("Lou est beau.", TypeDAffirmation.VERITE);
        Affirmation affirmation = new Affirmation("Lou est généreux.", TypeDAffirmation.AFFIRMATION);
        Affirmation mensonge = new Affirmation("Lou est pauvre", TypeDAffirmation.MENSONGE);

        assertEquals(verite.ou(verite).calculer(), Reponse.vrai);
        assertEquals(mensonge.ou(verite).calculer(), Reponse.vrai);
        assertEquals(verite.ou(affirmation).calculer(), Reponse.vrai);
        assertEquals(mensonge.ou(affirmation.ou(verite)).calculer(), Reponse.vrai);
    }

    @Test
    public void doit_etre_faux_si_tout_est_mensonge(){
        Affirmation mensonge = new Affirmation("Lou est pauvre", TypeDAffirmation.MENSONGE);
        assertEquals(mensonge.ou(mensonge).calculer(), Reponse.faux);
    }

    @Test
    public void doit_etre_jenesaispas_si_une_affirmation_est_inconue_et_lautre_mensonge_ou_inconue_aussi(){
        Affirmation mensonge = new Affirmation("Lou est pauvre", TypeDAffirmation.MENSONGE);
        Affirmation affirmation = new Affirmation("Lou est pauvre", TypeDAffirmation.AFFIRMATION);
        assertEquals(mensonge.ou(affirmation).calculer(), Reponse.jenesaispas);
        assertEquals(affirmation.ou(mensonge.ou(mensonge)).calculer(), Reponse.jenesaispas);
    }
}