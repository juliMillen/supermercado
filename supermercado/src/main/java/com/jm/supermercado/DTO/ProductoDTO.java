package com.jm.supermercado.DTO;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {

    private Long idProducto;

    private String nombre;

    private String categoria;

    private double precioUnitario;

    private int cantidad;
}
