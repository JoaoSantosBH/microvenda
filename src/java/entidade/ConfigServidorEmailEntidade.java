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
@Table(name = "config_servidor_email")
@NamedQueries({
    @NamedQuery(name = "ConfigServidorEmailEntidade.findAll", query = "SELECT c FROM ConfigServidorEmailEntidade c")})
public class ConfigServidorEmailEntidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_config")
    private Integer idConfig;
    @Column(name = "host")
    private String host;
    @Column(name = "port")
    private String port;
    @Column(name = "auth")
    private String auth;
    @Column(name = "starttls")
    private String starttls;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "from_name_email")
    private String fromNameEmail;
    @Column(name = "password")
    private String password;
    @Column(name = "charset")
    private String charset;
    @Column(name = "subject")
    private String subject;

    public ConfigServidorEmailEntidade() {
    }

    public ConfigServidorEmailEntidade(Integer idConfig) {
        this.idConfig = idConfig;
    }

    public Integer getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Integer idConfig) {
        this.idConfig = idConfig;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getStarttls() {
        return starttls;
    }

    public void setStarttls(String starttls) {
        this.starttls = starttls;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFromNameEmail() {
        return fromNameEmail;
    }

    public void setFromNameEmail(String fromNameEmail) {
        this.fromNameEmail = fromNameEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfig != null ? idConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigServidorEmailEntidade)) {
            return false;
        }
        ConfigServidorEmailEntidade other = (ConfigServidorEmailEntidade) object;
        if ((this.idConfig == null && other.idConfig != null) || (this.idConfig != null && !this.idConfig.equals(other.idConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ConfigServidorEmailEntidade[ idConfig=" + idConfig + " ]";
    }
    
}
