package de.whs.mwa.pcl.rs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.googlecode.htmleasy.HtmleasyProviders;

public class App extends Application {
	private Set<Object> singletons;
	private Set<Class<?>> classes;

	public App() {
		singletons = new HashSet<Object>();
		classes = new HashSet<Class<?>>();
		
		singletons.add(new Test());
		singletons.add(new UserResource());
		
		classes.addAll(HtmleasyProviders.getClasses());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
