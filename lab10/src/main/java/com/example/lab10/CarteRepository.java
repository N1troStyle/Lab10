package com.example.lab10;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lab10.Carte;

public interface CarteRepository extends JpaRepository<Carte, String> {
    List<Carte> findByAutor(String autor);
}