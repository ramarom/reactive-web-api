package com.bcp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tipo_cambio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambio {
    @Id
    @Column(name = "moneda")
    private String moneda;

    @Column(name = "conversion")
    private Double conversion;
}