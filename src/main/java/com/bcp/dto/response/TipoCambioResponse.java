package com.bcp.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoCambioResponse {
    private Double monto;
    private Double montoTipoCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
}
