package com.restfull.jdev.model;

import java.io.Serializable;

import org.hibernate.annotations.ForeignKey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String numero;

	@ForeignKey(name = "usuario_id")
	@ManyToOne()
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
