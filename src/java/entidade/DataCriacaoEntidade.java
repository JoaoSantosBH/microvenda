/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author joaosantos
 */
@Entity
@Table(name = "data_criacao")
@NamedQueries({
    @NamedQuery(name = "DataCriacaoEntidade.findAll", query = "SELECT d FROM DataCriacaoEntidade d")})
public class DataCriacaoEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "data_criacao")
    private String dataCriacao;

    public DataCriacaoEntidade() {
    }

    public DataCriacaoEntidade(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public DataCriacaoEntidade(Integer idUsuario, String dataCriacao) {
        this.idUsuario = idUsuario;
        this.dataCriacao = dataCriacao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
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
        if (!(object instanceof DataCriacaoEntidade)) {
            return false;
        }
        DataCriacaoEntidade other = (DataCriacaoEntidade) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.DataCriacaoEntidade[ idUsuario=" + idUsuario + " ]";
    }
    
}
