/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpositioncipher2;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zahin
 */
public class TranspositionCipher {
    public static String[] strArr = new String[4];
    public static String encode(String string,int []key) {
        String []parts = Utility.divide2(string, key.length);
        String []jumbledParts = new String[parts.length];
        for(int i=0;i<parts.length;i++){
            jumbledParts[i] = Utility.rearrange(parts[i], key, key.length);
          //  System.out.println(parts[i]);
          //  System.out.println(jumbledParts[i]);
        }
        String cipheredText = "";
        for(int i=0;i<key.length;i++){
            for(int j=0;j<parts.length;j++){
                cipheredText += jumbledParts[j].charAt(i);
            }
        }
        return cipheredText;
                
        
        
    }

    public static String decode(String string) throws IOException {
          for(int i=2;i<string.length();i++){
            if (string.length() % i == 0) {
                int length = string.length() / i;
             //   System.out.println("Before transposition: #parts(row): " + i + " |Length of each part(col): " + length);
             //   System.out.println("After transposition: #parts(row): " + length + " |Length of each part(col): " + i);
                char[][] matrix = Utility.divide(string, i);
                //transposition
                char[][] transposedMatrix = new char[length][i];
                for (int r = 0; r < i; r++) {
                    for (int c = 0; c < length; c++) {
                        transposedMatrix[c][r] = matrix[r][c];
                    }
                    // System.out.println(matrix[r]);
                }
                String decodedText = "";

                for (int c = 0; c < length; c++) {
                    decodedText += new String(transposedMatrix[c]);
                }
               // System.out.println(decodedText);
               
//                strArr[0] = "congratulation".toUpperCase();
//                strArr[1] = "necessary".toUpperCase();
//                strArr[2] = "arrangement".toUpperCase();
//                strArr[3] = "withdraw".toUpperCase();
//
//                strArr[0] = "headquarter".toUpperCase();
//                strArr[1] = "checkpoint".toUpperCase();
//                strArr[2] = "attention".toUpperCase();
//                strArr[3] = "contact".toUpperCase();
               
                Utility.keys = new ArrayList<Integer[]>();
                for (int it2 = 0; it2 < strArr.length; it2++) {

                    List<String> parts = Utility.getSubsequenceOfFixedLength(strArr[it2], i);

                    for (int it1 = 0; it1 < parts.size(); it1++) {

                        //Utility.generatePermutations(parts.get(it1), 0, parts.get(it1).length() - 1);
                        int[] arr = new int[parts.get(it1).length()];
                        for (int var1 = 0; var1 < parts.get(it1).length(); var1++) {
                            arr[var1] = var1 + 1;
                        }
                        Utility util = new Utility(parts.get(it1), arr);
                        util.hybridperm(util, 0, arr.length - 1, decodedText);

                       
                        Utility.permutedStrings.clear();
                        Utility.count = 0;
                    }

                }
                for (int it4 = 0; it4 < Utility.keys.size(); it4++) {
                   // Utility.printArray(Utility.keys.get(it4), i);
                    String finalDecoded = "";
                    for (int it5 = 0; it5 < (decodedText.length() / i); it5++) {
                        String sub = decodedText.substring(it5 * i, it5 * i + i);
                        finalDecoded += Utility.rearrange(sub, Utility.keys.get(it4), i);
                       
                    }
                  //  System.out.println(finalDecoded);
                    boolean flag = true;
                    for(int it6=0;it6< strArr.length;it6++){
                        if(finalDecoded.contains(strArr[it6])==false) flag = false;
                    }
                    if(flag == true) {
                     //   System.out.println("Decipher key: ");
                     //   Utility.printArray(Utility.keys.get(it4), i);
                        int []cipherKey = new int[Utility.keys.get(it4).length];
                        for(int it7=0;it7< Utility.keys.get(it4).length;it7++){
                            int k = Utility.keys.get(it4)[it7]-1;
                            int a  = it7+1;
                            cipherKey[k] = a;
                        }
                        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
                       
                        System.out.println("Cipher key:");
                        Utility.printArray(cipherKey, cipherKey.length);
                        writer.write("Ordering: (");
                        for(int it8=0;it8<cipherKey.length;it8++) writer.write(Integer.toString(cipherKey[it8])+" ");
                         writer.write(")");
                          
                         String len = Integer.toString(cipherKey.length);
                         writer.write(" Length: "+len);
                        System.out.println("Deciphered Text: "+finalDecoded.toLowerCase());
                        writer.write(" Deciphered Text: "+finalDecoded.toLowerCase());
                        String ciphered = encode(finalDecoded, cipherKey);
                        System.out.println("Ciphered Text: " + ciphered);
                        writer.write(" Ciphered Text: "+ciphered);
                        if(encode(finalDecoded, cipherKey).equalsIgnoreCase(string)) {
                            System.out.println("Matches");
                            writer.write(" Accuracy: 100% ");
                        }
                        else {
                          int cnt=0;
                          int idx = -1;
                          String s = ciphered;
                          String s1 = string;
                         while(true){
                             if(s.charAt(cnt)!=s1.charAt(cnt)) {
                                 idx = cnt;
                                 break;
                             }
                             cnt++;
                         }
                           int acc = idx/string.length();
                           writer.write(" Accuracy: "+Integer.toString(acc)+"%");
                        }
                      
                        writer.close();;
                        return finalDecoded.toLowerCase();
                    }

                }

            }

        }
        return string;

    }
}
