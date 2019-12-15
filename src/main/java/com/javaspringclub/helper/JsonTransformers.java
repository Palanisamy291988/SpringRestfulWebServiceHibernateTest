package com.javaspringclub.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTransformers {

	private static ObjectMapper objectMapper;
	

	public JsonTransformers() {
		if(objectMapper==null){
			synchronized (JsonTransformers.class) {
				objectMapper = new ObjectMapper();
				//US11230 - all services called during the login process
				//objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			}			
		}
	}
	
	public String ObjectToJson(Object obj) throws IOException{
		return objectMapper.writeValueAsString(obj);
	}
	
	public Object JsonToObject(String objMapper,Object obj) throws JsonParseException, JsonMappingException, IOException{
		return objectMapper.readValue(objMapper, obj.getClass());
	}
	
	
	public String getValue(String objectMapper, String property)
			throws JsonParseException, IOException {
		JsonFactory jfactory = new JsonFactory();
		String attValue = null;
		
		if(objectMapper == null || property == null)
		{
			return attValue;
		}
		
		JsonParser jParser = jfactory
				.createJsonParser(new String(objectMapper));
		try {
			while (jParser.nextToken() != JsonToken.END_OBJECT) {
				String fieldname = jParser.getCurrentName();
				if (property.equals(fieldname)) {
					jParser.nextToken();
					attValue = jParser.getText();
					jParser.close();
					jfactory=null;
					break;
				}
			}
		} catch (Exception e) {
			//logger.error("Exception in getValue:",e);
		}finally {
			jParser.close();
			jfactory=null;
		}
		return attValue;
	}
	

}
