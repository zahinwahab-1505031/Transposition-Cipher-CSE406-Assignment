/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transpositioncipher2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Zahin
 */
public class Main {

   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("transposition-31.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String tobedeciphered = "";
        String keyWords = "";
        int count = 0;
        while((st=br.readLine())!=null){
            //System.out.println(st);
            if(count==0) tobedeciphered = st;
            else if(count==2) {
                keyWords = st;
            }
            count++;
        }
        System.out.println(tobedeciphered);
        System.out.println(keyWords);
        
        String[] arrOfStr = keyWords.split(","); 
        count = 0;
        for (String a : arrOfStr) 
        {
            TranspositionCipher.strArr[count++] = a.trim().toUpperCase();
            
        }
      TranspositionCipher.decode(tobedeciphered);
      
    }

}
