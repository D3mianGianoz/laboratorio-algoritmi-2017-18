package editdistance;

import editdistance.*;

import com.sun.org.apache.xpath.internal.functions.FuncSubstringBefore;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class EditDistanceTests {
    private String s1, s2, word1, word2, name1, name2;

    @Before
    public void initVariable() 
    {
        s1 = "casa";
        s2 = "cassa";
        word1 = "tassa";
        word2 = "passato";
        name1 = "pioppo";
        name2 = "pioppo";
    }

    @Test
    public void bothStrignAreEmpty()
    {
        String empty = "";
        String voidd = "";

        assertTrue(EditDistance.edit_distance(voidd,empty) == 0);
    }

    @Test
    public void bothStrignAreEmpty_dyn()
    {
        String empty = "";
        String voidd = "";

        assertTrue(EditDistance.edit_distance_dyn(voidd,empty) == 0);
    }

    @Test
    public void oneStrignIsEmpty()
    {
        String empty = "";

        assertTrue(EditDistance.edit_distance(s1,empty) == s1.length());
    }

    @Test
    public void oneStrignIsEmpty_dyn()
    {
        String empty = "";

        assertTrue(EditDistance.edit_distance_dyn(s1,empty) == s1.length());
    }

    @Test
    public void firstExample()
    {
        assertTrue(EditDistance.edit_distance(s1, s2) == 1);
    }

    @Test
    public void firstExample_dyn()
    {
        assertTrue(EditDistance.edit_distance_dyn(s1, s2) == 1);
    }

    @Test
    public void secondExample()
    {
        assertFalse(EditDistance.edit_distance(word1, word2) == 3);
    }

    @Test
    public void secondExample_dyn()
    {
        assertFalse(EditDistance.edit_distance_dyn(word1, word2) == 5);
    }

    @Test
    public void thirdExample()
    {
        assertFalse(EditDistance.edit_distance(name1, name2) == 2);
    }

    @Test
    public void thirdExample_dyn()
    {
        assertFalse(EditDistance.edit_distance_dyn(name1, name2) == 6);
    }

}
