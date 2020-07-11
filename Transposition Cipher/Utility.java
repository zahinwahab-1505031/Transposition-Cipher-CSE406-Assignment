/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpositioncipher2;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Zahin
 */
public class Utility {

    public static List<String> permutedStrings = new ArrayList<String>();
    public static int[][] permutedNumbers = new int[2000][20];
    public static List<Integer[]> keys = new ArrayList<Integer[]>();
    static int count = 0;
    String str;
    int []arr;
    Utility(String str,int []arr){
        this.str = str;
        this.arr = arr;
        
    }
    public void hybridperm(Utility util,int start,int end,String decodedText){
        if (start == end) //  System.out.println(str); 
        {
            
            
          
                            if (decodedText.contains(util.str)) {
                               
                            //    printArray(util.arr,util.arr.length);
//                      	    System.out.println("");
                                int idx = decodedText.indexOf(util.str);
                                int add = idx % util.arr.length;
                                if (add != 0) {
                                    for (int var2 = 0; var2 < util.arr.length; var2++) {
                                        if (((util.arr[var2] + add) % (util.arr.length)) == 0) {
                                            util.arr[var2] = util.arr.length;
                                           // System.out.print(util.arr.length + " ");
                                        } else {
                                            util.arr[var2] = ((util.arr[var2] + add) % (util.arr.length));
                                           // System.out.print(util.arr[var2] + " ");
                                        }
                                    }
                                }

                                Integer[] keyArr = new Integer[util.arr.length];
                                for (int it3 = 0; it3 < util.arr.length; it3++) {
                                    keyArr[it3] = new Integer(util.arr[it3]);
                                }
                                keys.add(keyArr);

//                  
                             //   System.out.println(util.str + " is found at " + decodedText.indexOf(util.str));

                            }
        } else {
            for (int i = start; i <= end; i++) {
                util.str = swap(util.str, start, i);
                int temp = util.arr[start];
                util.arr[start] = util.arr[i];
                util.arr[i] = temp;
                hybridperm(util, start + 1, end,decodedText);
                util.str = swap(util.str, start, i);
                temp = util.arr[start];
                util.arr[start] = util.arr[i];
                util.arr[i] = temp;
            }
        }

    
    }
    public static void generatePermutations(String str, int start, int end) {
        if (start == end) //  System.out.println(str); 
        {
            permutedStrings.add(str);
        } else {
            for (int i = start; i <= end; i++) {
                str = swap(str, start, i);
                generatePermutations(str, start + 1, end);
                str = swap(str, start, i);
            }
        }

    }
    public static void printArray(Integer[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

//    public static String rearrange(String str, int[] arr, int n) {
//        char[] rearranged = new char[n];
//        for (int i = 0; i < n; i++) {
//            rearranged[arr[i] - 1] = str.charAt(i);
//
//        }
//
//        return new String(rearranged);
//    }
    public static String rearrange(String str, Integer[] arr, int n) {
        char[] rearranged = new char[n];
        for (int i = 0; i < n; i++) {
            rearranged[arr[i] - 1] = str.charAt(i);

        }

        return new String(rearranged);
    }
    
    public static String rearrange(String str, int[] arr, int n) {
        char[] rearranged = new char[n];
        for (int i = 0; i < n; i++) {
            rearranged[arr[i] - 1] = str.charAt(i);

        }

        return new String(rearranged);
    }

    public static void generatePermutationsNumbers(int[] arr, int start, int end,String tobepermuted,String decodedText) {
        if (start == end) 
        {
          
           // printArray(arr, arr.length);
           // System.out.println(rearranged);
           
//            int row = count;
//            for (int i = 0; i < arr.length; i++) {
//                permutedNumbers[row][i] = arr[i];
//            }
//            count++;
              
              

        } else {
            for (int i = start; i <= end; i++) {
                int temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
                generatePermutationsNumbers(arr, start + 1, end,tobepermuted,decodedText);
                temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
            }
        }

    }

    public static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static char[][] divide(String string, int n) {
        String[] parts = new String[n];

        int length = string.length() / n;
        char[][] matrix = new char[n][length];
        int counter = 0;
        char c;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = string.charAt(counter++);

            }
            parts[i] = new String(matrix[i]);

        }

        return matrix;
    }

    public static List<String> getSubsequenceOfFixedLength(String string, int length) {

        List<String> parts = new ArrayList<String>();
        for(int i=0;i<=(string.length()-length);i++){
             String sub = string.substring(i,i+length);
             parts.add(sub);
          //   System.out.println(sub);
        }
        return parts;
    }

    public static String[] divide2(String string, int length) {

        double nprime = string.length() / (length * 1.0);
        int n = (int) Math.ceil(nprime);
        String[] parts = new String[n];
        char[][] matrix = new char[n][length];
        int counter = 0;
        char c;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < length; j++) {
                if (counter < string.length()) {
                    matrix[i][j] = string.charAt(counter++);
                } else {
                    break;
                }

            }
            parts[i] = new String(matrix[i]);
            //   System.out.println(matrix[i]);

        }

        return parts;
    }
}
