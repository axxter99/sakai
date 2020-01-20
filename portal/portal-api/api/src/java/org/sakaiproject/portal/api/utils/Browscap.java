package org.sakaiproject.portal.api.utils;
/**********************************************************************************
 * $URL: $
 * $Id: $
 ***********************************************************************************
 *
 * Copyright (c) 2020 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.opensource.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/
import java.util.List;

import org.sakaiproject.portal.api.model.Host;

/**
 * @author dhorwitz
 *
 */
public interface Browscap {
	
	/**
	 * @param host
	 * @return
	 */
	public Host getBrowcap(String host);
	
	/**
	 * @param host
	 * @return
	 */
	public List<Host> getBrowcap(List<String> host);

}
