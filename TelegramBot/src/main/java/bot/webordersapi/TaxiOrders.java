package bot.webordersapi;

import bot.webordersapi.models.Order;
import bot.webordersapi.models.response.CostResponse;

public interface TaxiOrders {
	
	public CostResponse calculateCost(Order order);
	
}
