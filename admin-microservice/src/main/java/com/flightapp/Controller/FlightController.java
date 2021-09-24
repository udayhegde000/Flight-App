/**
 * 
 */
package com.flightapp.Controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flightapp.Service.FlightService;
import com.flightapp.model.Flight;

/**
 * @author udayh
 *
 */
@RestController
@RequestMapping("flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
    LoadBalancerClient loadBalancerClient;

	@GetMapping("/all")
	public List<Flight> findAll() {
		return flightService.findAll();
	}

	@GetMapping("/scheduled")
	public List<Flight> findScheduledFlights() {
		return flightService.findScheduledFlights();
	}

	@GetMapping("/search")
	public List<Flight> search(@RequestParam(value = "airLine", required = false) String airLine,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to) {
		return flightService.search(airLine, from, to);
	}

	@PostMapping("/save")
	public Flight save(@RequestBody Flight flight) {
		return flightService.save(flight);
	}

	@PutMapping("/save/{flightId}")
	public Flight save(@PathVariable Long flightId, @RequestBody Flight flight) {
		return flightService.put(flightId, flight);
	}

	@DeleteMapping("/delete")
	public void Delete(@RequestBody Flight flight) {
		flightService.delete(flight);
	}

	/**
	 * Start this flight schedule
	 */
	@PutMapping("/{flightId}/start")
	public Flight startSchedule(@PathVariable Long flightId) {
		Flight startedFlight = null;
		try {

			Flight flight = flightService.getById(flightId);
			if (flight == null) {
				throw new Exception("Flight not found for flight id: " + flightId);
			} else {
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				HttpEntity<Flight> entity = new HttpEntity<Flight>(flight, headers);
				ResponseEntity<Object[]> responseEntity = restTemplate.exchange(
						"http://FLIGHT-SERVICE/trips/startTrips", HttpMethod.PUT, entity, Object[].class);

				flight.setScheduled(true);
				startedFlight = flightService.save(flight);
			}

		} catch (Exception e) {
			System.out.println("error occured while starting the schedule" + e);
			e.printStackTrace();
		}
		return startedFlight;
	}

	/**
	 * Stops this flight schedule
	 */
	@PutMapping("/{flightId}/stop")
	public Flight stopSchedule(@PathVariable Long flightId) {

		Flight startedFlight = null;
		try {

			Flight flight = flightService.getById(flightId);
			if (flight == null) {
				throw new Exception("Flight not found for flight id: " + flightId);
			} else {
				HttpHeaders headers = new HttpHeaders();
				ServiceInstance serviceinstance = loadBalancerClient.choose("FLIGHT-SERVICE");
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				HttpEntity<Flight> entity = new HttpEntity<Flight>(flight, headers);
				ResponseEntity<Object> responseEntity = restTemplate.exchange(
					"http://"+serviceinstance.getHost()+":"+serviceinstance.getPort()+"/trips/stopTrips", HttpMethod.PUT, entity, Object.class);

				flight.setScheduled(false);
				startedFlight = flightService.save(flight);
			}

		} catch (Exception e) {
			System.out.println("error occured while starting the schedule" + e);
			e.printStackTrace();
		}
		return startedFlight;

	}

}
