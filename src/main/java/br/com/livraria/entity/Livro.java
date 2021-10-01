package br.com.livraria.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String isbn;
    private Double price;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataLancamento = Calendar.getInstance();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Autor> autors = new ArrayList<>();

    public Livro() {
    }

    public Livro(Integer id, String name, String isbn, Double price) {
        this.id = id;
        this.titulo = name;
        this.isbn = isbn;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String name) {
        this.titulo = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Calendar getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Calendar dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public  void addAutor(Autor autor){
        this.autors.add(autor);
    }
    public void addAllAutor(Autor... autors){
        for (Autor autor: autors){
            if(!this.autors.contains(autor)){
                this.addAutor(autor);
            }
        }
    }

    public void removeAutor(Autor autor){
        this.autors.remove(autor);
    }
}
