package br.com.casadocodigo.loja.infra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class FileLoader {

	private static final String realPath = "/home/fidelis/Imagens/";
	
	public String load(String path){
		try {
			Path imagePath = Paths.get(realPath+path);
			byte[] encodeBase64 = Base64.encodeBase64(Files.readAllBytes(imagePath));
			String base64Encoded = new String(encodeBase64, "UTF-8");
			return base64Encoded;
		} catch (IllegalStateException | IOException e) {
			return new String();
		}
	}
}
