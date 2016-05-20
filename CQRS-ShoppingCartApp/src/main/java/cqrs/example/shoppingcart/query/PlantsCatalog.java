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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PlantsCatalog extends ShoppingCartQueryManager implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PlantsCatalog(){
		super();
	}
	
	public DBObject execute(){
		BasicDBObject plant = new BasicDBObject("code", getPlantData().getCode());
		DBObject thePlant = coll.findOne(plant);
		if (thePlant==null){
			coll.save(plant);
			System.out.println("New Plant has been saved into PlantCatalog DB");
			return plant;
		}

		System.out.println("in execute di PlantsCatalog: description->"  + getPlantData().getDescription());
		System.out.println("in execute di PlantsCatalog: price->"  + getPlantData().getPrice());

		coll.findAndModify(plant, thePlant);
			
		return thePlant;
	}


}
