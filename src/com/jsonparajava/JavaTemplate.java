package com.jsonparajava;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JavaTemplate {
	
    //public static final String JSON_ANNOTATION = "@JsonProperty";

    public File writeOutJavaFile(String key, String outputDirectory, String packageName, ClassFiles classFiles) throws IOException, ClassNotFoundException {
    	
        ClassFileData classFileData = classFiles.get(key);
        String className = StringHelper.capFirstLetter(key);
        StringBuilder stringBuilder = new StringBuilder();
        
        String pacoteComPonto = packageName.replaceAll("/", ".");
        stringBuilder.append("package ").append(pacoteComPonto).append(";\r\n\r\n");
        
        for (String importPackageName : classFileData.getImportPackages()) {
            stringBuilder.append("import ").append(importPackageName).append(";\r\n");
        }
        stringBuilder.append("public class ").append(className).append(" {\r\n");
        
        for (Map.Entry<String, String> variablesToTypeEntry : classFileData.getMapOfVariablesToTypes().entrySet()) {
        	
            String chave = variablesToTypeEntry.getKey();
            String valor = variablesToTypeEntry.getValue();
            
            if(valor.equals("Integer")){ 	
            	valor = "int";
            }
            if(valor.equals("Double")){
            	valor = "double";
            }
            if(valor.equals("Float")){
            	valor = "float";
            }

            //############## atributos privados: ##############
            stringBuilder.append(" private ").append(valor).append(" ").append(chave).append(";\r\n"); 
            //#################################################
            
            //############## campos para tabela sqlite: ##############
            String meio = "";
    		
			if (valor.equals("int")) {
				
				meio = "INTEGER_";
			}else{
				meio = "TEXT_";	
			}
			stringBuilder.append("public static final String COLUMN_").append(meio).append(chave.toUpperCase()).append(" = \"" + chave + "\";\r\n");
	        //#################################################
		    		
			//############## getters e setters: ##############
			stringBuilder.append("public void set"+StringHelper.capFirstLetter(chave)+"("+valor+" "+chave+"){this."+chave+"="+chave+";}\r\n");
			
			stringBuilder.append("public "+valor+" get"+StringHelper.capFirstLetter(chave)+"(){ return "+chave+";}\r\n");
	        //#################################################
		}
    
        stringBuilder.append("}\r\n");

        String packageDirectory = packageName.replaceAll("\\.", File.separator);
        if (!packageDirectory.endsWith(File.separator)) packageDirectory = packageDirectory + File.separator;
        String file = String.format("%s%s%s%s.java", outputDirectory, File.separator, packageDirectory, className);
        System.out.print(String.format("Writing file '%s' ...", file));
        File outputFile = new File(file);
        FileUtils.writeStringToFile(outputFile, stringBuilder.toString());
        System.out.print("done.");
        System.out.println();
        return outputFile;
    }

    //private StringBuilder appendJsonKey(StringBuilder stringBuilder, String entryKey) {
       //return stringBuilder.append(String.format("\t%s(\"%s\")", JSON_ANNOTATION, entryKey));
    //}
}
