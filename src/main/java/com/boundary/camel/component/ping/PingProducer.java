package com.boundary.camel.component.ping;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Ping producer.
 */
public class PingProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(PingProducer.class);
    private PingEndpoint endpoint;

    public PingProducer(PingEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        LOG.debug(exchange.getIn().getBody().toString());
        System.out.println(exchange.getIn().getBody().toString());
    }
}