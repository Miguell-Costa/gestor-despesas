package dao;

import db.DataBaseConnection;
import model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// classe para interagir com a tabela pessoa da base de dados

public class PessoaDAO {

    // metodo para adicionar uma pessoa
    public void adicionar(Pessoa pessoa){
        String sql = "INSERT INTO pessoa (nome, salario) VALUES(?,?)";

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, pessoa.getNome());
            stmt.setDouble(2, pessoa.getSalario());

            stmt.executeUpdate();

            System.out.println("Adicionada com sucesso");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // metodo para atualizar uma pessoa
    public void atualizar(Pessoa pessoa, int id){
        String sql = "UPDATE pessoa SET nome=?, salario=? WHERE id=?";

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, pessoa.getNome());
            stmt.setDouble(2, pessoa.getSalario());
            stmt.setInt(3, id);

            stmt.executeUpdate();

            System.out.println("Atualizada com sucesso");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // metodo para listar todas as pessoas
    public List<Pessoa> listar(){
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Pessoa pessoa = new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("salario")
                );
                pessoas.add(pessoa);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pessoas;
    }

    // metodo para eliminar uma pessoa
    public void eliminar(int id){
        String sql = "DELETE FROM pessoa WHERE id=?";

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Eliminada com sucesso");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
