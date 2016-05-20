package cqrs.example.shoppingcart;
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
import java.util.UUID;

import javax.security.auth.callback.Callback;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cqrs.example.shoppingcart.command.AddItemCommand;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartServlet.class);

    
    private ApplicationContext ac; 
    private CommandGateway commandGateway;
    
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String itemId=request.getParameter("id");
//        Integer quantity=Integer.parseInt(request.getParameter("quantity"));
        Integer quantity = 1;
        AddItemCommand addItemCommand=new AddItemCommand(itemId);
        addItemCommand.setQuantity(quantity);
        
        logger.debug("quantity:" + quantity);
        logger.debug("id:" + itemId);
        //asynchronous call - callback registered
        CommandCallback commandCallback = new CommandCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
            	logger.debug("Expected this command to fail");
            }

            @Override
            public void onFailure(Throwable cause) {
            	logger.debug("command exception", cause.getMessage());
            }
        };
        commandGateway.send(addItemCommand, commandCallback);
    }

    
    @Override
    public void init(ServletConfig config) throws ServletException {
       super.init(config);

       ac = new ClassPathXmlApplicationContext("/demo-configuration.xml");
       
       logger.debug("sono nella init della servlet");
       
       commandGateway= ac.getBean(CommandGateway.class);
	      
    }
}
