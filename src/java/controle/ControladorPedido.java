/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.ItemEntidade;
import entidade.PedidoEntidade;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import persistencia.ItemDAO;
import persistencia.PedidoDAO;

/**
 *
 * @author joaosantos
 */
public class ControladorPedido {

    public static void excluirPedido(Long numPedido) throws SQLException {
        PedidoDAO.excluirPedido(numPedido);
        ItemDAO.excluirItensPedido(numPedido);

    }

    public static void alterarStatusDoPedido(int numPedido) throws SQLException {

    }

    public static List<PedidoEntidade> listarPedidosDoVendedor(int idUser) throws SQLException {
        List<PedidoEntidade> ls = null;

        ls = PedidoDAO.ListarPedidos(idUser);

        return ls;
    }

    public static List<PedidoEntidade> listarPedidosInativosDoVendedor(int idUser) throws SQLException {
        List<PedidoEntidade> ls = null;

        ls = PedidoDAO.ListarPedidosInativos(idUser);

        return ls;
    }

    public static List<PedidoEntidade> listarPedidosAtivosDoVendedor(int idUser) throws SQLException {
        List<PedidoEntidade> ls = null;

        ls = PedidoDAO.ListarPedidosAtivosVendedor(idUser);

        return ls;
    }

    public static List<ItemEntidade> listarItensDoPedidoPeloNumPed(Long numPed) throws SQLException {
        List<ItemEntidade> lst = null;
        lst = ItemDAO.ListarProdutosCadastrados(numPed);

        return lst;
    }

    public static PedidoEntidade pegarPedidoPeloId(int idPedido) throws SQLException {
        PedidoEntidade p = null;

        p = PedidoDAO.pegarPedidoPeloId(idPedido);

        return p;
    }

    public static void darBaixaPedido(Long numeroPedido) throws SQLException {

        PedidoDAO.darBaixaPedido(numeroPedido);
    }

    public static List<PedidoEntidade> pegarTodosOsPedidosDoCliente(int idUs, int idCl) throws SQLException {
        List<PedidoEntidade> lst = PedidoDAO.pegarTodosOsPedidosDoCliente(idUs, idCl);
        return lst;
    }

    public static List<PedidoEntidade> pegarTodosOsPedidosDoUsuarioDoDiaCorrente(int idU) throws SQLException{
        Calendar c = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("YYYY-MM-dd");
        String dataAgora = s.format(c.getTime());
        List<PedidoEntidade> lst = PedidoDAO.pegarTodosOsPedidosDoUsuarioDoDiaCorrente(idU, dataAgora);
        return lst;
    }
    
    public static List<PedidoEntidade> pegarTodosOsPedidosDoUsuarioDaSemanaCorrente(int idU) throws SQLException{
        Calendar c = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");       
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String fim = "";    
        String ini = "";
        
        switch (dayOfWeek) {
            case 1:
                c.add(Calendar.DATE, -1);//anda 1 DIA pra traz e seta semana anterior
                fim = s.format(c.getTime());
                c.add(Calendar.DATE, -5); // Volta ate na seguda feira corrente
                ini = s.format(c.getTime());
                System.out.println(ini);
                System.out.println(fim);
                break;
            case 2:
                c.add(Calendar.DATE, 5);//anda 5 DIAS pra frente e seta semana corrente
                fim = s.format(c.getTime());
                c.add(Calendar.DATE, -5);// Volta ate na seguda feira corrente
                ini = s.format(c.getTime());
                System.out.println(ini);
                System.out.println(fim);
                break;
            case 3:
                c.add(Calendar.DATE, 4);
                fim = s.format(c.getTime());
                c.add(Calendar.DATE, -5);
                ini = s.format(c.getTime());
                System.out.println(ini);
                System.out.println(fim);
                break;
            case 4:
                c.add(Calendar.DATE, 3);
                fim = s.format(c.getTime());
                c.add(Calendar.DATE, -5);
                ini = s.format(c.getTime());
                System.out.println(ini);
                System.out.println(fim);
                break;
            case 5:
                c.add(Calendar.DATE, 2);
                fim = s.format(c.getTime());
                c.add(Calendar.DATE, -5);
                ini = s.format(c.getTime());
                System.out.println(ini);
                System.out.println(fim);
                break;
            case 6:
                c.add(Calendar.DATE, 1);
                fim = s.format(c.getTime());
                c.add(Calendar.DATE, -5);
                ini = s.format(c.getTime());
                System.out.println(ini);
                System.out.println(fim);
                break;

            case 7:
                fim = s.format(c.getTime());
                c.add(Calendar.DATE, -5);
                ini = s.format(c.getTime());
                System.out.println(ini);
                System.out.println(fim);
                break;
        }
        List<PedidoEntidade> ls = PedidoDAO.pegarTodosOsPedidosDoUsuarioPorPeriodoDeterminado(idU, ini, fim);
        return ls;
    }
    
    public static List<PedidoEntidade> pegarTodosOsoPedidosDoUsuarioDoMesCOrrente(int idU) throws SQLException{
        
        Calendar dataHoje = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String agora = s.format(dataHoje.getTime());

        String[] dataVetorSplit = agora.split("-");
        String stringAno = dataVetorSplit[0];
        String stringMes = dataVetorSplit[1];
        String stringDia = dataVetorSplit[2];

        String dataIni = stringAno + "-" + stringMes + "-01";
        String dataFim = "";
        
        if (stringMes.equals("02")) { // FEV TEM 28 DIAS
            int ano =Integer.parseInt(stringAno);
            if (ano % 400 == 0) {
                System.out.println(ano + " é bissexto.");
                dataFim = stringAno + "-" + stringMes + "-29";
                // se o ano for menor que 400
            } else if ((ano % 4 == 0) && (ano % 100 != 0)) {
                System.out.println(ano + " é bissexto.");
                dataFim = stringAno + "-" + stringMes + "-29";
            } else {
                System.out.println(ano + " não é bissexto");
                dataFim = stringAno + "-" + stringMes + "-28";

            }

            
        } else if (stringMes.equals("04") || stringMes.equals("06") || stringMes.equals("09") | stringMes.equals("11")) { // ABR JUN SET NOV 30 DIAS
            dataFim = stringAno + "-" + stringMes + "-30";
        } else { // JAN MAR MAI JUL AGO OUT DEZ   31 DIAS
            dataFim = stringAno + "-" + stringMes + "-31";
        }
        List<PedidoEntidade> lst = PedidoDAO.pegarTodosOsPedidosDoUsuarioPorPeriodoDeterminado(idU, dataIni, dataFim);
        
        return lst;
    }

    public static List<PedidoEntidade> listarPedidosAtivosDoCliente(int id) throws SQLException{
        List<PedidoEntidade> lst = PedidoDAO.listarPedidosAtivosDoCliente(id);
        return lst;
    }
}
