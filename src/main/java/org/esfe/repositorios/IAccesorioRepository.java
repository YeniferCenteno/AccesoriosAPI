package org.esfe.repositorios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.esfe.modelos.Accesorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccesorioRepository extends JpaRepository<Accesorio, Integer> {
    @Query("SELECT a FROM Accesorio a WHERE :nombre IS NULL OR LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<Accesorio> findByNombreContaining(@Param("nombre") String nombre, Pageable pageable);
}
