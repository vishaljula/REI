package com.example.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.example.rest.FileUploads;


@ApplicationPath("/rest")
public class MyApplication  extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public MyApplication() {
		
		singletons.add(new FileUploads());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}