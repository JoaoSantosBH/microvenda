/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author joaosantos
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "UsuarioEntidade.findAll", query = "SELECT u FROM UsuarioEntidade u")})
public class UsuarioEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "nome")
    private String nome;
    @Column(name = "id_telefone")
    private Integer idTelefone;
    @Column(name = "id_endereco")
    private Integer idEndereco;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "tipo")
    private Short tipo;
    @Column(name = "valido")
    private Short valido;
    @Column(name = "status_pagamento")
    private Short statusPagamento;
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @Column(name = "ultimo_acesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;

    public UsuarioEntidade() {
    }

    public UsuarioEntidade(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioEntidade(Integer idUsuario, Date ultimoAcesso) {
        this.idUsuario = idUsuario;
        this.ultimoAcesso = ultimoAcesso;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public Short getValido() {
        return valido;
    }

    public void setValido(Short valido) {
        this.valido = valido;
    }

    public Short getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(Short statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEntidade)) {
            return false;
        }
        UsuarioEntidade other = (UsuarioEntidade) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.UsuarioEntidade[ idUsuario=" + idUsuario + " ]";
    }
    
}
