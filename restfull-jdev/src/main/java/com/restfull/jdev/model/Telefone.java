package com.restfull.jdev.model;

import java.io.Serializable;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_telefone", sequenceName = "seq_telefone", allocationSize = 1, initialValue = 1)
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_telefone")
	private Long id;
	private String numero;

	@JsonIgnore
	@ForeignKey(name = "usuario_id")
	@ManyToOne(optional = false)
	private Usuario usuario;

	public Telefone() {
	}

	public Telefone(Long id, String numero, Usuario usuario) {
		super();
		this.id = id;
		this.numero = numero;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", numero=" + numero + ", usuario=" + usuario + "]";
	}

}
