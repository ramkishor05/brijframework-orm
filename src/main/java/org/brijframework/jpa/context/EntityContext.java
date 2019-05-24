package org.brijframework.jpa.context;

import java.util.Properties;

import org.brijframework.jpa.container.EntityDataContainer;
import org.brijframework.jpa.container.EntityModelContainer;
import org.brijframework.jpa.factories.EntityDataFactory;
import org.brijframework.jpa.factories.EntityModelFactory;
import org.brijframework.jpa.factories.internal.AnnoEntityModelFactory;
import org.brijframework.jpa.factories.internal.JsonEntityDataFactory;

public class EntityContext {
  
	private Properties properties=new Properties();
	
	public void start() {
		EntityModelContainer entityModelContainer=EntityModelContainer.getContainer(); 
		entityModelContainer.setContext(this);
		EntityModelFactory entityModelFactory=EntityModelFactory.getFactory();
		entityModelFactory.setContainer(entityModelContainer);
		entityModelFactory.loadFactory();
		EntityModelFactory annoEntityDataFactory=AnnoEntityModelFactory.getFactory();
		annoEntityDataFactory.setContext(this);
		annoEntityDataFactory.setContainer(entityModelContainer);
		annoEntityDataFactory.loadFactory();
		entityModelContainer.build();
		entityModelContainer.procced();
		EntityDataContainer entityDataContainer=EntityDataContainer.getContainer();
		entityDataContainer.setContext(this);
		EntityDataFactory entityDataFactory=EntityDataFactory.getFactory();
		entityDataFactory.setContext(this);
		entityDataFactory.setContainer(entityDataContainer);
		entityDataFactory.loadFactory();
		EntityDataFactory jsonEntityDataFactory=JsonEntityDataFactory.getFactory();
		jsonEntityDataFactory.setContext(this);
		jsonEntityDataFactory.setContainer(entityDataContainer);
		jsonEntityDataFactory.loadFactory();
		entityDataContainer.build();
		entityDataContainer.procced();
	}
	
	public void stop() {
		EntityDataContainer entityDataContainer=EntityDataContainer.getContainer();
		entityDataContainer.getCache().clear();
		EntityDataFactory entityDataFactory=EntityDataFactory.getFactory();
		entityDataFactory.getCache().clear();
		EntityDataFactory jsonEntityDataFactory=JsonEntityDataFactory.getFactory();
		jsonEntityDataFactory.getCache().clear();
	}
	
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
}
