package de.hft.stuttgart.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Id", "ParentId", "RepoTags", "RepoDigests", "Created", "Size", "VirtualSize", "Labels" })
public class Image {

	@JsonProperty("Id")
	private String id;
	@JsonProperty("ParentId")
	private String parentId;
	@JsonProperty("RepoTags")
	private List<String> repoTags = new ArrayList<String>();
	@JsonProperty("RepoDigests")
	private Object repoDigests;
	@JsonProperty("Created")
	private Integer created;
	@JsonProperty("Size")
	private Integer size;
	@JsonProperty("VirtualSize")
	private Integer virtualSize;
	@JsonProperty("Labels")
	private Labels labels;
	private String imageName;
	private String imageTag;

	/**
	 * 
	 * @return The id
	 */
	@JsonProperty("Id")
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The Id
	 */
	@JsonProperty("Id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The parentId
	 */
	@JsonProperty("ParentId")
	public String getParentId() {
		return parentId;
	}

	/**
	 * 
	 * @param parentId
	 *            The ParentId
	 */
	@JsonProperty("ParentId")
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * @return The repoTags
	 */
	@JsonProperty("RepoTags")
	public List<String> getRepoTags() {
		return repoTags;
	}

	/**
	 * 
	 * @param repoTags
	 *            The RepoTags
	 */
	@JsonProperty("RepoTags")
	public void setRepoTags(List<String> repoTags) {
		String temp = repoTags.get(0);

		int indexOfDoublePoint = temp.indexOf(":");

		imageName = temp.substring(0, indexOfDoublePoint);
		imageTag = temp.substring(indexOfDoublePoint + 1, temp.length());

		this.repoTags = repoTags;
	}

	/**
	 * 
	 * @return The repoDigests
	 */
	@JsonProperty("RepoDigests")
	public Object getRepoDigests() {
		return repoDigests;
	}

	/**
	 * 
	 * @param repoDigests
	 *            The RepoDigests
	 */
	@JsonProperty("RepoDigests")
	public void setRepoDigests(Object repoDigests) {
		this.repoDigests = repoDigests;
	}

	/**
	 * 
	 * @return The created
	 */
	@JsonProperty("Created")
	public Integer getCreated() {
		return created;
	}

	/**
	 * 
	 * @param created
	 *            The Created
	 */
	@JsonProperty("Created")
	public void setCreated(Integer created) {
		this.created = created;
	}

	/**
	 * 
	 * @return The size
	 */
	@JsonProperty("Size")
	public Integer getSize() {
		return size;
	}

	/**
	 * 
	 * @param size
	 *            The Size
	 */
	@JsonProperty("Size")
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * 
	 * @return The virtualSize
	 */
	@JsonProperty("VirtualSize")
	public Integer getVirtualSize() {
		return virtualSize;
	}

	/**
	 * 
	 * @param virtualSize
	 *            The VirtualSize
	 */
	@JsonProperty("VirtualSize")
	public void setVirtualSize(Integer virtualSize) {
		this.virtualSize = virtualSize;
	}

	/**
	 * 
	 * @return The labels
	 */
	@JsonProperty("Labels")
	public Labels getLabels() {
		return labels;
	}

	/**
	 * 
	 * @param labels
	 *            The Labels
	 */
	@JsonProperty("Labels")
	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageTag() {
		return imageTag;
	}

	public void setImageTag(String imageTag) {
		this.imageTag = imageTag;
	}

}
