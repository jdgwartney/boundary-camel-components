// Copyright 2014 Boundary, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.boundary.camel.component.url;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.UriParam;

import com.boundary.camel.component.common.ServiceCheckBaseConfiguration;
import com.boundary.camel.component.ping.PingConfiguration;


public class UrlConfiguration extends ServiceCheckBaseConfiguration implements Cloneable {
	
	private final static int EMPTY_PORT = -1;
	private final static String EMPTY_PATH = "";
	private final static String EMPTY_QUERY = "";
	
	@UriParam
	private String scheme;
	@UriParam
	private String query;
	@UriParam
	private String requestMethod;
	
	URL url;
	private boolean followRedirects;
	
	public UrlConfiguration() {
		scheme = "http";
		followRedirects=true;
		requestMethod = "GET";

	}
	
	public UrlConfiguration(URL url) {
		this.url = url;
		updateParts();
	}
	
	/**
	 * Initialize a {@link UrlConfiguration} instance from a url
	 * 
	 * @param uri {@link URI} use to configure the {@link PingConfiguration}
	 */
	public UrlConfiguration(URI uri) {

	}
	
    public void configure(URI uri) {
		try {
			this.url = uri.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
    
    public UrlConfiguration copy() {
        try {
            return (UrlConfiguration) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeCamelException(e);
        }
    }
    
	public URL toURL() throws MalformedURLException {
		URL url = null;
		if (this.url != null) {
			url = this.url;
		}
		else {
		StringBuffer sb = new StringBuffer();
		sb.append(getScheme());
		sb.append("://");
		// User and password are optional
		if (getUser() != null || getPassword() != null) {
			sb.append(getUser() == null ? "" : getUser());
			sb.append(":");
			sb.append(getPassword());
			sb.append("@");
		}
		sb.append(getHost());
		sb.append(getPort() == DEFAULT_PORT ? "" : ":" + getPort());
		sb.append("/");
		sb.append(getPath() == null ? "" : getPath());
		if (getQuery() != null) {
			sb.append("?"+getQuery());
		}

		url = new URL(sb.toString());
		}
		
		return url;
	}

	/**
	 * Returns a {@link String} with the internal representation
	 * of the instance.
	 * 
	 * @return {@link String} String contents of {@link UrlConfiguration}
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		
		s.append(getHost() == null ?  "" : "host=" + getHost());
		s.append(getPort() == 0 ?  "" : ",port=" + getPort());
		s.append(getPath() == null ?  "" : ",path=" +  getPath());
		s.append(getUser() == null ?  "" : ",user=" + getUser());
		s.append(getPassword() == null ? "" : ",password=" + getPassword());
		s.append(getTimeout() == 0 ? "" : ",timeout=" + getTimeout());

		return s.toString();
	}
	
	public static UrlConfiguration getConfiguration(String host,int timeOut) {
		UrlConfiguration configuration = new UrlConfiguration();
		
		configuration.setHost(host);
		configuration.setTimeout(timeOut);
		
		return configuration;
	}
	
	public static UrlConfiguration getConfiguration(String host) {
		return getConfiguration(host,DEFAULT_TIMEOUT);
	}
	
	public static UrlConfiguration getConfiguration() {
		return getConfiguration(DEFAULT_HOST,DEFAULT_TIMEOUT);
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public URL getUrl() {
		return url;
	}
	
	private void updateParts() {
		setHost(getUrl().getHost());
		setPort(getUrl().getPort());
		setPath(getUrl().getPath());
	}

	public void setUrl(URL url) {
		this.url = url;
		updateParts();
	}

	public void setFollowRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
	}

	public boolean getFollowRedirects() {
		return followRedirects;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
}
