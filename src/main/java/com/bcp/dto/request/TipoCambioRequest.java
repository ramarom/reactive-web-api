 package com.bcp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioRequest {
    private Double monto;
    private String monedaOrigen;
    private String monedaDestino;
    private String moneda;
    private Double tipoCambio;
}
