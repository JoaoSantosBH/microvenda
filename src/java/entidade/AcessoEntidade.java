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
@Table(name = "acesso")
@NamedQueries({
    @NamedQuery(name = "AcessoEntidade.findAll", query = "SELECT a FROM AcessoEntidade a")})
public class AcessoEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acesso")
    private Integer idAcesso;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "acesso_atual")
    private String acessoAtual;
    @Column(name = "ultimo_acesso")
    private String ultimoAcesso;
    @Column(name = "ip_acesso")
    private String ipAcesso;

    public AcessoEntidade() {
    }

    public AcessoEntidade(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public Integer getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAcessoAtual() {
        return acessoAtual;
    }

    public void setAcessoAtual(String acessoAtual) {
        this.acessoAtual = acessoAtual;
    }

    public String getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(String ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getIpAcesso() {
        return ipAcesso;
    }

    public void setIpAcesso(String ipAcesso) {
        this.ipAcesso = ipAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcesso != null ? idAcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcessoEntidade)) {
            return false;
        }
        AcessoEntidade other = (AcessoEntidade) object;
        if ((this.idAcesso == null && other.idAcesso != null) || (this.idAcesso != null && !this.idAcesso.equals(other.idAcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.AcessoEntidade[ idAcesso=" + idAcesso + " ]";
    }
    
}
