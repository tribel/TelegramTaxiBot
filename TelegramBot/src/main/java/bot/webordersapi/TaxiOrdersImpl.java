package bot.webordersapi;

import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bot.webordersapi.models.AuthorisationRequest;
import bot.webordersapi.models.CreateOrder;
import bot.webordersapi.models.Order;
import bot.webordersapi.models.OrderCancel;
import bot.webordersapi.models.PhoneNumber;
import bot.webordersapi.models.ProfileRefresh;
import bot.webordersapi.models.Registration;
import bot.webordersapi.models.response.AuthorizationResponse;
import bot.webordersapi.models.response.CostResponse;
import bot.webordersapi.models.response.CreateOrderResponse;
import bot.webordersapi.models.response.ErrorResponse;
import bot.webordersapi.models.response.OrderCancelResponse;

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
		System.out.println(json);
		ErrorResponse errorResponse = new Gson().fromJson(json, ErrorResponse.class);
		System.out.println(errorResponse);
		System.out.println("skaly sexy");
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

	@Override
	public CreateOrderResponse createOrder(CreateOrder order, String authorizBasic) {
		String json = HttpJsonClient.postToURL(url + "weborders", gson.toJson(order), authorizBasic, true);
		CreateOrderResponse orderResponse = new Gson().fromJson(json, CreateOrderResponse.class);
		return orderResponse;
	}

	@Override
	public OrderCancelResponse cancelOrder(OrderCancel orderCancel, String authorizBasic) {
		String json = HttpJsonClient.postToURL(url + "weborders/cancel/a17a327fbd754d54bc91ba6af716f8c6", //orderCancel.getUid(), 
												null, authorizBasic, false);
		OrderCancelResponse cancelResponse = new Gson().fromJson(json, OrderCancelResponse.class);
		return cancelResponse;
	}

	@Override
	public int refreshProfile(ProfileRefresh profileRefresh, String authorizBasic) {
		return HttpJsonClient.putToURL(url + "clients/profile", gson.toJson(profileRefresh), authorizBasic, false);
	}
	

}
