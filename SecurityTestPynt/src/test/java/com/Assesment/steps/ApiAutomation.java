package com.Assesment.steps;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiAutomation {

//	@Given("User gets the api response")
//	public void user_gets_the_api_response() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@When("the list is not empty")
//	public void the_list_is_not_empty() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("extracts ids")
//	public void extracts_ids() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("extracts tags")
//	public void extracts_tags() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
	
	
	String jsonString;
	Response res;

	@Given("User gets the api response")
	public void user_gets_the_api_response() throws IOException {
		System.out.println("API Automation Using JwayJSON\n");
		res = RestAssured.given().baseUri("https://petstore.swagger.io/v2/pet/findByStatus")
				.queryParam("status", "available").when().get();

	}

	@When("the list is not empty")
	public void the_list_is_not_empty()
			throws StreamReadException, DatabindException, MalformedURLException, IOException {
		jsonString = res.getBody().asString();
	}

	@Then("extracts ids")
	public void jwayjson_extractraction() {
		ArrayList<Long> ids = JsonPath.read(jsonString, "$[*].id");
		ArrayList<ArrayList<Object>> tags = JsonPath.parse(jsonString).read("$[*].tags");

		System.out.println("ids : " + ids + "\n");
		System.out.println("tags : " + tags + "\n\n");

	}

	@Then("extracts tags")
	public void objectMapper_extractraction() throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("in objectmapper");
		List<Automation> nodes = objectMapper.readValue(jsonString, new TypeReference<List<Automation>>() {
		});

//      Automation[] nodes = objectMapper.readValue(jsonString, Automation[].class);
		System.out.println("In object mapper");
		for (Automation node : nodes) {
			System.out.println("ID: " + node.getId() + ", Tags: " + node.getTags());
			for (Tag ele : node.getTags()) {
				System.out.println("ID: " + ele.getId() + ", Tags: " + ele.getName());
			}
		}
	}

}
