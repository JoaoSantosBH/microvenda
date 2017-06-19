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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author joaosantos
 */
@Entity
@Table(name = "linha")
@NamedQueries({
    @NamedQuery(name = "LinhaEntidade.findAll", query = "SELECT l FROM LinhaEntidade l")})
public class LinhaEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_linha")
    private Integer idLinha;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "nome")
    private String nome;

    public LinhaEntidade() {
    }

    public LinhaEntidade(Integer idLinha) {
        this.idLinha = idLinha;
    }

    public Integer getIdLinha() {
        return idLinha;
    }

    public void setIdLinha(Integer idLinha) {
        this.idLinha = idLinha;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLinha != null ? idLinha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinhaEntidade)) {
            return false;
        }
        LinhaEntidade other = (LinhaEntidade) object;
        if ((this.idLinha == null && other.idLinha != null) || (this.idLinha != null && !this.idLinha.equals(other.idLinha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.LinhaEntidade[ idLinha=" + idLinha + " ]";
    }
    
}
