package bot.webordersapi;

import bot.webordersapi.models.AuthorisationRequest;
import bot.webordersapi.models.CreateOrder;
import bot.webordersapi.models.Order;
import bot.webordersapi.models.OrderCancel;
import bot.webordersapi.models.PhoneNumber;
import bot.webordersapi.models.Registration;
import bot.webordersapi.models.response.AuthorizationResponse;
import bot.webordersapi.models.response.CostResponse;
import bot.webordersapi.models.response.CreateOrderResponse;
import bot.webordersapi.models.response.ErrorResponse;
import bot.webordersapi.models.response.OrderCancelResponse;

public interface TaxiOrders {
	
	public CostResponse calculateCost(Order order, String authorizBasic);
	
	public ErrorResponse registration(PhoneNumber number);
	
	public ErrorResponse registrationConfirm(Registration registration);
	
	public AuthorizationResponse authorize(AuthorisationRequest request);
	
	public CreateOrderResponse createOrder(CreateOrder order, String authorizBasic); 
	
	public OrderCancelResponse cancelOrder(OrderCancel orderCancel , String authorizBasic);

	
}
