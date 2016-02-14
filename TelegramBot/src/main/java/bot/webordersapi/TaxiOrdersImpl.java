package bot.webordersapi;

import javax.inject.Named;

import com.google.gson.Gson;

import bot.webordersapi.models.Order;
import bot.webordersapi.models.response.CostResponse;

@Named
public class TaxiOrdersImpl implements TaxiOrders{

	private String url = "http://apk.taxi-*********/api/" ;
	
	@Override
	public CostResponse calculateCost(Order order) {
		String json = HttpJsonClient.postToURL(url + "weborders/cost", new Gson().toJson(order), null, null);
		CostResponse costResponse = new Gson().fromJson(json, CostResponse.class);
		return costResponse;
	}

	
}
