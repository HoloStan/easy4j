/**
 *
 */
package me.web.framework.utils.log;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.util.EntityUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

public class LogUtil {

    public static LogUtil instance = new LogUtil("LogHelper");

    private Log logger;

    public LogUtil(String name) {
        logger = LogFactory.getLog(name);
    }

    public LogUtil(Class clazz) {
        logger = LogFactory.getLog(clazz);
    }

    public void logDebug(String message) {
        logger.debug(message);
    }

    public void logDebug(String message, Throwable t) {
        logger.debug(message, t);
    }

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logInfo(String message, Throwable t) {
        logger.info(message, t);
    }

    public void logInfo(String message, String... paras) {
        logInfo(String.format(message, paras));
    }

    public void logError(String message) {
        logger.error(message);
    }

    public void logError(String message, Throwable t) {
        logger.error(message,t );
    }

    public void logWarn(String message) {
        logger.warn(message);
    }

    public void logWarn(String message, Throwable t) {
        logger.warn(message,t );
    }


    public void logObject(Object obj) {
        try {
            this.logObject(obj, false);
        } catch (JAXBException ex) {
            //do nothing, eat the exception
        }
    }

    public void logObject(Object obj, boolean throwException) throws JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller m = context.createMarshaller();
            StringWriter sw = new StringWriter();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(obj, sw);
            String xmlOutPut = sw.toString();

            logInfo(xmlOutPut);
        } catch (JAXBException ex) {
            logError("Exception happen when log object. " + ex.getMessage());
            if (throwException) {
                throw ex;
            }
        }
    }

    public void logHttpRequest(HttpUriRequest request) {
        try {
            this.logHttpRequest(request, false);
        } catch (IOException ex) {
            //do nothing, eat the exception
        }
    }

    public void logHttpRequest(HttpUriRequest request, boolean throwException) throws IOException {
        try {
            logInfo("*** EXECUTING " + request.getMethod());
            logInfo("*** URI: " + request.getURI());
            String headers = "*** HEADERS: [";
            for (Header h : request.getAllHeaders()) {
                if (headers.length() > "*** HEADERS: [".length()) {
                    headers += ", ";
                }
                headers += h.getName() + ":" + h.getValue();
            }
            headers += "]";
            logInfo(headers);
            if (request.getParams().getParameter(ConnRouteParams.DEFAULT_PROXY) != null) {
                logInfo("*** Proxy: " + request.getParams().getParameter(ConnRouteParams.DEFAULT_PROXY).toString());
            }
            if (request instanceof HttpEntityEnclosingRequest) {
                HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
                logInfo("*** BODY:");
                if (entity != null) {
                    logInfo(EntityUtils.toString(entity));
                }
            }
        } catch (IOException ex) {
            logError("Exception happen when log http uri request. " + ex.getMessage());
            if (throwException) {
                throw ex;
            }
        }
    }

    public void logHttpResponse(HttpResponse response, String responseBody) {
        logInfo("*** RECEIVE RESPONSE ");
        if (response != null) {
            String headers = "*** HEADERS: [";
            for (Header h : response.getAllHeaders()) {
                headers += h.getName() + ": " + h.getValue() + ", ";
            }
            headers = headers.substring(0, headers.length() - 2) + "]";
            logInfo(headers);
            logInfo("*** Status Code:" + response.getStatusLine().getStatusCode());
            logInfo("*** Body:");

            String contentType = null;
            if (response.getHeaders("Content-Type").length > 0) {
                contentType = response.getHeaders("Content-Type")[0].getValue();
            }

            if (contentType != null && contentType.contains("/json")) {
                try {
                    Object jsonObject = JSONObject.parse(responseBody);
                    logInfo(jsonObject.toString());
                } catch (JSONException ex) {
                    logInfo(responseBody);
                }
            } else {
                logInfo(responseBody);
            }
        } else {
            logInfo("null");
        }
    }
}
