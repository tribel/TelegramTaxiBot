package bot.webordersapi;

import com.google.gson.Gson;

import bot.webordersapi.models.Order;
import bot.webordersapi.models.response.CostResponse;

public class TaxiOrdersImpl implements TaxiOrders{

	private String url = "http://<ip-addres>:<port>/api/" ;
	
	@Override
	public CostResponse calculateCost(Order order) {
		String json = HttpJsonClient.postToURL(url + "weborders/cost", new Gson().toJson(order));
		CostResponse costResponse = new Gson().fromJson(json, CostResponse.class);
		return costResponse;
	}

	
}
