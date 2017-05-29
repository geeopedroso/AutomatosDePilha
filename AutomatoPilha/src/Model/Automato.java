/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author geovani
 */
public class Automato {

    private ArrayList<String> alfabetoEntrada;
    private ArrayList<String> alfabetoPilha;
    private String simboloEpsilon;
    private String simboloInicialPilha;
    private ArrayList<Estado> conjuntoEstados;
    private Stack pilha;
    private Estado estadoAtual;
    private ArrayList<Transicao> transicoes;
    private ArrayList<Estado> EstadosAtua;

    public Automato(ArrayList<String> alfabetoEntrada, ArrayList<String> alfabetoPilha, String simboloEpsilon, String simboloInicialPilha, ArrayList<Estado> conjuntoEstados, ArrayList<Transicao> transicoes) {
        this.alfabetoEntrada = alfabetoEntrada;
        this.alfabetoPilha = alfabetoPilha;
        this.simboloEpsilon = simboloEpsilon;
        this.simboloInicialPilha = simboloInicialPilha;
        this.conjuntoEstados = conjuntoEstados;
        this.pilha = new Stack();
        this.pilha.push(simboloInicialPilha);
        this.transicoes = transicoes;
        this.estadoAtual = atual(conjuntoEstados);
        this.EstadosAtua = new ArrayList<>();
        this.EstadosAtua.add(this.estadoAtual);
    }

    public boolean VerificaPalavra(String p, Automato automato) {
        boolean b;
        ArrayList<String> palavra = new ArrayList<>();
        palavra.addAll(Arrays.asList(p.split("")));
        // verifica se a palavra esta no alfabeto
        for (String s : palavra) {
            if (!EstaNoAlfabeto(automato.getAlfabetoEntrada(), s)) {
                return false;
            }
        }
        ArrayList<Transicao> transicoesPossiveis = Transicao.getTrans(automato.getTransicoes(), automato.getEstadoAtual());
        while (!transicoesPossiveis.isEmpty()) {
            b = false;
            for (Transicao t : transicoesPossiveis) {

                if (t.getSimboloEntrada().equals(automato.simboloEpsilon)) {
                    if (t.getTopoDaPilha().equals(automato.getPilha().peek())) {
                        automato.getPilha().pop();
                        if (!t.getSimboloEmpilha().equals(automato.simboloEpsilon)) {
                            Empilha(t, automato);
                        }
                         automato.setEstadoAtual(Estado.getEstad(automato.getConjuntoEstados(), t.getNovoEstado().getNome()));
//                        if (!palavra.isEmpty()) {
//                            palavra.remove(0);
//                        }
                        
                        b = true;
                    }
                } else if (!palavra.isEmpty()&& !automato.getPilha().empty()) {
                    if (t.getSimboloEntrada().equals(palavra.get(0)) && t.getTopoDaPilha().equals(automato.getPilha().peek())) {
                        automato.setEstadoAtual(Estado.getEstad(automato.getConjuntoEstados(), t.getNovoEstado().getNome()));
                        automato.getPilha().pop();
                        if (!t.getSimboloEmpilha().equals(automato.simboloEpsilon)) {
                            Empilha(t, automato);
                        }
                        palavra.remove(0);
                       
                        b =  true;
                    }
                    
                } 

            }
            
            if(b){
                transicoesPossiveis = Transicao.getTrans(automato.getTransicoes(), automato.getEstadoAtual());
                
            }else {
                break;
            }

            
        }
        System.out.println("estado atual: " + automato.getEstadoAtual().getNome()+" aceitacao: " + automato.getEstadoAtual().isIsAceitacao());
        System.out.println("tamanho palavra: "+ palavra.size());
        System.out.println("topo pilha: "+ automato.getPilha());
        if (palavra.isEmpty()) {
            if (automato.getPilha().empty()) {
                return true;
            } else if (automato.getEstadoAtual().isIsAceitacao()) {
                return true;
            }

        }

        return false;
    }

    public void Empilha(Transicao t, Automato automato) {
        String[] s = t.getSimboloEmpilha().split("");
        for (int i = s.length - 1; i >= 0; i--) {
            automato.getPilha().push(s[i]);

        }
    }

    public boolean EstaNoAlfabeto(ArrayList<String> alfabeto, String letra) {
        for (String e : alfabeto) {
            if (e.equals(letra)) {
                return true;
            }
        }
        return false;
    }

    public Estado atual(ArrayList<Estado> estados) {
        Estado e = null;
        for (Estado estado : estados) {
            if (estado.isIsInicial()) {
                e = estado;
                return e;
            }
        }

        return e;
    }

    public static void Imprime(Automato automato) {
        System.out.println("\n Alfabeto entrada: ");
        for (String e : automato.getAlfabetoEntrada()) {
            System.out.print(" " + e + ",");
        }

        System.out.println("\n Alfabeto Pilha: ");
        for (String e : automato.getAlfabetoPilha()) {
            System.out.print(" " + e + ",");
        }
        System.out.println("\n Epsilon: " + automato.getSimboloEpsilon());

        System.out.println(" \n Estados :");
        for (Estado e : automato.getConjuntoEstados()) {
            System.out.println(" " + e.getNome() + "  inicial: " + e.isIsInicial() + "  aceitacao: " + e.isIsAceitacao());
        }

        System.out.println("\n Estado atual: " + automato.getEstadoAtual().getNome());

        System.out.println("\n Topo da pilha: " + automato.getPilha().peek());

        Transicao.ImprimeTransicao(automato.getTransicoes());

    }

    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(Estado estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public ArrayList<Transicao> getTransicoes() {
        return transicoes;
    }

    public ArrayList<Estado> getEstadosAtua() {
        return EstadosAtua;
    }

    public void setEstadosAtua(ArrayList<Estado> EstadosAtua) {
        this.EstadosAtua = EstadosAtua;
    }

    public void setTransicoes(ArrayList<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    public ArrayList<String> getAlfabetoEntrada() {
        return alfabetoEntrada;
    }

    public void setAlfabetoEntrada(ArrayList<String> alfabetoEntrada) {
        this.alfabetoEntrada = alfabetoEntrada;
    }

    public ArrayList<String> getAlfabetoPilha() {
        return alfabetoPilha;
    }

    public void setAlfabetoPilha(ArrayList<String> alfabetoPilha) {
        this.alfabetoPilha = alfabetoPilha;
    }

    public String getSimboloEpsilon() {
        return simboloEpsilon;
    }

    public void setSimboloEpsilon(String simboloEpsilon) {
        this.simboloEpsilon = simboloEpsilon;
    }

    public String getSimboloInicialPilha() {
        return simboloInicialPilha;
    }

    public void setSimboloInicialPilha(String simboloInicialPilha) {
        this.simboloInicialPilha = simboloInicialPilha;
    }

    public ArrayList<Estado> getConjuntoEstados() {
        return conjuntoEstados;
    }

    public void setConjuntoEstados(ArrayList<Estado> conjuntoEstados) {
        this.conjuntoEstados = conjuntoEstados;
    }

    public Stack getPilha() {
        return pilha;
    }

    public void setPilha(Stack pilha) {
        this.pilha = pilha;
    }

}
