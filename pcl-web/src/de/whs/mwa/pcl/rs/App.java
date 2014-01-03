package de.whs.mwa.pcl.rs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class App extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public App(){
	     singletons.add(new Test());
	}
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}

}
