package com.eclipsesource.restHttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import android.util.Log;

/**
 * 
 * @author sergio
 *
 */
public class RestHttpClient {

	protected static final String TAG = "RestClientUtils";

	/**
	 * Sets the timeout until a connection is established. A value of zero means
	 * the timeout is not used. The default value is zero.
	 */
	private static final int CONNECTION_TIMEOUT = 15 * 1000; // ms

	/**
	 * Sets the default socket timeout (SO_TIMEOUT) in milliseconds which is the
	 * timeout for waiting for data. A timeout value of zero is interpreted as
	 * an infinite timeout. This value is used when no socket timeout is set in
	 * the method parameters.
	 */
	private static final int SOCKET_TIMEOUT = 15 * 1000;

	/**
	 * 
	 * @param webService
	 * @param headerRegistry
	 * @param httpMethod
	 * @param formContent
	 * @return
	 */
	public static final String getRequest(String webService, List<NameValuePair> keyValuePairs) {
		return queryRestUrlString(webService, keyValuePairs);
	}

	/**
	 * 
	 * @return
	 */
	private static final HttpClient basicHttpClient() {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParameters, SOCKET_TIMEOUT);
		HttpClient httpclient = new DefaultHttpClient(httpParameters);
		return httpclient;
	}

	/**
	 * 
	 * @param url
	 * @param formContent
	 * @param headerRegistry
	 * @return
	 */
	public static final String queryRestUrlString(String url, List<NameValuePair> keyValuePairs) {
		
		String result = "";
		String keyValueParams = "";

		// Validate Pairs
		if (keyValuePairs != null)
			keyValueParams = "?" + URLEncodedUtils.format(keyValuePairs, HTTP.UTF_8);

		Log.d(TAG, "GET PARAMS: " + keyValueParams);

		HttpClient httpclient = basicHttpClient();
		HttpRequestBase httpreq = new HttpGet(url + keyValueParams);

		// Force resquest in EN
		//httpreq.addHeader("Accept-Language", "EN");
		HttpResponse response;
		HttpContext localContext = new BasicHttpContext();

		try {
			response = httpclient.execute(httpreq, localContext);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = convertStreamToString(instream);
				instream.close();
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			Log.d("##########CONection TIME OUT#############",
					"VARIAVEL TRUEEEEEEE");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
