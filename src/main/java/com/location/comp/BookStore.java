package com.location.comp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class BookStore {

	private final Map<String, Object> bookStore = new ConcurrentHashMap<String, Object>();
	
	public void save(String key, Object value) {
		bookStore.put(key, value);
	}
	
	public Object get(String key) {
		return bookStore.get(key);
	}
	
	public void remove(String key) {
		bookStore.remove(key);
	}
}
