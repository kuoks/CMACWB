/**
 * 
 */
package edu.uah.itsc.cmac.portal;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author sshrestha
 * 
 */
public class Workflow {
	private String	title;
	private String	description;
	private String	creator;
	private String	submittor;
	private String	path;
	private String	keywords;
	private String	programs;
	private String	resourceUsageInfo;
	private Date	startTime;
	private Date	endTime;
	private boolean	isShared;
	private String	allowCloneTo;

	public Workflow() {
		super();
	}

	public Workflow(String title, String description, String path, String keywords, boolean isShared) {
		super();
		this.title = title;
		this.description = description;
		this.path = path;
		this.keywords = keywords;
		this.isShared = isShared;
	}

	public Workflow(String title, String description, String keywords) {
		super();
		this.title = title;
		this.description = description;
		this.keywords = keywords;
	}

	public JSONObject getJSON() throws JSONException {
		JSONObject jsonData = new JSONObject();
		jsonData.put("title", title);
		jsonData.put("type", "workflow");
		jsonData.put("field_is_shared", isShared ? getComplexObject("value", "1") : new JSONArray());
		if (description != null && !description.isEmpty())
			jsonData.put("body", getComplexObject("value", description));
		if (creator != null && !creator.isEmpty())
			jsonData.put("field_creator", getComplexObject("uid", creator));
		if (submittor != null && !submittor.isEmpty())
			jsonData.put("field_submittor", getComplexObject("uid", submittor));
		if (path != null && !path.isEmpty())
			jsonData.put("field_could_path", getComplexObject("value", path));
		if (keywords != null && !keywords.isEmpty())
			jsonData.put("field_keywords", new JSONObject("{'und':'" + keywords + "'}"));
		if (allowCloneTo != null && !allowCloneTo.isEmpty())
			jsonData.put("field_allow_clone_to", getComplexObjectArray(null, allowCloneTo.split(",")));
		else
			jsonData.put("field_allow_clone_to", new JSONObject("{'und':'_none'}"));
		// jsonData.put("field_keywords", getComplexObject(keywords));
		// jsonData.put("field_tags", getComplexObject(keywords));
		// jsonData.put("field_start_time",
		// getComplexObject(startTime.toString()));
		// jsonData.put("field_end_time", getComplexObject(description));
		if (resourceUsageInfo != null && !resourceUsageInfo.isEmpty())
			jsonData.put("field_resource_usage_info", getComplexObject("value", resourceUsageInfo));
		return jsonData;
	}

	private JSONObject getComplexObject(String key, String value) throws JSONException {

		JSONObject undObject = new JSONObject();
		JSONArray undArray = new JSONArray();
		JSONObject undArrayObject = new JSONObject();

		/*
		 * This method will return a JSONObject similar to "field_is_shared": { "und": [ { "value": "1" } ] }
		 */

		undArrayObject.put(key, value);
		undArray.put(undArrayObject);
		undObject.put("und", undArray);
		return undObject;

	}

	private JSONObject getComplexObjectArray(String key, String[] values) throws JSONException {
		JSONObject undObject = new JSONObject();
		JSONArray undArray = new JSONArray();

		/*
		 * This method will return a JSONObject similar to "field_is_shared": { "und": [ { "value": "1" } ] }
		 */
		for (String value : values) {
			if (key != null) {
				JSONObject undArrayObject = new JSONObject();
				undArrayObject.put(key, value);
				undArray.put(undArrayObject);
			}
			else
				undArray.put(value);
		}

		undObject.put("und", undArray);
		return undObject;

	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the submittor
	 */
	public String getSubmittor() {
		return submittor;
	}

	/**
	 * @param submittor
	 *            the submittor to set
	 */
	public void setSubmittor(String submittor) {
		this.submittor = submittor;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the programs
	 */
	public String getPrograms() {
		return programs;
	}

	/**
	 * @param programs
	 *            the programs to set
	 */
	public void setPrograms(String programs) {
		this.programs = programs;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the resourceUsageInfo
	 */
	public String getResourceUsageInfo() {
		return resourceUsageInfo;
	}

	/**
	 * @param resourceUsageInfo
	 *            the resourceUsageInfo to set
	 */
	public void setResourceUsageInfo(String resourceUsageInfo) {
		this.resourceUsageInfo = resourceUsageInfo;
	}

	/**
	 * @return the isShared
	 */
	public boolean isShared() {
		return isShared;
	}

	/**
	 * @param isShared
	 *            the isShared to set
	 */
	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}

	public String getBucket() {
		if (path == null || path.isEmpty())
			return null;

		String[] parts = path.split("/");
		return parts[1];
	}

	public String getWorkflowName() {
		if (path == null || path.isEmpty())
			return null;

		String[] parts = path.split("/");
		return parts[parts.length - 1];
	}

	public String getAllowCloneTo() {
		return allowCloneTo;
	}

	public void setAllowCloneTo(String allowCloneTo) {
		this.allowCloneTo = allowCloneTo;
	}

}
