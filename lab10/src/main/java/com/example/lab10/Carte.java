package com.example.lab10;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carti")
public class Carte {
    @Id
    private String isbn;
    private String titlu;
    private String autor;

    public Carte() {}

    public Carte(String isbn, String titlu, String autor) {
        this.isbn = isbn;
        this.titlu = titlu;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}