package com.newpractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class MainController {
	
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    
	    public String fileUpload(@RequestParam("fileinfo") MultipartFile file) {
	          String result = "File was uploaded successfully";
	          try {
	        	  File convertfile = new File("D://temp/"+file.getOriginalFilename());
	        	  convertfile.createNewFile();
	        	  FileOutputStream fout = new FileOutputStream(convertfile);
	        	  fout.write(file.getBytes());
	        	  fout.close();
	          }
	          catch (IOException iex) {
	        	  result ="Error"+iex.getMessage();
	          }
	
	          return result;
	}
	@GetMapping(value = "/download")   
    public ResponseEntity<Object> downloadFile() throws IOException  {
        // server location of file which user wants to download   
    	String fileName = "D:/temp/dac.txt";
    	
    	
    	File file = new File(fileName); 
    	//Read your file
    	   	
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
     
    	//Prepare Header to send it to client side
    	HttpHeaders headers = new HttpHeaders();
    	//providing information about data type
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        
     
        //prepare responseentity (header-- file information, body-- conatins data of file)
        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
        		.contentType(MediaType.parseMediaType("application/txt")).body(resource);
        
     return responseEntity;
    }
	
}