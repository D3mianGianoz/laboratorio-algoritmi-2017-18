package editdistance;

/**
 * @author Costamagna Alberto e Gianotti Damiano
 */
public class EditDistance {

    /**
     * This function counts the minimum number of operations required to transform one string into the other.
     * 
     * @param s1,s2: two random string to be compared, not necessarily of the samelength
     * @return: a integer rappresenting the minimum number of operations
     */

    public static int edit_distance(String s1, String s2)
    {
        if (s1.isEmpty())
            return s2.length();
        else if (s2.isEmpty())
            return s1.length();

        return minTree(no_op(s1, s2), canc(s1, s2), ins(s1, s2));
    }

    /**
     * @param s1,s2: random string
     * @return: an integer; in particular a recursive call to edit_distance if the
     *          condition is true, else a MAX_VALUE
     */

    private static int no_op(String s1, String s2){
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
        int smallest;

        if(num1 < num2 && num1 < num3){
            smallest = num1;
        }else if(num2 < num1 && num2 < num3){
            smallest = num2;
        }else{
            smallest = num3;
        }
        return smallest;
    }

    /**
     * Dynamic implementation of edit_distance 
     * Create a matrix to store results of subproblems and initialize the matrix: -1 (-1 means unknown value)
     * 
     * @param s1,s2: two random string to be compared, not necessarily of the samelength
     * @return: a integer rappresenting the minimum number of operations required to transform the string s2 into s1
     */


    public static int edit_distance_dyn(String s1, String s2){
        int dyn_table[][] = new int[s1.length()][s2.length()];
        
        for(int i = 0; i < s1.length(); i++){
            for(int j = 0; j < s2.length(); j++){
                dyn_table[i][j] = -1;
            }
        }
        
        return edit_distance_supp(s1, s2, dyn_table);
    }

    /**
     * support function for edit_distance_dyn
     * 
     * @param s1,s2: two random string to be compared, not necessarily of the samelength
     * @param dyn_table: matrix used to store the result of the operation
     * @return: a integer rappresenting the minimum number of operations required to transform the string s2 into s1 using the matrix
     */

    private static int edit_distance_supp(String s1, String s2, int[][] dyn_table){
		if(s1.length() == 0)
			return s2.length();
		if(s2.length() == 0)
			return s1.length();
		if(s1.charAt(s1.length()-1) == s2.charAt(s2.length()-1))
			return edit_distance_supp(sub(s1), sub(s2), dyn_table);
			
		if(dyn_table[s1.length()-1][s2.length()-1] != -1)
			return dyn_table[s1.length()-1][s2.length()-1];
		else{
			dyn_table[s1.length()-1][s2.length()-1] = Math.min(edit_distance_supp(sub(s1), s2, dyn_table), edit_distance_supp(s1, sub(s2), dyn_table)) + 1;
			return Math.min(edit_distance_supp(sub(s1), s2, dyn_table), edit_distance_supp(s1, sub(s2), dyn_table)) + 1;
		}
		
    }

    /**
     * @param string: a random string
     * @return: the substring obtained by ignoring the last character
     */

    private static String sub(String s){
		return s.substring(0, s.length() - 1);
	}

}
