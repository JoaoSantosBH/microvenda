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
@Table(name = "investimento")
@NamedQueries({
    @NamedQuery(name = "InvestimentoEntidade.findAll", query = "SELECT i FROM InvestimentoEntidade i")})
public class InvestimentoEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_investimento")
    private Integer idInvestimento;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_capital")
    private Float valorCapital;
    @Column(name = "valor_inventario")
    private Float valorInventario;

    public InvestimentoEntidade() {
    }

    public InvestimentoEntidade(Integer idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public Integer getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(Integer idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Float getValorCapital() {
        return valorCapital;
    }

    public void setValorCapital(Float valorCapital) {
        this.valorCapital = valorCapital;
    }

    public Float getValorInventario() {
        return valorInventario;
    }

    public void setValorInventario(Float valorInventario) {
        this.valorInventario = valorInventario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInvestimento != null ? idInvestimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvestimentoEntidade)) {
            return false;
        }
        InvestimentoEntidade other = (InvestimentoEntidade) object;
        if ((this.idInvestimento == null && other.idInvestimento != null) || (this.idInvestimento != null && !this.idInvestimento.equals(other.idInvestimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.InvestimentoEntidade[ idInvestimento=" + idInvestimento + " ]";
    }
    
}
