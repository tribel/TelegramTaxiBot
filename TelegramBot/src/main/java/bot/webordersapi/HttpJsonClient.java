package bot.webordersapi;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class HttpJsonClient {

	private static final String APPLICATION_ID = "";
	
	public static String postToURL(String url, String msg ,String autorize, boolean addId) {
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

			HttpPost postRequest = new HttpPost(url);
			StringEntity entity = new StringEntity(msg);
			postRequest.addHeader("content-type", "application/json");
			
			if(autorize != null)
				postRequest.addHeader("Authorization", "Basic " + autorize);
			
			if(addId == true)
				postRequest.addHeader("X-WO-API-APP-ID", APPLICATION_ID);
			
			postRequest.setEntity(entity);
			HttpResponse response = client.execute(postRequest);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200 && statusCode != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("USPEH" + json);
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
				get.addHeader("Authorization", "Basic " + autorize);
			
			if(msg != null) {
				StringEntity entity = new StringEntity(msg);
				//set params
			}
			HttpResponse response = client.execute(get);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200 || statusCode != 201) {
				System.out.println("ya otvet " + response);
				System.out.println(EntityUtils.toString(response.getEntity(),
						"UTF-8"));
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			String json = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("USPEH" + json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
