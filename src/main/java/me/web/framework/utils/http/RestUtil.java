package me.web.framework.utils.http;

import me.web.framework.utils.log.LogUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRouteParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public abstract class RestUtil {

    public static String get(String uri) {
        return get(uri,null);
    }

    public static String get(String uri,Map<String,String> headers) {
        return get(uri, headers, null);
    }

    public static String get(String uri,Map<String,String> headers, HttpHost httpProxy) {
        HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
        GetMethod method = new GetMethod(uri);
        setHeaders(method,headers);
        return execute(httpClient, method, httpProxy);
    }


    public static String post(String uri, String requestBody) {
        return post(uri,requestBody,null);
    }

    public static String post(String uri, String requestBody,Map<String,String> headers) {
        return post(uri,requestBody,headers,null);
    }

    public static String post(String uri, String requestBody,Map<String,String> headers, HttpHost httpProxy) {
        HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
        PostMethod method = new PostMethod(uri);
        setHeaders(method,headers);
        method.setRequestBody(requestBody);
        return execute(httpClient, method, httpProxy);
    }

    public static String put(String uri,String requestBody) {
        return put(uri, requestBody, null);
    }

    public static String put(String uri,String requestBody,Map<String,String> headers) {
        return  put(uri,requestBody,headers,null);
    }

    public static String put(String uri,String requestBody,Map<String,String> headers, HttpHost httpProxy) {
        HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
        PutMethod method = new PutMethod(uri);
        setHeaders(method,headers);
        method.setRequestBody(requestBody);
        return execute(httpClient, method, httpProxy);
    }

    public String delete(String uri,String requestBody) {
        return null;
    }


    private static void setHeaders(HttpMethod method,Map<String,String> headers) {
        if(headers == null){
            return;
        }
        for(Map.Entry<String,String> entry : headers.entrySet()){
            method.setRequestHeader(entry.getKey(),entry.getValue());
        }
    }

    private static String execute(HttpClient httpClient, HttpMethod method, HttpHost httpProxy) {

        if(httpProxy != null){
            httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, httpProxy);
        }

        LogUtil.instance.logInfo("*** EXECUTING " + method.getName());
        LogUtil.instance.logInfo("*** URI: "+method.getPath());

        StringBuffer responseBody = new StringBuffer();
        try {
            int responseStatus = httpClient.executeMethod(method);
            if (responseStatus != 200) {
                throw new RuntimeException(
                        "Failed : HTTP error code : " + method.getStatusLine());
            }
            InputStream is = method.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }

        LogUtil.instance.logInfo("*** RECEIVE RESPONSE ");
        LogUtil.instance.logInfo("*** Body: " + responseBody);

        return responseBody.toString();
    }

}
