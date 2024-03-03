package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contacto. */
	JavaBeans contacto = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		// teste de recebimento de requisições
		System.out.println(action);
		// Alt + Shift + Y - quebra automática de linha
		// Ctrl + Shift + F - indentação do código
		// Ctrl + Shift + O - adiciona a biblioteca em falta

		if (action.equals("/main")) {
			contactos(request, response);
		} else if (action.equals("/insert")) {
			adicionarContacto(request, response);
		} else if (action.equals("/select")) {
			listarContacto(request, response);
		} else if (action.equals("/update")) {
			editarContacto(request, response);
		} else if (action.equals("/delete")) {
			removerContacto(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}

		// Teste de conexão
		// dao.testeConexao();
	}

	/**
	 * Contactos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contactos
	protected void contactos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Passo 2
		// Criando um objeto que irá receber os dados JavaBeans
		// Passo 6
		// lista recebe todos os contactos que estão na classe JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContactos();
		
		// Passo 7
		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contactos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Adicionar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo contacto
	protected void adicionarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento dos dados do formulário
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("telemovel"));
		System.out.println(request.getParameter("email"));
		
		// setters dos atributos JavaNeans
		contacto.setNome(request.getParameter("nome"));
		contacto.setTelemovel(request.getParameter("telemovel"));
		contacto.setEmail(request.getParameter("email"));
		
		// Passo 6
		// invocar o método inserirContacto passando o objeto contacto
		dao.inserirContacto(contacto);
		
		// Passo 10
		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Listar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar contacto
	protected void listarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String idcon = request.getParameter("idcon");
		//System.out.println(idcon);
		
		// Passo 18.2
		// Atribuir a variável JavaBeans
		contacto.setIdcon(request.getParameter("idcon"));
		
		// Passo 19.3
		// Executar selecionar contacto (DAO)
		dao.selecionarContacto(contacto);

		// Atribuir os atributos do formulário com o conteúdo JavaBeans
		// Passo 20.10                passo20.9
		request.setAttribute("idcon", contacto.getIdcon());
		request.setAttribute("nome", contacto.getNome());
		request.setAttribute("telemovel", contacto.getTelemovel());
		request.setAttribute("email", contacto.getEmail());
		// Encaminhar oa documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Atualizar contacto
	protected void editarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Passo 21.13
		contacto.setIdcon(request.getParameter("idcon"));
		contacto.setNome(request.getParameter("nome"));
		contacto.setTelemovel(request.getParameter("telemovel"));
		contacto.setEmail(request.getParameter("email"));
		// executar o método alterarContacto
		dao.alterarContacto(contacto);
		// Passo 21.19
		// redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
		
	}

	/**
	 * Remover contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Remover contacto
	protected void removerContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do contacto a ser apagado (validador.js)
		//String idcon = request.getParameter("idcon");
		//System.out.println(idcon);
		// atribuir a variável idcon JavaBeans
		contacto.setIdcon(request.getParameter("idcon"));
		// Passo 23.4
		// executar o método apagarContacto (DAO) passando o objeto contacto
		dao.apagarContacto(contacto);
		// Passo 23.8
		// redirecionar para o documento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main"); // goGet-action:/main chama função contactos que atualiza a lista de contactos
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatório em PDF
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebimento do id do contacto a ser apagado (validador.js)
		Document documento = new Document();
		try {
			// tipo de contacto
			response.setContentType("application/pdf");
			// nome do documento
			response.addHeader("Contents-Disposiion", "inline; filename=" + "contactos.pdf");
			// criar documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// abrir o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de contactos:"));
			documento.add(new Paragraph(" "));
			// criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			// cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telemovel"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// preencher a tabela com os contactos
			ArrayList<JavaBeans> lista = dao.listarContactos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getTelemovel());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}

