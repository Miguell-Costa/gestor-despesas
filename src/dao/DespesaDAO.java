package dao;

import db.DataBaseConnection;
import model.Despesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    // metodo para adicionar uma despesa
    public void adicionar(Despesa despesa) {
        String sql = "INSERT INTO despesa (nome, valor, mes, ano) VALUES(?,?,?,?)";

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, despesa.getNome());
            stmt.setDouble(2, despesa.getValor());
            stmt.setInt(3, despesa.getMes());
            stmt.setInt(4, despesa.getAno());

            stmt.executeUpdate();

            System.out.println("Adicionada com sucesso");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    // metodo para editar uma despesa
    public void editar(Despesa despesa, int id){
        String sql = "UPDATE despesa SET nome=?, valor=?, mes=?, ano=? WHERE id=?";

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, despesa.getNome());
            stmt.setDouble(2, despesa.getValor());
            stmt.setInt(3, despesa.getMes());
            stmt.setInt(4, despesa.getAno());
            stmt.setInt(5, id);

            stmt.executeUpdate();

            System.out.println("Atualizado com sucesso");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // metodo para listar todas as despesas
    public List<Despesa> listar(){
        String sql = "SELECT * FROM despesa";
        List<Despesa> despesas = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Despesa despesa = new Despesa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getInt("mes"),
                        rs.getInt("ano")
                );
                despesas.add(despesa);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return despesas;
    }

    // metodo para eliminar uma despesa
    public void eleminar(int id){
        String sql = "DELETE FROM despesa WHERE id=?";

        try (Connection conn = DataBaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Eleminado com sucesso");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
