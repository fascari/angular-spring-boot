package io.github.fascari.agenda.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contatos", schema = "agenda_owner")
public class Contatos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 150, nullable = false)
    private String nome;
    @Column(length = 150, nullable = false)
    private String email;
    @Column
    private Boolean favorito;
    @Column
    @Lob
    private byte[] foto;
}
