package com.boundary.camel.component.port;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.impl.ScheduledPollConsumer;

/**
 * The Ping consumer.
 */
public class PortConsumer extends ScheduledPollConsumer {
    private final PortEndpoint endpoint;

    public PortConsumer(PortEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
    }
    
    protected PortInfo executePortCheck() {
    	PortCheck portCheck = new PortCheck();
    	
    	// TBD: Set this during initialization
    	portCheck.setHost(endpoint.getHost());
    	
    	return portCheck.performCheck();
    }
    
	@Override
	protected int poll() throws Exception {

		Exchange exchange = endpoint.createExchange();
		Message message = exchange.getIn();
		
		PortInfo status = executePortCheck();

		message.setBody(status, PortInfo.class);

		try {
			// send message to next processor in the route
			getProcessor().process(exchange); 
			return 1; // number of messages polled
		} finally {
			// log exception if an exception occurred and was not handled
			if (exchange.getException() != null) {
				getExceptionHandler().handleException(
						"Error processing exchange", exchange,
						exchange.getException());
			}
		}
	}
}
