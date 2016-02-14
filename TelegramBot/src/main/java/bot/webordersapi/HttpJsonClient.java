package bot.webordersapi;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class HttpJsonClient {

	public static String postToURL(String url, String msg ,String autorize, String addId) {
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost(url);
			StringEntity entity = new StringEntity(msg);
			postRequest.addHeader("content-type", "application/json");
			
			if(autorize != null)
				postRequest.addHeader("Authorization", "");
			
			if(addId != null)
				postRequest.addHeader("X-WO-API-APP-ID", "");
			
			postRequest.setEntity(entity);
			HttpResponse response = client.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println("ya otvet " + response);
				System.out.println(EntityUtils.toString(response.getEntity(),
						"UTF-8"));
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getToURL(String url, String msg, String autorize) {
		try(CloseableHttpClient client = HttpClientBuilder.create().build()) {
			
			HttpGet get = new HttpGet(url);
			get.addHeader("content-type", "application/json");
			
			if(autorize != null)
				get.addHeader("Authorization", "");
			
			if(msg != null) {
				StringEntity entity = new StringEntity(msg);
				//set params
			}
			HttpResponse response = client.execute(get);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				System.out.println("ya otvet " + response);
				System.out.println(EntityUtils.toString(response.getEntity(),
						"UTF-8"));
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
