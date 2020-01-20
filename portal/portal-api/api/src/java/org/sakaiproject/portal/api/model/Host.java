package org.sakaiproject.portal.api.model;
import lombok.Data;

@Data 
public class Host {

	private String host;
	private String browser;
	private String browserType;
	private String deviceType;
	private String platform;
	private Integer count;

}
