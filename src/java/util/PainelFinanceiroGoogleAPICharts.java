/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import controle.ControladorUsuario;
import entidade.AreceberEntidade;
import entidade.EstoqueEntidade;
import entidade.ProdutoEntidade;
import entidade.VendaEntidade;
import java.sql.SQLException;
import java.util.List;
import persistencia.AreceberDAO;
import persistencia.DespesaDAO;
import persistencia.EstoqueDAO;
import persistencia.ProdutoDAO;
import persistencia.VendaDAO;

/**
 *
 * @author joaosantos
 */
public class PainelFinanceiroGoogleAPICharts {

    public static String pegarDadosFinanceiros(int idUsuario) throws SQLException {
        String result = "";

        Float totalInvestido = 0f;
        Float totalVendido = 0f;
        Float lucroReal = 0f;
        Float totalAReceber = 0f;
        Float despesaTotal = DespesaDAO.pegarSomaDepsesaUsuario(idUsuario);

        List<ProdutoEntidade> lstProdutos = ProdutoDAO.ListarProdutosCadastrados(idUsuario);
        for (ProdutoEntidade p : lstProdutos) {
            boolean estaEmEstoque = EstoqueDAO.esteProdutoEstaEmEstoque(p.getIdProduto());
            if (estaEmEstoque) {
                EstoqueEntidade e = new EstoqueEntidade();
                e.setIdProduto(p.getIdProduto());
                e.setIdUsuario(p.getIdUsuario());
                int qtd = EstoqueDAO.pegarQuantidadeDoItemNoEstoque(e);
                Float quantidade = Float.valueOf(qtd);
                Float calculo = quantidade * p.getPrecoCompra();
                totalInvestido += calculo;
            }

        }
        totalInvestido += despesaTotal;
        List<VendaEntidade> lstVendas = VendaDAO.pegarTodasAsVendasDoUsuario(idUsuario);
        for (VendaEntidade v : lstVendas) {

            totalVendido += v.getValor();

        }

        lucroReal = totalVendido - totalInvestido;

        List<AreceberEntidade> lstReceber = AreceberDAO.pegarListaDeParcelasAReceber(idUsuario);
        for (AreceberEntidade ar : lstReceber) {
            totalAReceber += ar.getValor();
        }

        result = "[\n";
        result += "['Task', 'Hours per Day'],\n";
        result += "[ 'Gastos', " + totalInvestido + "],\n";
        result += "[ 'Total Vendas', " + totalVendido + "],\n";
        result += "[ 'A Receber', " + totalAReceber + "],\n";
        if (lucroReal < 0) {
            result += "[ ' Lucro em Vendas', " + 0 + "]\n";
        } else {
            result += "[ ' Lucro em Vendas', " + lucroReal + "]\n";
        }

        result += "]";
        return result;
    }

    public static String pegarPerformanceDeVendas(int idUser) throws SQLException {
        String result = "";
        int totalPedidos = ControladorUsuario.pegarTodosOsPedidosDoUsuario(idUser);
        int totalVendas = ControladorUsuario.pegarTodasAsVendasDoUsuario(idUser);
        int totalDferenca = totalPedidos - totalVendas;

        result = "[\n";
        result += "['Task', 'Hours per Day'],\n";
        result += "[ 'Total Pedidos', " + totalPedidos + "],\n";
        result += "[ 'Vendas Efetuadas', " + totalVendas + "],\n";
        result += "[ 'Pedidos Sem Venda', " + totalDferenca + "],\n";

        result += "]";

        return result;
    }

    public static Float pegarValorTotalDasVendas(int idUsuario) throws SQLException {
        Float totalVendido = 0f;
        List<VendaEntidade> lstVendas = VendaDAO.pegarTodasAsVendasDoUsuario(idUsuario);
        for (VendaEntidade v : lstVendas) {
            totalVendido += v.getValor();
        }

        return totalVendido;
    }

    public static Float pegarValorTotalInvestido(int idUsuario) throws SQLException {
        Float totalInvestido = 0f;
        List<ProdutoEntidade> lstProdutos = ProdutoDAO.ListarProdutosCadastrados(idUsuario);
        for (ProdutoEntidade p : lstProdutos) {
            boolean estaEmEstoque = EstoqueDAO.esteProdutoEstaEmEstoque(p.getIdProduto());
            if (estaEmEstoque) {
                EstoqueEntidade e = new EstoqueEntidade();
                e.setIdProduto(p.getIdProduto());
                e.setIdUsuario(p.getIdUsuario());
                int qtd = EstoqueDAO.pegarQuantidadeDoItemNoEstoque(e);
                Float quantidade = (float) qtd;
                Float calculo = quantidade * p.getPrecoCompra();
                totalInvestido += calculo;
            }

        }
        Float despesaTotal = DespesaDAO.pegarSomaDepsesaUsuario(idUsuario);
        totalInvestido += despesaTotal;
        return totalInvestido;
    }
}
