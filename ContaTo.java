//Classe de Negócio
//Esta classe é utilizada para realizar as operações da conta do cliente
import java.util.ArrayList;

public class ContaTo
{
   private int nBanco;
   private int nAgencia;
   private int nConta;
   private int senha;
   private int saldo;
     
   public ContaTo()
   {
      nBanco = 033;
      nAgencia = 1111;
      nConta = 123456;
      senha = 1029;
      saldo = 20;
   }//fim do construtor

   //Metodo Construtor com parametros
   public ContaTo(int nBanco,int nAgencia,int nConta,int senha)
   {
      setBanco(nBanco);
      setAgencia(nAgencia);
      setConta(nConta);
      setSenha(senha);
   }//fim do construtor

   //Metodos de Acesso(GET'S)
   public int getBanco()
   {
      return nBanco;
   }
   
   public int getAgencia()
   {
      return nAgencia;
   }
   
   public int getConta()
   {
      return nConta;
   }

   public int getSenha()
   {
      return senha;
   }
	
   public int getSaldo()
   {
      return saldo;
   }

   //Metodos Modificadores(SET'S)
   public void setBanco(int nBanco)
   {
      this.nBanco = nBanco;
   }
   
   public void setAgencia(int nAgencia)
   {
      this.nAgencia = nAgencia;
   }
   
   public void setConta(int nConta)
   {
      this.nConta = nConta;
   }

   public void setSenha(int senha)
   {
      this.senha = senha;
   }
   
   public void setSaldo(int saldo)
   {
      this.saldo = saldo;   
   }
   
   public void criar() 
   {
      ContaDAO dao = new ContaDAO();
      dao.incluir(nConta, nBanco, nAgencia, saldo);
   }
   public void carregarExtrato()
   {
      ContaDAO dao = new ContaDAO();
      @SuppressWarnings("rawtypes")
         ArrayList dados = dao.carregar(nConta);
      nAgencia = (int) dados.get(0);
      nConta = (int) dados.get(1);
      saldo = (int)dados.get(2);
   }
   @Override
      public String toString() {
      return "Conta [Numero Conta=" + nConta + ", Agência=" + nAgencia + ", Saldo=" + saldo + "]";
   
   }
   
}