package com.jm.supermercado.DTO;


import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long idDetalleVenta;

    private String nombreProd;

    private int cantProd;

    private double precio;

    private double subtotal;
}
