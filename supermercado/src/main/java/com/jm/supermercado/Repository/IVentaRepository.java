package com.jm.supermercado.Repository;

import com.jm.supermercado.Entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {
}
