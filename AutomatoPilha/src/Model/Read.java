/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author geovani
 */
public class Read {

    public static Automato Carrega(String arquivo) {
        ArrayList<String> texto = abrirArquivo(arquivo);

        String[] tokens = texto.get(0).split(" ");
        ArrayList<String> alfabeto = new ArrayList<>();
        alfabeto.addAll(Arrays.asList(tokens));

        tokens = texto.get(1).replaceFirst(" ", "").split(" ");
        ArrayList<String> alfabetoPilha = new ArrayList<>();
        alfabetoPilha.addAll(Arrays.asList(tokens));

        String epsilon = texto.get(2);

        String simboloInicialPilha = texto.get(3);

        tokens = texto.get(4).split(" ");
        ArrayList<Estado> estados = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {
            Estado e = new Estado(tokens[i]);
            estados.add(e);
        }

        String estadoInicial = texto.get(5);

        for (Estado e : estados) {
            if (e.getNome().equals(estadoInicial)) {
                e.setIsInicial(true);
            }
        }

        String[] estadosFinais = texto.get(6).split(" ");
        for (int i = 0; i < estadosFinais.length; i++) {
            for (Estado e : estados) {
                if (e.getNome().equals(estadosFinais[i])) {
                    e.setIsAceitacao(true);
                }
            }

        }
        ArrayList<Transicao> transicoes = new ArrayList<>();
        for (int i = 7; i < texto.size(); i++) {
            tokens = texto.get(i).split(" ");
            Transicao t = new Transicao(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
            transicoes.add(t);
        }

        Automato automato = new Automato(alfabeto, alfabetoPilha, epsilon, simboloInicialPilha, estados, transicoes);
        
        return automato;

    }

    public static ArrayList<String> abrirArquivo(String caminho) {
        ArrayList<String> texto = new ArrayList<>();
        File arq = new File(caminho);

        try {
            //OpenFile
            FileReader arquivo = new FileReader(caminho);
            BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);
            String linha = conteudoDoArquivo.readLine();
            while (linha != null) {
                texto.add(linha);
                linha = conteudoDoArquivo.readLine();
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        return texto;
    }

}
