package com.jsonparajava;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AclassePrincipal {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	
    	String caminhoOndeSerahSalvaMinhaClasse = "c:/temp/"; 
    	String nomeDoPacote = "mobile/contasim/model";
    	String expressaoRegularAserUtilizada = "c:/temp/exemploREGEX.json";		
    	String caminhoDoJsonQestouBuscando = "c:/temp/exemploJSON.json"; 
    	String nomeDaClasseAserCriada = "MinhaPrimeiraClasse";
    	
    	ObjectMapper mapper = new ObjectMapper();
        
        @SuppressWarnings("unchecked") 
        Map<String, Object> map = mapper.readValue(FileUtils.readFileToString(new File(caminhoDoJsonQestouBuscando)), Map.class);
        
        Generator generator = new Generator(caminhoOndeSerahSalvaMinhaClasse, nomeDoPacote, expressaoRegularAserUtilizada, null, null, false);
        
        System.out.println("Generating classes....");
        
        		  generator.generateClasses(nomeDaClasseAserCriada, map);
        		  
        System.out.println();
        System.out.println(String.format("Generated %s files. All done.", generator.getGeneratedFileCount()));
    }
}
