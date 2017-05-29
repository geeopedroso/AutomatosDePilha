/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author geovani
 */
public class Estado {
    private String nome;
    private boolean isInicial;
    private boolean isAceitacao;

    public Estado(String nome) {
        this.nome = nome;
        this.isInicial = false;
        this.isAceitacao = false;
    }

    public static Estado getEstad(ArrayList<Estado> estados, String nome){
        for(Estado e: estados){
            if(e.getNome().equals(nome)){
                return e;
            }
        }
        return null;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isIsInicial() {
        return isInicial;
    }

    public void setIsInicial(boolean isInicial) {
        this.isInicial = isInicial;
    }

    public boolean isIsAceitacao() {
        return isAceitacao;
    }

    public void setIsAceitacao(boolean isAceitacao) {
        this.isAceitacao = isAceitacao;
    }
    
    
    
}
