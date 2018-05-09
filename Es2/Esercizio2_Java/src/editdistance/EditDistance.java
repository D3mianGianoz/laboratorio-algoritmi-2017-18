package editdistance;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 */

public class EditDistance {

    /**
     * @param s1,s2: two random string to be compared, not necessarily of the samelength
     * @return: a integer rappresenting the minimum number of operations required to transform the string s2 into s1
     */

    public static int edit_distance(String s1, String s2) {

        if (s1.isEmpty())
            return s2.length();
        else if (s2.isEmpty())
            return s1.length();

        return minTree(no_op(s1, s2), canc(s1, s2), ins(s1, s2));
    }

    /**
     * @param s1,s2: random string
     * @return: an integer; in particular a recursive call to edit_distance
     * if the condition is true, else a MAX_VALUE
     */
    private static int no_op(String s1, String s2) {
        if (s1.charAt(0) == s2.charAt(0))
            return edit_distance(rest(s1), rest(s2));
        return Integer.MAX_VALUE;
    }

    /**
     * @param s1,s2: random string
     * @return: an integer, in particular a recursive call to edit_distance plus 1
     * rappresenting the deletion of a character
     */

    private static int canc(String s1, String s2) {
        return 1 + edit_distance(rest(s1), s2);
    }
    
    /**
     * @param s1,s2: random string
     * @return: an integer, in particular a recursive call to edit_distance plus 1
     * rappresenting the the insertion of a character
     */

    private static int ins(String s1, String s2) {
        return 1 + edit_distance(s1, rest(s2));
    }

    /**
     * @param string: a random string
     * @return: the substring obtained by ignoring the first character
     */

    private static String rest(String string) {
        return string.substring(1);
    }

    /**
     * @param num1,num2,num3: tree random integer
     * @return: the smallest integer between tree
     */

    private static int minTree(int num1, int num2, int num3) {
        if(num1 < num2 && num1 < num3){
            smallest = num1;
        }else if(num2 < num1 && num2 < num3){
            smallest = num2;
        }else{
            smallest = num3;
        }
        return smallest;
        //return Integer.min(Integer.min(num1, num2), num3);
    }

    /**
     * Dynamic implementation of edti_distance:
     * Create a table to store results of subproblems and fill it in bottom up manner
     * than consider all of the sub-cases
     * 
     * @param s1,s2: two random string to be compared, not necessarily of the samelength
     * @param s1length,s2length: length, respectively, of s1 and s2
     * @return: a integer rappresenting the minimum number of operations required to transform the string s2 into s1
     */

    public static int edit_distance_dyn(String str1, String str2, int s1length, int s2length){  //Iterativa
        int dyn_table[][] = new int[s1length+1][s2length+1];

        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                // If first string is empty, only option is to
                // insert all characters of second string
                if (i==0)
                    dyn_table[i][j] = j;  // Min. operations = j
        
                // If second string is empty, only option is to
                // remove all characters of second string
                else if (j==0)
                    dyn_table[i][j] = i; // Min. operations = i
        
                // If last characters are same, ignore last char
                // and recur for remaining string
                else if (str1.charAt(i-1) == str2.charAt(j-1)) // è Corretto ?
                    dyn_table[i][j] = dyn_table[i-1][j-1];
        
                // If last character are different, consider all
                // possibilities and find minimum
                else
                    dyn_table[i][j] = 1 + Math.min(dyn_table[i][j-1],  // Insert
                                        dyn_table[i-1][j]);  // Remove
                                        //dyn_table[i-1][j-1]); // Replace  //non richiesta
            }
        }
    
        return dyn_table[m][n];
    }
    
}
