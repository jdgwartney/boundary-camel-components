package com.boundary.camel.component.ping;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;

import com.boundary.camel.component.common.ServiceEndpoint;

/**
 * Implements a Camel {@link Endpoint} to ping a host
 */
public class PingEndpoint extends ServiceEndpoint {
	
    public PingEndpoint() {
    }

    public PingEndpoint(String uri, PingComponent component) {
        super(uri, component);
     }

	public PingEndpoint(String endpointUri) {
        super(endpointUri);
    }
    
    /**
     * Creates the producer for the component.
     */
    public Producer createProducer() throws Exception {
        return new PingProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
    	PingConsumer consumer = new PingConsumer(this, processor);
        return consumer;
    }

    public boolean isSingleton() {
        return true;
    }
}
