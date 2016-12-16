package de.hft.client.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Name", "Source", "Destination", "Driver", "Mode", "RW", "Propagation" })
public class Mount {

	@JsonProperty("Name")
	private String name;
	@JsonProperty("Source")
	private String source;
	@JsonProperty("Destination")
	private String destination;
	@JsonProperty("Driver")
	private String driver;
	@JsonProperty("Mode")
	private String mode;
	@JsonProperty("RW")
	private Boolean rW;
	@JsonProperty("Propagation")
	private String propagation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	@JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("Source")
	public String getSource() {
		return source;
	}

	@JsonProperty("Source")
	public void setSource(String source) {
		this.source = source;
	}

	@JsonProperty("Destination")
	public String getDestination() {
		return destination;
	}

	@JsonProperty("Destination")
	public void setDestination(String destination) {
		this.destination = destination;
	}

	@JsonProperty("Driver")
	public String getDriver() {
		return driver;
	}

	@JsonProperty("Driver")
	public void setDriver(String driver) {
		this.driver = driver;
	}

	@JsonProperty("Mode")
	public String getMode() {
		return mode;
	}

	@JsonProperty("Mode")
	public void setMode(String mode) {
		this.mode = mode;
	}

	@JsonProperty("RW")
	public Boolean getRW() {
		return rW;
	}

	@JsonProperty("RW")
	public void setRW(Boolean rW) {
		this.rW = rW;
	}

	@JsonProperty("Propagation")
	public String getPropagation() {
		return propagation;
	}

	@JsonProperty("Propagation")
	public void setPropagation(String propagation) {
		this.propagation = propagation;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
