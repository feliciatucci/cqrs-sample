package cqrs.example.shoppingcart.query;
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

import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import cqrs.example.shoppingcart.query.*;

import cqrs.example.utility.db.MongoConfiguration;

public class ShoppingCartQueryManager implements Serializable {
	
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	protected static DB db;
	protected static DBCollection coll;
	protected DBObject plant;
	protected PlantData plantData;


	public ShoppingCartQueryManager(){
		init();
	}
	
	private void init(){
		// For Annotation
		ApplicationContext ctx = 
	             new AnnotationConfigApplicationContext(MongoConfiguration.class);
		db = (DB) ctx.getBean("mongoDb");

		coll = db.getCollection("plants");
	}

	public void setPlant(DBObject plant) {
		this.plant = plant;
	}
	
	public void setPlantData(PlantData plantData){
		this.plantData = plantData;
	}

	public PlantData getPlantData(){
		return plantData;
	}

	public BasicDBObject getUserPreference(PlantData plantData) {
		BasicDBObject plant = new BasicDBObject("code", plantData.getCode());
		
		System.out.println("in getPlantName : " +  plantData.getName());
		
		BasicDBObject thePlant = (BasicDBObject)coll.findOne(plant);

		System.out.println("Utente Trovato: " +  thePlant);

		return thePlant;
	}

	public DBObject execute(){ return null;};

}
