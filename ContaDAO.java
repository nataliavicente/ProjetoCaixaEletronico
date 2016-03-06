//DAO
//Esta classe é utilizada para inserir os dados do cliente no banco
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO
{     
   static {
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } 
      catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }
// Obtém conexão com o banco de dados

   public Connection obtemConexao() throws SQLException {
      return DriverManager
         .getConnection("jdbc:mysql://localhost/tutorial?user=alunos&password=alunos");
   }
   
  //Incluir conta no banco
   public void incluir(ContaTo to)
   {
      String sqlInsert = "INSERT INTO conta(nBanco, nAgencia, nConta, senha, saldo) VALUES (?, ?, ?, ?, ?)";
      //PreparedStatement stm = null;
      try (Connection conn = obtemConexao();
      PreparedStatement stm = conn.prepareStatement(sqlInsert);)
      {
         stm.setInt(1, to.getBanco());
         stm.setInt(2, to.getAgencia());
         stm.setInt(3, to.getConta());
         stm.setInt(4, to.getSaldo());
         stm.execute();
      } 
      catch (SQLException e) {
         e.printStackTrace();
      }
   }//fim do metodo incluir   
   
    //Método para carregar dados do cliente no banco de dados
   @SuppressWarnings({ "rawtypes", "unchecked" })
   public ContaTo carregar(int nConta){
      ContaTo to = new ContaTo();
      String sqlSelect = "SELECT  nome,nAgencia,nConta,saldo FROM conta inner join cliente on conta.nConta = cliente.conta WHERE nConta = ?";;
      try (Connection conn = obtemConexao();
      PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
         stm.setInt(1, nConta);
         try (ResultSet rs = stm.executeQuery();) {
            if (rs.next()) {
               to.setAgencia(rs.getInt("nAgencia"));
               to.setConta(rs.getInt("nConta"));
               to.setSaldo(rs.getInt("saldo"));   
            } 
         } 
         catch (SQLException e) {
            e.printStackTrace();
         }
      } 
      catch (SQLException e1) {
         System.out .print(e1.getStackTrace());
      }
      return to;
   }
   //Metodo que consulta saldo
   public String carregarSaldo()
   {
      String sucesso = "";
      String sql = "select nome,nAgencia,nConta,saldo from conta inner join cliente on conta.nConta = cliente.conta where nome = ? AND nAgencia = ? AND nConta = ?";
      PreparedStatement stm = null;
      ResultSet rs = null;
      Cliente cliente = new Cliente();
      try
      {
         stm = conn.prepareStatement(sql);
         stm.setString(1,cliente.getNome());
         stm.setInt(2,getAgencia());
         stm.setInt(3,getConta());
         rs = stm.executeQuery();
         while(rs.next())
         {
            cliente.setNome(rs.getString("nome"));
            setAgencia(rs.getInt("nAgencia"));
            setConta(rs.getInt("nConta"));
            sucesso = "R$" + rs.getInt("saldo");
            stm.close();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         try
         {
            conn.rollback();
         }
         catch (SQLException e1)
         {
            System.out.print(e1.getStackTrace());
         }
      }
      finally
      {
         if (stm != null)
         {
            try
            {
               stm.close();
            }
            catch (SQLException e1)
            {
               System.out.print(e1.getStackTrace());
            }
         }
      }
      return sucesso;
   }//fim do metodo carregar saldo

   
}//fim da classe