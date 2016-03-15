 package com.example.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import com.example.rest.DaoClass;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/file")
public class FileUploads {

	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(@MultipartForm FileUploadForm form) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {

		byte[] content = form.getData();
		DaoClass.connectionSetup();
		DaoClass.insertRecords(content);
       
        
        
		return Response.status(200).entity("Sucess").build();
	}
	@GET
	@Path("/request")
	public Response retrieve() throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {

		DaoClass.connectionSetup();
        HashMap<String, String> rs = DaoClass.retrieveRecord();
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = mapperObj.writeValueAsString(rs); 
        
        
		return Response.status(200).entity(jsonResp).build();
	}
	
}
