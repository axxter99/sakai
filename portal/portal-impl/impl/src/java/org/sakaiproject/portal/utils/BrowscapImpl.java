package org.sakaiproject.portal.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.sakaiproject.portal.api.model.Host;
import org.sakaiproject.portal.api.utils.Browscap;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;

import lombok.extern.slf4j.Slf4j;
@Slf4j

public class BrowscapImpl implements Browscap {
	
	private UserAgentParser parser;
	
	/**
	 * init
	 */
	public void init() {
		try {
			parser = new UserAgentService().loadParser();
		} catch (IOException | ParseException e) {
			log.warn(e.getLocalizedMessage(), e);
		}
		log.info("init");
	}

	@Override
	public Host getBrowcap(String host) {
		host = StringUtils.trimToNull(host);
		if (null == host) {
			log.warn("Null");
			return null;
		}
		Capabilities capabilities = parser.parse(host);
		Host h = new Host();
		h.setHost(host);
		h.setBrowser(capabilities.getBrowser());
		h.setBrowserType(capabilities.getBrowserType());
		h.setPlatform(capabilities.getPlatform());
		h.setDeviceType(capabilities.getDeviceType());
		return h;
	}

	@Override
	public List<Host> getBrowcap(List<String> host) {
		List<Host> hostList = new ArrayList<>();
		for (String string : host) {
			Host h = getBrowcap(string);
			if (null != h) {
				hostList.add(h);
			}
		}
		return hostList;
	}

}
