package br.com.phc.citiesapi.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.phc.citiesapi.exception.ErrorDetails;
import br.com.phc.citiesapi.exception.StandardError;

public class TransactionFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(TransactionFilter.class);
	private static final String MENSAGEM_ERRO  = "Sua solicitação não pode ser completada devido há um erro em sua requisição.";
	private static final String X_TRANSACTION_ID = "x-transaction-id";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Filter validate s-transaction-id");
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String xRegisterPublic = req.getHeader(X_TRANSACTION_ID);
        boolean isTransaction = path.equals("/transaction") ? Boolean.TRUE : Boolean.FALSE;
        
        if ((!isTransaction) && (xRegisterPublic == null || xRegisterPublic.trim().equals(""))) {
        	try {
        		String errorDetails = this.createErrorDetails(path, xRegisterPublic);

            	res.setStatus(HttpStatus.BAD_REQUEST.value());
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errorDetails);
				
                return;	
        	} catch (Exception e) {
        		res.sendError(HttpStatus.BAD_REQUEST.value(), MENSAGEM_ERRO);
        		return;
			}
        }
        
        chain.doFilter(request, response);
	}
	
	private String createErrorDetails(String path, String xRegisterPublic) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		ErrorDetails details = new ErrorDetails();
		details.setUniqueId(UUID.randomUUID().toString());
		details.setMessage(MENSAGEM_ERRO);
		details.setInformationCode(HttpStatus.BAD_REQUEST.name());
		
		StandardError error = new StandardError();
		error.setPath(path);
		error.setMessage(MENSAGEM_ERRO);
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setErrorDetails(details);
		
		String jsonError = objectMapper.writeValueAsString(details);
		log.info(String.format("Erro: %s", jsonError));
		return jsonError;
	}
}
