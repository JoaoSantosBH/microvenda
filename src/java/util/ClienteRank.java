/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author joaosantos
 */
public class ClienteRank implements Comparable<ClienteRank> {

    public int idCliente;
    public String NomeCLiente;
    public int total;

    public String getNomeCLiente() {
        return NomeCLiente;
    }

    public void setNomeCLiente(String NomeCLiente) {
        this.NomeCLiente = NomeCLiente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int compareTo(ClienteRank o) {

        if (this.total < o.total) {
            return -1;
        }
        if (this.total > o.total) {
            return 1;
        }
        return 0;
    }

}
