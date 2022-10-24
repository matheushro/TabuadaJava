package classes;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CalculadoraService {
	
	private static final String PARAMETRO_OPERACAO = "operacao";

	public double adicionar(final double parcela1, final double parcela2) {
		return parcela1 + parcela2;
	}

	public double subtrair(final double minuendo, final double subtraendo) {
		return minuendo - subtraendo;
	}

	public double dividir(final double dividendo, final double divisor) {
		return dividendo / divisor;
	}

	public String multiplicar(final int i,final double multiplicador) {
		
		Double resultado = i * multiplicador;
		return  i + " x " + multiplicador + " = " + resultado.toString();
		

	}
	
	public String getParametroOperacao(final HttpServletRequest req, final Map<String, String> erros) {
		String operacao = null;
		try {
		   operacao = req.getParameter(PARAMETRO_OPERACAO);
			
		} catch (IllegalArgumentException ex) {
			erros.put("Operação", "Operação inválida");
		}
		
		return operacao;
		
	}

}
