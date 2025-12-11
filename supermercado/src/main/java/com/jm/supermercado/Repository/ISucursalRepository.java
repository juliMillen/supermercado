package com.jm.supermercado.Repository;

import com.jm.supermercado.Entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal,Long> {
}
