package com.jm.supermercado.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {


    private Long idVenta;

    private String estado;

    private LocalDate fecha;

    //datos de la sucursal
    private Long idSucursal;

    //lista de detalles
    private List<DetalleVentaDTO> detalles;

    //total de la venta
    private double total;
}
