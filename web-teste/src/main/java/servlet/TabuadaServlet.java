package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.CalculadoraService;

@WebServlet(name = "TabuadaServlet", urlPatterns = { "/tabuada" })
public class TabuadaServlet extends HttpServlet {


	private static final String PARAMETRO_NUM_1 = "num1";

	private static final long serialVersionUID = 1L;
	
	static CalculadoraService calculadoraService = new CalculadoraService();
 
	@Override
	public final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		processar(req, resp);
	}

	@Override
	public final void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		processar(req, resp); 
	}

	private void processar(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		Map<String, String> erros = new HashMap<String, String>();
		
		List<Double> listaNum = getListaParametroDouble(req, PARAMETRO_NUM_1, "Número 1", erros);
		
		//String operacao = calculadoraService.getParametroOperacao(req, erros);
			
		if (erros.isEmpty()) {
			for(int i = 1; i <= 10;i++) {
				for(Double num: listaNum) {
	
					out.print(calculadoraService.multiplicar(i,num));
					out.print("\t\t");
				}
				out.print("\n");
			}
		} else {
			out.print(erros);
		}
		
	}
	
	private List<Double> getListaParametroDouble(final HttpServletRequest req, final String nome, final String campo,
			final Map<String, String> erros) {
		List<Double> listaParams = new ArrayList<Double>();
		String numStr = req.getParameter(nome);
		if (numStr == null || numStr.isEmpty()) {
			erros.put(campo, "Número requerido!");
			return null;
		}
		String[] strArr = numStr.split(";");
		for(String str: strArr) {
			Double num = null;
			try {
				num = Double.parseDouble(str);
				listaParams.add(num);
			} catch (NumberFormatException nfe) {
				erros.put(campo, "Número inválido!");
			}
		}
		return listaParams;
	}
	

}
