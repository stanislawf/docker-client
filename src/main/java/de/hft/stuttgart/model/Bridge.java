package de.hft.stuttgart.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "IPAMConfig", "Links", "Aliases", "NetworkID", "EndpointID", "Gateway", "IPAddress", "IPPrefixLen",
	"IPv6Gateway", "GlobalIPv6Address", "GlobalIPv6PrefixLen", "MacAddress" })
public class Bridge {

	@JsonProperty("IPAMConfig")
	private Object iPAMConfig;
	@JsonProperty("Links")
	private Object links;
	@JsonProperty("Aliases")
	private Object aliases;
	@JsonProperty("NetworkID")
	private String networkID;
	@JsonProperty("EndpointID")
	private String endpointID;
	@JsonProperty("Gateway")
	private String gateway;
	@JsonProperty("IPAddress")
	private String iPAddress;
	@JsonProperty("IPPrefixLen")
	private Integer iPPrefixLen;
	@JsonProperty("IPv6Gateway")
	private String iPv6Gateway;
	@JsonProperty("GlobalIPv6Address")
	private String globalIPv6Address;
	@JsonProperty("GlobalIPv6PrefixLen")
	private Integer globalIPv6PrefixLen;
	@JsonProperty("MacAddress")
	private String macAddress;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("IPAMConfig")
	public Object getIPAMConfig() {
		return iPAMConfig;
	}

	@JsonProperty("IPAMConfig")
	public void setIPAMConfig(Object iPAMConfig) {
		this.iPAMConfig = iPAMConfig;
	}

	@JsonProperty("Links")
	public Object getLinks() {
		return links;
	}

	@JsonProperty("Links")
	public void setLinks(Object links) {
		this.links = links;
	}

	@JsonProperty("Aliases")
	public Object getAliases() {
		return aliases;
	}

	@JsonProperty("Aliases")
	public void setAliases(Object aliases) {
		this.aliases = aliases;
	}

	@JsonProperty("NetworkID")
	public String getNetworkID() {
		return networkID;
	}

	@JsonProperty("NetworkID")
	public void setNetworkID(String networkID) {
		this.networkID = networkID;
	}

	@JsonProperty("EndpointID")
	public String getEndpointID() {
		return endpointID;
	}

	@JsonProperty("EndpointID")
	public void setEndpointID(String endpointID) {
		this.endpointID = endpointID;
	}

	@JsonProperty("Gateway")
	public String getGateway() {
		return gateway;
	}

	@JsonProperty("Gateway")
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	@JsonProperty("IPAddress")
	public String getIPAddress() {
		return iPAddress;
	}

	@JsonProperty("IPAddress")
	public void setIPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}

	@JsonProperty("IPPrefixLen")
	public Integer getIPPrefixLen() {
		return iPPrefixLen;
	}

	@JsonProperty("IPPrefixLen")
	public void setIPPrefixLen(Integer iPPrefixLen) {
		this.iPPrefixLen = iPPrefixLen;
	}

	@JsonProperty("IPv6Gateway")
	public String getIPv6Gateway() {
		return iPv6Gateway;
	}

	@JsonProperty("IPv6Gateway")
	public void setIPv6Gateway(String iPv6Gateway) {
		this.iPv6Gateway = iPv6Gateway;
	}

	@JsonProperty("GlobalIPv6Address")
	public String getGlobalIPv6Address() {
		return globalIPv6Address;
	}

	@JsonProperty("GlobalIPv6Address")
	public void setGlobalIPv6Address(String globalIPv6Address) {
		this.globalIPv6Address = globalIPv6Address;
	}

	@JsonProperty("GlobalIPv6PrefixLen")
	public Integer getGlobalIPv6PrefixLen() {
		return globalIPv6PrefixLen;
	}

	@JsonProperty("GlobalIPv6PrefixLen")
	public void setGlobalIPv6PrefixLen(Integer globalIPv6PrefixLen) {
		this.globalIPv6PrefixLen = globalIPv6PrefixLen;
	}

	@JsonProperty("MacAddress")
	public String getMacAddress() {
		return macAddress;
	}

	@JsonProperty("MacAddress")
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
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
