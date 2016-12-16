package de.hft.client.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Id", "Names", "Image", "ImageID", "Command", "Created", "Ports", "Labels", "State", "Status",
	"HostConfig", "NetworkSettings", "Mounts" })
public class Container {

	@JsonProperty("Id")
	private String id;
	@JsonProperty("Names")
	private List<String> names = null;
	@JsonProperty("Image")
	private String image;
	@JsonProperty("ImageID")
	private String imageID;
	@JsonProperty("Command")
	private String command;
	@JsonProperty("Created")
	private Integer created;
	@JsonProperty("Ports")
	private List<Object> ports = null;
	@JsonProperty("Labels")
	private Labels labels;
	@JsonProperty("State")
	private String state;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("HostConfig")
	private HostConfig hostConfig;
	@JsonProperty("NetworkSettings")
	private NetworkSettings networkSettings;
	@JsonProperty("Mounts")
	private List<Mount> mounts = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private String containerName;

	@JsonProperty("Id")
	public String getId() {
		return id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("Names")
	public List<String> getNames() {
		return names;
	}

	@JsonProperty("Names")
	public void setNames(List<String> names) {
		this.names = names;
		String temp = names.get(0);
		containerName = temp.substring(1, temp.length());
	}

	@JsonProperty("Image")
	public String getImage() {
		return image;
	}

	@JsonProperty("Image")
	public void setImage(String image) {
		this.image = image;
	}

	@JsonProperty("ImageID")
	public String getImageID() {
		return imageID;
	}

	@JsonProperty("ImageID")
	public void setImageID(String imageID) {
		this.imageID = imageID;
	}

	@JsonProperty("Command")
	public String getCommand() {
		return command;
	}

	@JsonProperty("Command")
	public void setCommand(String command) {
		this.command = command;
	}

	@JsonProperty("Created")
	public Integer getCreated() {
		return created;
	}

	@JsonProperty("Created")
	public void setCreated(Integer created) {
		this.created = created;
	}

	@JsonProperty("Ports")
	public List<Object> getPorts() {
		return ports;
	}

	@JsonProperty("Ports")
	public void setPorts(List<Object> ports) {
		this.ports = ports;
	}

	@JsonProperty("Labels")
	public Labels getLabels() {
		return labels;
	}

	@JsonProperty("Labels")
	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	@JsonProperty("State")
	public String getState() {
		return state;
	}

	@JsonProperty("State")
	public void setState(String state) {
		this.state = state;
	}

	@JsonProperty("Status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("Status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("HostConfig")
	public HostConfig getHostConfig() {
		return hostConfig;
	}

	@JsonProperty("HostConfig")
	public void setHostConfig(HostConfig hostConfig) {
		this.hostConfig = hostConfig;
	}

	@JsonProperty("NetworkSettings")
	public NetworkSettings getNetworkSettings() {
		return networkSettings;
	}

	@JsonProperty("NetworkSettings")
	public void setNetworkSettings(NetworkSettings networkSettings) {
		this.networkSettings = networkSettings;
	}

	@JsonProperty("Mounts")
	public List<Mount> getMounts() {
		return mounts;
	}

	@JsonProperty("Mounts")
	public void setMounts(List<Mount> mounts) {
		this.mounts = mounts;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

}
