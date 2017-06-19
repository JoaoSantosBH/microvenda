/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.ItemEntidade;
import entidade.VendaEntidade;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import persistencia.AreceberDAO;
import persistencia.EstoqueDAO;
import persistencia.ItemDAO;
import persistencia.VendaDAO;
import util.EnviarEmail;

/**
 *
 * @author joaosantos
 */
public class ControladorVenda {

    public static int criarNovaVenda(VendaEntidade venda) throws SQLException {

        int id = VendaDAO.criarNovaVenda(venda);
        return id;
    }

    public static VendaEntidade pegarVendaPeloId(int idVenda) throws SQLException {

        VendaEntidade v = VendaDAO.pegarVendaPeloId(idVenda);
        return v;

    }

    public static List<VendaEntidade> pegarVendasDoUsuario(int idUser) throws SQLException {

        List<VendaEntidade> ls = VendaDAO.pegarTodasAsVendasDoUsuario(idUser);
        return ls;
    }

//   n o v a s  bv
    public static List<VendaEntidade> pegarVendasDoUsuarioPorPeriodoPreDefinido(int idUser, String dataIni, String dataFim) throws SQLException {

        List<VendaEntidade> lst = VendaDAO.pegarVendasDoUsuarioProPeriodoPreDefinido(idUser, dataIni, dataFim);

        return lst;

    }

    public static void extornarVendaDoDia(int idVenda) throws SQLException {
        
        //E N V I A R   E M A I L   N O T I F I C A N D O   C L I E N T E   E   V E N D E D O 
        EnviarEmail env = new EnviarEmail();

        VendaEntidade v = ControladorVenda.pegarVendaPeloId(Integer.valueOf(idVenda));
        env.enviarEmailConfirmacaoDeVenda(v);
        //A P A G A R   I T E N S   D A   V E N D A
        List<ItemEntidade> lstItem = ControladorPedido.listarItensDoPedidoPeloNumPed(v.getNumPedido());

        ControladorVenda.apagarItensDaVendaCancelada(v.getNumPedido());

        //I N C R E M E N T A R   I T E M   E S T O Q U E 
        for (ItemEntidade i : lstItem) {
            int qtdAtual = EstoqueDAO.pegarQuantidadeDoItemNoEstoqueParaAtualizar(i.getIdProduto(), i.getQtde());
            ControladorEstoque.incrementarItemEstoquePorCancelamentoVenda(i, qtdAtual);
        }

        //A P A G A R   P A R C E L A S   A R E C E B E R
        AreceberDAO.apagarParcelasAreceberVendaCancelada(v.getIdVenda());

        //A P A G A R   V E N D A 
        ControladorVenda.apagarVenda(v.getIdVenda());

        //E X C L U I R   P E D I D O 
        ControladorPedido.excluirPedido(v.getNumPedido());
        
    }

    public static List<VendaEntidade> pegarVendasDoUsuarioDoDiaAtual(int idUser) throws SQLException {
        Calendar dataHoje = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String agora = s.format(dataHoje.getTime());
        List<VendaEntidade> ls = VendaDAO.pegarVendasDoUsuarioProPeriodoPreDefinido(idUser, agora, agora);
        return ls;
    }

    public static List<VendaEntidade> pegarVendasDoUsuarioDoMesAtual(int idUser) throws SQLException {

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
            int ano = Integer.parseInt(stringAno);
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

        List<VendaEntidade> ls = VendaDAO.pegarVendasDoUsuarioProPeriodoPreDefinido(idUser, dataIni, dataFim);

        return ls;
    }

    public static List<VendaEntidade> pegarVendasDoUsuarioDaSemenaCorrente(int idUser) throws SQLException {
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

        List<VendaEntidade> ls = VendaDAO.pegarVendasDoUsuarioProPeriodoPreDefinido(idUser, ini, fim);
        return ls;
    }

    public static List<VendaEntidade> pegarTodasAsVendasPorClienteDoVendedor(int idU, int idCli) throws SQLException {
        List<VendaEntidade> lst = VendaDAO.pegarTodasAsVendasPorClienteDoVendedor(idU, idCli);
        return lst;
    }

    //   n o v a s 
//    public static Boolean verificarEstoqueProdutos(int idProd) throws SQLException{
//        Boolean temEstoque = false;
//        
////        temEstoque = ControladorEstoque.verificarQuantidadeEmEstoque(idProd);
//        
//        return temEstoque;
//    }
    public static Boolean verificarSeJaExisteVendaComNumPedidoAtual(String numPedido) throws SQLException {

        Boolean existe = VendaDAO.verificarSeJaExisteVendaComNumPedidoAtual(numPedido);
        return existe;
    }

    public static VendaEntidade pegarVendaPeloNumPedido(Long numPedido, int idU) throws SQLException {
        VendaEntidade v = VendaDAO.pegarVendaPeloNumPedido(numPedido, idU);
        return v;

    }

    public static List<VendaEntidade> pegarListaDeVendasQueAtendemQueryLike(String idUser, String query) throws SQLException {
        List<VendaEntidade> lst = VendaDAO.pegarListaDeVendasQueAtendemQueryLike(idUser, query);
        return lst;

    }

    public static List<VendaEntidade> pegarComprasDoCLiente(String idVendedor, int idCliente) throws SQLException {
        List<VendaEntidade> lst = VendaDAO.pegarListaDeComprasDoCLiente(idVendedor, idCliente);
        return lst;
    }

    public static void apagarItensDaVendaCancelada(Long numPedido) throws SQLException {
        ItemDAO.excluirItensDaVendaCancelada(numPedido);
    }

    public static void apagarVenda(Integer idVenda) throws SQLException {
        VendaDAO.apagarVenda(idVenda);
    }
}
