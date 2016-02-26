package bot.webordersapi;

import bot.webordersapi.models.AuthorisationRequest;
import bot.webordersapi.models.Order;
import bot.webordersapi.models.PhoneNumber;
import bot.webordersapi.models.Registration;
import bot.webordersapi.models.response.AuthorizationResponse;
import bot.webordersapi.models.response.CostResponse;
import bot.webordersapi.models.response.ErrorResponse;

public interface TaxiOrders {
	
	public CostResponse calculateCost(Order order, String authorizBasic);
	
	public ErrorResponse registration(PhoneNumber number);
	
	public ErrorResponse registrationConfirm(Registration registration);
	
	public AuthorizationResponse authorize(AuthorisationRequest request);

	
}
