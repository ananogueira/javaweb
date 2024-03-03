package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Módulo de conexão *. */
	// Parâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "mysql";
			
	
	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Inserir contacto.
	 *
	 * @param contacto the contacto
	 */
	public void inserirContacto(JavaBeans contacto) {
		// Passo 8 - execução da query
		String create = "insert into contactos (nome, telemovel, email) values (?,?,?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução na base de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Passo 7 - gets
			// substituir os parâmatros (?) pelo conteúdo das variáveis
			pst.setString(1, contacto.getNome());
			pst.setString(2, contacto.getTelemovel());
			pst.setString(3, contacto.getEmail());
			
			// Passo 9 - atualização dos dados na nase de dados
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com a base de dados
			con.close();	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar contactos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContactos() {
		// criando um objeto para aceder à classe JavaBeans
		ArrayList<JavaBeans> contactos = new ArrayList<>();
		String read = "select * from contactos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			// Passo 3
			ResultSet rs = pst.executeQuery();
			// o ciclo será executado enquanto houver contactos
			while (rs.next()) {
				// variáveis de apoio que recebem os dados da base de dados
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String telemovel = rs.getString(3);
				String email = rs.getString(4);
				
				// preencher o ArrayList
				contactos.add(new JavaBeans(idcon, nome, telemovel, email));
			}
			con.close();
			return contactos;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Selecionar contacto.
	 *
	 * @param contacto the contacto
	 */
	public void selecionarContacto(JavaBeans contacto) {
		String read2 = "select * from contactos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			// Passo 19.5             // Passo 19.4
			pst.setString(1, contacto.getIdcon());
			// Passo 19.6 - executeQuery()
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// Atribuir as variáveis JavaBeans
				// Passo 19.8        // Passo 19.7
				contacto.setIdcon(rs.getString(1));
				contacto.setNome(rs.getString(2));
				contacto.setTelemovel(rs.getString(3));
				contacto.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar contacto.
	 *
	 * @param contacto the contacto
	 */
	public void alterarContacto(JavaBeans contacto) {
		String update = "update contactos set nome=?,telemovel=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			// Passo 21.17   // Passo 21.16
			pst.setString(1, contacto.getNome());
			pst.setString(2, contacto.getTelemovel());
			pst.setString(3, contacto.getEmail());
			pst.setString(4, contacto.getIdcon());
			// Passo 21.18
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Apagar contacto.
	 *
	 * @param contacto the contacto
	 */
	public void apagarContacto(JavaBeans contacto) {
		// Passo 22.7
		String delete = "delete from contactos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			// Passo 23.6    Passo 23.5
			pst.setString(1, contacto.getIdcon());
			// Passo 23.7
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			 System.out.println(e);
		}
	}
}
