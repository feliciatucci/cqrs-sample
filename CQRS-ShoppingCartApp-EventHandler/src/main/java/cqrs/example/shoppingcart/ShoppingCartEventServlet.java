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

import static org.axonframework.domain.GenericEventMessage.asEventMessage;

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

import cqrs.example.shoppingcart.event.ItemAddedEvent;

//import cqrs.example.shoppingcart.command.AddItemCommand;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Servlet implementation class ShoppingCartEventServlet
 */
@WebServlet("/ShoppingCartEventServlet")
public class ShoppingCartEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartEventServlet.class);

    
    private ApplicationContext ac; 
    private CommandGateway commandGateway;
    
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    
    @Override
    public void init(ServletConfig config) throws ServletException {
       super.init(config);

       ac = new ClassPathXmlApplicationContext("/eventHandler-configuration.xml");
       
       logger.debug("sono nella init della servlet");
       
       // we get the event bus from the application context
       EventBus eventBus = ac.getBean(EventBus.class);
//       eventBus.subscribe(eventListener);
	      
    }
}
