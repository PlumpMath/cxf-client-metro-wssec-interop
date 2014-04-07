package org.xmlsoap.ping;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2014-01-13T01:20:48.468+01:00
 * Generated source version: 2.7.8
 * 
 */
@WebService(targetNamespace = "http://xmlsoap.org/Ping", name = "IPingService")
@XmlSeeAlso({ObjectFactory.class})
public interface IPingService {

    @RequestWrapper(localName = "Ping", targetNamespace = "http://xmlsoap.org/Ping", className = "org.xmlsoap.ping.Ping")
    @WebMethod(operationName = "Ping", action = "http://xmlsoap.org/Ping")
    @ResponseWrapper(localName = "PingResponse", targetNamespace = "http://xmlsoap.org/Ping", className = "org.xmlsoap.ping.PingResponseBody")
    public void ping(
        @WebParam(mode = WebParam.Mode.INOUT, name = "scenario", targetNamespace = "http://xmlsoap.org/Ping")
        javax.xml.ws.Holder<java.lang.String> scenario,
        @WebParam(mode = WebParam.Mode.INOUT, name = "origin", targetNamespace = "http://xmlsoap.org/Ping")
        javax.xml.ws.Holder<java.lang.String> origin,
        @WebParam(mode = WebParam.Mode.INOUT, name = "text", targetNamespace = "http://xmlsoap.org/Ping")
        javax.xml.ws.Holder<java.lang.String> text
    );
}
