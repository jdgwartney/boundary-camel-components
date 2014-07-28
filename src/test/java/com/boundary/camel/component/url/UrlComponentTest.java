package com.boundary.camel.component.url;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boundary.camel.component.common.ServiceStatus;
import com.boundary.camel.component.ping.PingProducer;


public class UrlComponentTest extends CamelTestSupport {
	
    private static final Logger LOG = LoggerFactory.getLogger(UrlComponentTest.class);

    @Produce(uri = "direct:url-in")
    private ProducerTemplate in;
    
    @EndpointInject(uri = "mock:url-out")
    private MockEndpoint out;

    @Test
    public void testUrl() throws Exception {
    	UrlConfiguration urlConfiguration = new UrlConfiguration();
    	
        out.expectedMessageCount(1);
        out.await(5, TimeUnit.SECONDS);
        
        out.assertIsSatisfied();
        List <Exchange> exchanges = mock.getExchanges();
    	LOG.info("EXCHANGE COUNT: " + exchanges.size());

        for(Exchange e: exchanges) {
        	UrlResult result = e.getIn().getBody(UrlResult.class);
        	
        	assertTrue("check url status",result.getStatus() == ServiceStatus.SUCCESS);
        	LOG.info(result.getURL());
        	LOG.info(result.getHost());
        	//assertEquals("url does not match","")
        }
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("url://localhost?delay=5")
                  .to("mock:result");
            }
        };
    }
}
