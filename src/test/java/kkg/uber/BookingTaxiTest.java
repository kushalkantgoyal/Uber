package kkg.uber;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import kkg.uber.Entity.BookingEntity;
import kkg.uber.Entity.TaxiEntity;
import kkg.uber.Entity.TaxiTypeEntity;
import kkg.uber.Entity.UserEntity;
import kkg.uber.Util.Result;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BookingTaxiTest {
	private static String BASE_URI ="http://localhost:8080/Uber";
	private static UserEntity user = null;
	private static TaxiEntity taxi1 = null;
	private static TaxiEntity taxi2 = null;
	private static TaxiEntity taxi3 = null;
	private static TaxiEntity taxi4 = null;
	private static BookingEntity booking = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		user = new UserEntity("kushal", "goyal", "kk@gmail.com", 9460413925L);
		//Adding a user
		RestTemplate restTemplate = new RestTemplate();
		Result result = restTemplate.postForObject(BASE_URI+"/user/create", user, Result.class);

		user = mapper.convertValue(result.getData(), UserEntity.class);
		assertNotNull("Adding a new user",user);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		//Removing a user
		String params = "id="+user.getId();
	    restTemplate.delete(BASE_URI+"/user/delete?"+params);
	}

	@Before
	public void setUp() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		TaxiTypeEntity taxiType = new TaxiTypeEntity();
		taxiType.setId(1L);	//Seadan = 1L
		
		//Adding taxis
		taxi1 = new TaxiEntity("RJ14VD2324", "AbcDriver", 9460413921L,
				"abc@gmail.com", taxiType, 10.0, 10.0, false);
		Result result = restTemplate.postForObject(BASE_URI+"/taxi/create", taxi1, Result.class);
		assertTrue("Adding a Sedan taxi",result.getSuccess());
		taxi1 = mapper.convertValue(result.getData(), TaxiEntity.class);
		
		taxi2 = new TaxiEntity("RJ14VD2325", "AbcdDriver", 9460413922L,
				"abcd@gmail.com", taxiType, 8.0, 8.0, false);
		result = restTemplate.postForObject(BASE_URI+"/taxi/create", taxi2, Result.class);
		assertTrue("Adding another Sedan", result.getSuccess());
		taxi2 = mapper.convertValue(result.getData(), TaxiEntity.class);
		
		taxiType.setId(2L);		//PINK = 2L
		taxi3 = new TaxiEntity("RJ14VD2326", "AbcddDriver", 9460413923L,
				"abcde@gmail.com", taxiType, 9.0, 9.0, false);
		result = restTemplate.postForObject(BASE_URI+"/taxi/create", taxi3, Result.class);
		assertTrue("Adding a Pink taxi", result.getSuccess());
		taxi3 = mapper.convertValue(result.getData(), TaxiEntity.class);
		
		taxi4 = new TaxiEntity("RJ14VD2327", "ADriver", 9460413924L,
				"abcdef@gmail.com", taxiType, 6.0, 6.0, false);
		result = restTemplate.postForObject(BASE_URI+"/taxi/create", taxi4, Result.class);
		assertTrue("Adding another Pink taxi", result.getSuccess());
		taxi4 = mapper.convertValue(result.getData(), TaxiEntity.class);
	}

	@After
	public void tearDown() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String params ="";
		//Removing Booking
		if(booking!=null){
			params = "id="+booking.getId();
			restTemplate.delete(BASE_URI+"/booking/delete?"+params, null, Result.class);
		}
	    
		//Removing taxis
		params = "id="+taxi1.getId();
	    restTemplate.delete(BASE_URI+"/taxi/delete?"+params);
	    params = "id="+taxi2.getId();
	    restTemplate.delete(BASE_URI+"/taxi/delete?"+params);
	    params = "id="+taxi3.getId();
	    restTemplate.delete(BASE_URI+"/taxi/delete?"+params);
	    params = "id="+taxi4.getId();
	    restTemplate.delete(BASE_URI+"/taxi/delete?"+params);
	}

	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		//Booking a Sedan
		String params = "userId="+user.getId()+"&userLat=1&userLon=1&taxiType=SEDAN";
		Result result = restTemplate.postForObject(BASE_URI+"/booking/bookTaxi?"+params, null, Result.class);
		assertTrue("Book a Sedan ", result.getSuccess());
		booking = mapper.convertValue(result.getData(), BookingEntity.class);
		
		//Starting the Ride
		params = "bookingId="+booking.getId()+"&startLat=2&startLon=2";
		result = restTemplate.postForObject(BASE_URI+"/booking/startRide?"+params, null, Result.class);
		assertTrue("Start a Ride ", result.getSuccess());
		booking = mapper.convertValue(result.getData(), BookingEntity.class);
		assertNotNull("Start Time not null",booking.getStartTime() );
		
		//Ending the Ride
		params = "bookingId="+booking.getId()+"&endLat=2&endLon=2";
		result = restTemplate.postForObject(BASE_URI+"/booking/endRide?"+params, null, Result.class);
		assertTrue("End a Ride ", result.getSuccess());
		booking = mapper.convertValue(result.getData(), BookingEntity.class);
		assertNotNull("End Time not null",booking.getEndTime() );
		
	}

}
