package racionator;

import com.ricka.princy.racionator.Affirmation;
import com.ricka.princy.racionator.Reponse;
import com.ricka.princy.racionator.TypeDAffirmation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperationMixteTest {
    @Test
    public void doit_etre_faux(){
        Affirmation mensonge = new Affirmation("Lou est pauvre", TypeDAffirmation.MENSONGE);
        Affirmation affirmation = new Affirmation("Lou est généreux.", TypeDAffirmation.AFFIRMATION);
        Affirmation verite = new Affirmation("Lou est beau.", TypeDAffirmation.VERITE);

        assertEquals(mensonge.et(affirmation).calculer(), Reponse.faux);
        assertEquals(verite.donc(mensonge).calculer(), Reponse.faux);
        assertEquals(verite.ou(affirmation).donc(mensonge).calculer(), Reponse.faux);
        assertEquals((verite.ou(affirmation).donc(mensonge)).et(mensonge.donc(affirmation)).calculer(), Reponse.faux);
    }

    @Test
    public void doit_etre_vrai(){
        Affirmation affirmation = new Affirmation("Lou est généreux.", TypeDAffirmation.AFFIRMATION);
        Affirmation mensonge = new Affirmation("Lou est pauvre", TypeDAffirmation.MENSONGE);

        assertEquals(mensonge.donc(affirmation).calculer(), Reponse.vrai);
    }
}