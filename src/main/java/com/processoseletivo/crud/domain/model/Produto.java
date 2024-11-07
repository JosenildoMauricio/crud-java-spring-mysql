package com.processoseletivo.crud.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.processoseletivo.crud.domain.enums.UnidadeMedida;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "nome")
	private String nome;

	@NotNull
	@Column(name = "valor")
	private BigDecimal valor;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "unidade_medida")
	private UnidadeMedida unidadeMedida;

//	@JsonIgnoreProperties("produtos")
	@ManyToOne
	@JoinColumn(name="categoria_id", nullable = false)
	private Categoria categoria;
	
	@CreationTimestamp
	@Column(name = "data_cadastro")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(name = "data_atualizacao")
	private OffsetDateTime dataAtualizacao;
	
}
