package com.jm.supermercado.DTO;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SucursalDTO {

    private Long idSucursal;

    private String nombre;

    private String direccion;
}
