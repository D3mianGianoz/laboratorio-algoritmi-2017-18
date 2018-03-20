public class Test {

    public static void main(String[] Args)
    {
        SortedArray<Integer> srt = new SortedArray<Integer>(5);
        for(int i = 0;i<4;i++)
        {
            boolean b = srt.addItem(i+5);
        }
        srt.addToIndex(4, 2);
        srt.printArray();
        System.out.println("********************************");
        //srt.removeItem(3);
        srt.sortArrayAsc();
        srt.printArray();
        System.out.println("********************************");
        srt.sortArrayDes();
        srt.printArray();
    }
    



}