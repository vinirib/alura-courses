package br.com.casadocodigo.loja.conf;

import java.util.Locale;


import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 
 * @author fidelis
 *	
 *	JsonViewResolver use Jackson lib to resolve an uri to JSON
 */
public class JsonViewResolver implements ViewResolver {

	/**
	 * The setPrettyPrint is to make an friendly view of JSON 
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);
		return jsonView;
	}

}
