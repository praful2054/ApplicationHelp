package com.project.user.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.user.model.Data;

@RestController
@RequestMapping("/create")
public class UserController {

	@PostMapping("/{filename}")
	public void createFile(@RequestBody Data data, @PathVariable("filename") String name) throws ClientProtocolException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

		CloseableHttpClient client = getCloseableHttpClient();
		
		HttpPost httpPost = new HttpPost("https://test.net/test1?name=" + name);
		HttpEntity entity = new StringEntity(data.toString());
		
		httpPost.setEntity(entity);

		CloseableHttpResponse response = client.execute(httpPost);
		
		if(response.getStatusLine().getStatusCode() == 200)
		{
		    String json = EntityUtils.toString(response.getEntity());
		    System.out.println(json);
		}else
			System.out.println("Not got Successfull Response");
		
	}
	
	public static CloseableHttpClient getCloseableHttpClient()
	{
	    CloseableHttpClient httpClient = null;
	    try {
	        httpClient = HttpClients.custom().
	                setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).
	                setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy()
	                {
	                    public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException
	                    {
	                        return true;
	                    }

						@Override
						public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
								throws java.security.cert.CertificateException {
							// TODO Auto-generated method stub
							return true;
						}
	                }).build()).build();
	    } catch (KeyManagementException e) {
	        System.out.println("KeyManagementException in creating http client instance"+ e);
	    } catch (NoSuchAlgorithmException e) {
	    	System.out.println("NoSuchAlgorithmException in creating http client instance"+ e);
	    } catch (KeyStoreException e) {
	    	System.out.println("KeyStoreException in creating http client instance"+e);
	    }
	    return httpClient;
	}

}
