package com.jm.supermercado.Repository;

import com.jm.supermercado.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    //Buscar producto por nombre

    Optional<Producto> findByNombre(String nombre);
}
