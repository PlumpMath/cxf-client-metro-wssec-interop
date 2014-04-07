package simple.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.ws.Holder;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xmlsoap.ping.IPingService;
import org.xmlsoap.ping.PingService;

/**
 * Interop test: CXF client against Metro service with WS-Security policies
 * 
 * @author Cyril DANGERVILLE
 * 
 */
public class PingServiceClientTest
{
	protected static Server server;
	
	public static void main(String[] args) throws Exception {
		setUp();
	}

	@BeforeClass
	public static void setUp() throws Exception
	{
		// CXF debugging
		System.setProperty("java.util.logging.config.file", PingServiceClientTest.class.getResource("/logging.properties").getFile());

		// SSL debugging
		//System.setProperty("javax.net.debug", "ssl, handshake");

		server = new Server();

		// https://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty#Configuring_Connectors
		SslSelectChannelConnector sslConnector = new SslSelectChannelConnector();
		sslConnector.setPort(8443);
		SslContextFactory cf = sslConnector.getSslContextFactory();
		cf.setKeyStorePath(PingServiceClientTest.class.getResource("/server-keystore.jks").toExternalForm());
		cf.setKeyStorePassword("changeit");
		cf.setKeyManagerPassword("changeit");
		cf.setTrustStore(PingServiceClientTest.class.getResource("/server-truststore.jks").toExternalForm());
		cf.setTrustStorePassword("changeit");
		server.setConnectors(new Connector[] { sslConnector });

		// For dumping SOAP request/response messages on Metro service side
		// System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump","true");

		WebAppContext wactx = new WebAppContext();
		wactx.setContextPath("/jaxws-sc");
		
		wactx.setWar(PingServiceClientTest.class.getResource("/jaxws-sc.war").toExternalForm());

//		HandlerCollection handlers = new HandlerCollection();
//		handlers.setHandlers(new Handler[] { wactx, new DefaultHandler() });
		server.setHandler(wactx);

		// try
		// {
		server.start();
		//server.join();
		// } catch (Exception e)
		// {
		// e.printStackTrace();
		// }
	}

	@AfterClass
	public static void tearDown() throws Exception
	{
		if (server != null)
		{
			server.stop();
			server.destroy();
			server = null;
		}
	}

	@Test()
	public void testWsscOverHttps() throws MalformedURLException, InterruptedException
	{
		// FIXME: http://cxf.apache.org/docs/jax-rs-client-api.html
		// Use webclient api to specify spring config directly instead of a default bus
		// Creating the service client
		SpringBusFactory bf = new SpringBusFactory();
		URL busFile = PingServiceClientTest.class.getResource("/client-cxf.xml");
		Bus bus = bf.createBus(busFile.toString());
		BusFactory.setDefaultBus(bus);

		URL wsdlLocation = new URL("https://localhost:8443/jaxws-sc/simple?wsdl");
		PingService service = new PingService(wsdlLocation);
		IPingService stub = service.getPingPort();

		stub.ping(new Holder<>("1"), new Holder<>("sun"), new Holder<>("Passed!"));

		// Ping again
		stub.ping(new Holder<>("1"), new Holder<>("sun"), new Holder<>("Passed again!"));

		// Ping the third time
		stub.ping(new Holder<>("1"), new Holder<>("sun"), new Holder<>("Passed again again!"));
	}
}
