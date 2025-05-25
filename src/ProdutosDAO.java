/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto(ProdutosDTO produto) {
    Connection conn = null;
    PreparedStatement pstm = null;

    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try {
        conn = new conectaDAO().connectDB();
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, produto.getNome());
        pstm.setDouble(2, produto.getValor());
        pstm.setString(3, produto.getStatus());
        pstm.execute();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
    } finally {
        try {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
    
    public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    try {
        Connection conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getDouble("valor"));
            p.setStatus(rs.getString("status"));

            lista.add(p);
        }

        conn.close();

    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao listar: " + erro.getMessage());
    }

    return lista;
}

    
    public void venderProduto(int id) {
    Connection conn = null;
    PreparedStatement pstm = null;

    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try {
        conn = new conectaDAO().connectDB(); 
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.execute();

        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender: " + e.getMessage());
    } finally {
        try {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            System.out.println("Erro ao fechar conexão: " + ex.getMessage());
        }
    }
}


    
    
    
        
}

