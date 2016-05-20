package cqrs.example.utility.db;
/**
 * Copyright 2016 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoConfiguration {


	public @Bean DB mongoDb() throws Exception {
		MongoClientURI mcUri = new MongoClientURI(getUrlConnection());
		MongoClient mc = new MongoClient(mcUri);
		mc.getDB(mcUri.getDatabase());
		return mc.getDB(mcUri.getDatabase());
	}

	
	private static String getUrlConnection() {

		String env = System.getenv("VCAP_SERVICES");
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(env);
			Iterator<JsonNode> dbNode = node.get("mongodb-2.2").getElements();
			//Iterator<JsonNode> dbNode = node.get("mongolab-n/a").getElements();
			
			JsonNode cred =  (JsonNode)dbNode.next().get("credentials");

			String uri =cred.get("url").getTextValue();
			//String uri =cred.get("uri").getTextValue();
			System.out.println("url db: " + uri);

			return uri;

		} catch (JsonGenerationException e) {
			System.out.println(e);
		} catch (JsonMappingException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		return null;
	}



}
