package cqrs.example.shoppingcart.saga;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;

public class PurchaseManagementSaga extends AbstractAnnotatedSaga {


	    @StartSaga
	    @SagaEventHandler(associationProperty = "itemId")
	    public void handle(ProductPurchasedEvent event) {
	        // identifiers
	        String deliveryId = createDeliveryId();
	        String invoiceId = createInvoiceId();
	        // associate the Saga with these values, before sending the commands
	        associateWith("shipmentId", deliveryId);
	        associateWith("invoiceId", invoiceId);
	        // send the commands
	        commandGateway.send(new PrepareDeliveryCommand(deliveryId));
	        commandGateway.send(new CreateInvoiceCommand(invoiceId));
	    }

	    private boolean paid = false;
	    private boolean delivered = false;
	    private transient CommandGateway commandGateway;

	    private String createInvoiceId() {
			return  UUID.randomUUID().toString();
		}

		private String createDeliveryId() {
			return  UUID.randomUUID().toString();
		}

		@SagaEventHandler(associationProperty = "deliveryId")
	    public void handle(DeliveryArrivedEvent event) {
	        delivered = true;
	        if (paid) {
	            end();
	        }
	    }

	    @SagaEventHandler(associationProperty = "invoiceId")
	    public void handle(InvoicePaidEvent event) {
	        paid = true;
	        if (delivered) {
	            end(); 
	        }
	    }

	    // ...

}
