package net.mariusgundersen.bookmarks;

import java.util.HashMap;
import java.util.Map;

public abstract class SessionStore<T> {

	private Map<String, T> sessionObjectMap;

	public SessionStore() {
		this.sessionObjectMap = new HashMap<>();
	}

	public T getSessionObject(String sessionId){
		if(sessionObjectMap.containsKey(sessionId) == false){
			sessionObjectMap.put(sessionId, createSessionObject());
		}
		
		return sessionObjectMap.get(sessionId);
	}

	protected abstract T createSessionObject();
}
