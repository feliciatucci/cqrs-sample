package cqrs.example.shoppingcart.saga;

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

import java.util.UUID;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CreateInvoiceCommand {
    @TargetAggregateIdentifier
    private String invoiceId;
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String code;

	private String description;
    private String name;
    private String address;

	public CreateInvoiceCommand() {
		// TODO Auto-generated constructor stub
		invoiceId = UUID.randomUUID().toString();
    	
	}
 
    public CreateInvoiceCommand(String invoiceId) {
        this.invoiceId = invoiceId;
    }

	public String getInvoiceId() {
		return invoiceId;
	}



}
