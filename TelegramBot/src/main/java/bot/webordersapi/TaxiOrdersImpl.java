package bot.webordersapi;

import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bot.webordersapi.models.AuthorisationRequest;
import bot.webordersapi.models.Order;
import bot.webordersapi.models.PhoneNumber;
import bot.webordersapi.models.Registration;
import bot.webordersapi.models.response.AuthorizationResponse;
import bot.webordersapi.models.response.CostResponse;
import bot.webordersapi.models.response.ErrorResponse;

@Named
public class TaxiOrdersImpl implements TaxiOrders{

	private String url = "" ;
	private Gson gson = new GsonBuilder().serializeNulls().create();
	
	@Override
	public CostResponse calculateCost(Order order, String authorizBasic) {
		String json = HttpJsonClient.postToURL(url + "weborders/cost", gson.toJson(order), authorizBasic, true);
		CostResponse costResponse = new Gson().fromJson(json, CostResponse.class);
		return costResponse;
	}

	@Override
	public ErrorResponse registration(PhoneNumber number) {
		String json = HttpJsonClient.postToURL(url + "account/register/sendConfirmCode", gson.toJson(number),
												null, true);
		ErrorResponse errorResponse = new Gson().fromJson(json, ErrorResponse.class);
		return errorResponse;
	}

	@Override
	public ErrorResponse registrationConfirm(Registration registration) {
		String json = HttpJsonClient.postToURL(url + "account/register",gson.toJson(registration),
												null, false);
		ErrorResponse errorResponse = new Gson().fromJson(json, ErrorResponse.class);
		return errorResponse;
	}

	@Override
	public AuthorizationResponse authorize(AuthorisationRequest request) {
		String json  = HttpJsonClient.postToURL(url + "account", gson.toJson(request), null, true);
		AuthorizationResponse aResponse = new Gson().fromJson(json, AuthorizationResponse.class);
		return aResponse;
	}

}
