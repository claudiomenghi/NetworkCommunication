package se.gu;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class MissionSender {

	public void send(String mission, String ip, String port) throws ClientProtocolException, IOException {

		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://" + ip + ":" + port);

		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		String sentMission = mission;
		params.add(new BasicNameValuePair("mission", sentMission));

		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HttpResponse response;
		response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
			InputStream instram = entity.getContent();
			instram.close();

		}

	}

}
