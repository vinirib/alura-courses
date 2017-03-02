package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	
	private static final String realPath = "/home/fidelis/Imagens/";
	
	public String gravar(String baseFolder, MultipartFile file){
		try {
			String relativePath = baseFolder + "/" +file.getOriginalFilename();
			File folder = new File(realPath+baseFolder);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			String path = realPath + baseFolder + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));
			return relativePath;
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}
}
