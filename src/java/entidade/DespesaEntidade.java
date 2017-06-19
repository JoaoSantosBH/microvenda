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
@Table(name = "despesa")
@NamedQueries({
    @NamedQuery(name = "DespesaEntidade.findAll", query = "SELECT d FROM DespesaEntidade d")})
public class DespesaEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_despesa")
    private Integer idDespesa;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data")
    private String data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;

    public DespesaEntidade() {
    }

    public DespesaEntidade(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Integer getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDespesa != null ? idDespesa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DespesaEntidade)) {
            return false;
        }
        DespesaEntidade other = (DespesaEntidade) object;
        if ((this.idDespesa == null && other.idDespesa != null) || (this.idDespesa != null && !this.idDespesa.equals(other.idDespesa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.DespesaEntidade[ idDespesa=" + idDespesa + " ]";
    }
    
}
