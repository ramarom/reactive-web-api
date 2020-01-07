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
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;
}