/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author geovani
 */
public class Main {
    public static void main(String[] args) {
         
        Automato automato = Read.Carrega("automato1.txt");
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("> Digite a palavra a ser verificada: ");
        String str = scanner.nextLine();
        boolean b =  automato.VerificaPalavra(str, automato);
        if(b){
            System.out.println("\n "+ b+"\n  PALAVRA ACEITA");
        }else {
            System.out.println("\n "+ b+"\n  PALAVRA   REJEITADA");
        }
        
        
        
        //automato.Imprime(automato);
        
    }
    
}
