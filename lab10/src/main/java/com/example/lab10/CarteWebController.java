package com.example.lab10;

import com.example.lab10.Carte;
import com.example.lab10.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarteWebController {
    @Autowired
    CarteRepository repo;

    @GetMapping("/lista-carti")
    public String afiseazaCarti(Model model) {
        model.addAttribute("carti", repo.findAll());
        model.addAttribute("mesaj", "Lista cartilor preluate prin repository.");
        return "carti";
    }

    @PostMapping("/operatii")
    public String operatii(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String titlu,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String adauga,
            @RequestParam(required = false) String modifica,
            @RequestParam(required = false) String sterge,
            @RequestParam(required = false) String filtreaza,
            Model model
    ) {
        String mesaj = "";
        model.addAttribute("carti", repo.findAll());
        if (adauga != null) {
            if (!isbn.isEmpty() && !titlu.isEmpty() && !autor.isEmpty()) {
                repo.save(new Carte(isbn, titlu, autor));
                mesaj = "Adaugare realizata cu succes!";
                model.addAttribute("carti", repo.findAll());
            } else {
                mesaj = "Completeaza campurile!";
            }
        } else if (modifica != null) {
            Optional<Carte> carte = repo.findById(isbn);
            if (carte.isPresent()) {
                Carte c = carte.get();
                c.setTitlu(titlu);
                c.setAutor(autor);
                repo.save(c);
                mesaj = "Cartea cu ISBN-ul " + isbn + " a fost modificata!";
                model.addAttribute("carti", repo.findAll());
            } else {
                mesaj = "Nu se gaseste nici o carte";
            }
        } else if (sterge != null) {
            if (repo.existsById(isbn)) {
                repo.deleteById(isbn);
                mesaj = "Cartea " + isbn + " a fost stearsa!";
                model.addAttribute("carti", repo.findAll());
            } else {
                mesaj = "Nu exista carte cu ISBN-ul introdus.";
            }
        } else if (filtreaza != null) {

            if (autor == null || autor.isEmpty()) {
                model.addAttribute("carti", repo.findAll());
                mesaj = "Toate cărțile sunt afișate.";
            } else {
                List<Carte> filtrate = repo.findByAutor(autor); //din Repository
                model.addAttribute("carti", filtrate);
                mesaj = "Cărţile următoare aparţin autorului " + autor + ".";
            }
        }
        model.addAttribute("mesaj", mesaj);
        return "carti";
    }
}
