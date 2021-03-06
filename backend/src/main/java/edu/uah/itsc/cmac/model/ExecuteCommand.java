/**
 * 
 */
package edu.uah.itsc.cmac.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author sshrestha
 * 
 */
public class ExecuteCommand implements JSONCommunication {
	private final String	userName;
	private final String	userEmail;
	private final String	bucketName;
	private final String	repoName;
	private final String	fileName;
	private final String	comment;
	private final String	accessKey;
	private final String	secretKey;
	private final String	repoOwner;
	private final String	largeBucketName;
	private final boolean	isSharedRepo;

	/**
	 * Builder class for ExecuteCommand
	 * 
	 * @author sshrestha
	 * 
	 */
	public static class Builder {
		private final String	bucketName;
		private final String	repoName;
		private final String	fileName;

		private String			userName		= "cwb";
		private String			userEmail		= "cwb@itsc.uah.edu";
		private String			comment			= "Execute a program";
		private String			accessKey;
		private String			secretKey;
		private String			repoOwner;
		private String			largeBucketName;
		private boolean			isSharedRepo	= false;

		public Builder(String bucketName, String repoName, String fileName) {
			this.bucketName = bucketName;
			this.repoName = repoName;
			this.fileName = fileName;
		}

		public Builder mail(String userEmail) {
			this.userEmail = userEmail;
			return this;
		}

		public Builder name(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder comment(String comment) {
			this.comment = comment;
			return this;
		}

		public Builder secretKey(String secretKey) {
			this.secretKey = secretKey;
			return this;
		}

		public Builder accessKey(String accessKey) {
			this.accessKey = accessKey;
			return this;
		}

		public Builder repoOwner(String repoOwner) {
			this.repoOwner = repoOwner;
			return this;
		}

		public Builder shared(boolean isSharedRepo) {
			this.isSharedRepo = isSharedRepo;
			return this;
		}

		public Builder largeBucketName(String largeBucketName) {
			this.largeBucketName = largeBucketName;
			return this;
		}

		public ExecuteCommand build() {
			return new ExecuteCommand(this);
		}

	}

	private ExecuteCommand(Builder builder) {
		userName = builder.userName;
		userEmail = builder.userEmail;
		bucketName = builder.bucketName;
		repoName = builder.repoName;
		fileName = builder.fileName;
		comment = builder.comment;
		isSharedRepo = builder.isSharedRepo;
		accessKey = builder.accessKey;
		secretKey = builder.secretKey;
		repoOwner = builder.repoOwner;
		largeBucketName = builder.largeBucketName;
	}

	public String toJSONString() {
		return toJSON().toString();
	}

	public JSONObject toJSON() {
		JSONObject jsonECmd = new JSONObject();
		try {
			jsonECmd.put("userName", userName).put("userEmail", userEmail).put("bucketName", bucketName)
				.put("repoName", repoName).put("fileName", fileName).put("comment", comment)
				.put("repoOwner", repoOwner).put("isSharedRepo", isSharedRepo).put("accessKey", accessKey)
				.put("secretKey", secretKey).put("largeBucketName", largeBucketName);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonECmd;
	}

	public static Object toObject(String jsonString) {
		if (jsonString == null || jsonString.isEmpty())
			return null;
		String userName = null;
		String userEmail = null;
		String bucketName = null;
		String repoName = null;
		String fileName = null;
		String comment = null;
		String accessKey = null;
		String secretKey = null;
		String repoOwner = null;
		String largeBucketName = null;
		boolean isSharedRepo = false;

		try {
			JSONObject jsonEcmd = new JSONObject(jsonString);
			userName = jsonEcmd.getString("userName");
			userEmail = jsonEcmd.getString("userEmail");
			bucketName = jsonEcmd.getString("bucketName");
			repoName = jsonEcmd.getString("repoName");
			fileName = jsonEcmd.getString("fileName");
			comment = jsonEcmd.getString("comment");
			isSharedRepo = jsonEcmd.getBoolean("isSharedRepo");
			accessKey = jsonEcmd.getString("accessKey");
			secretKey = jsonEcmd.getString("secretKey");
			repoOwner = jsonEcmd.getString("repoOwner");
			largeBucketName = jsonEcmd.getString("largeBucketName");

		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		ExecuteCommand eCmd = new ExecuteCommand.Builder(bucketName, repoName, fileName).mail(userEmail).name(userName)
			.comment(comment).repoOwner(repoOwner).shared(isSharedRepo).accessKey(accessKey).secretKey(secretKey)
			.largeBucketName(largeBucketName).build();
		return eCmd;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getBucketName() {
		return bucketName;
	}

	public String getRepoName() {
		return repoName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getComment() {
		return comment;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public boolean isSharedRepo() {
		return isSharedRepo;
	}

	public String getRepoOwner() {
		return repoOwner;
	}

	public String getLargeBucketName() {
		return null;
	}

}
