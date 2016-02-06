package bot.webordersapi;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpJsonClient {
	
	public static String postToURL(String url, String msg) {
		try (CloseableHttpClient client = HttpClientBuilder.create().build()){
			
			HttpPost postRequest = new HttpPost(url);
			StringEntity entity = new StringEntity(msg);
			postRequest.addHeader("content-type", "application/json");
			postRequest.setEntity(entity);
			
			HttpResponse response = client.execute(postRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
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
