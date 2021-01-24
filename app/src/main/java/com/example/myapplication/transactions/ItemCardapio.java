package com.example.myapplication.transactions;

public class ItemCardapio {
    private static int contadorId = 0;

    private int id;
    private String nome;
    private float preco;

    public ItemCardapio() {
    }

    public ItemCardapio(String nome, float preco) {
        this.id = contadorId++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return  nome + "\t\t\t\t\t\t" + preco ;
    }
}
