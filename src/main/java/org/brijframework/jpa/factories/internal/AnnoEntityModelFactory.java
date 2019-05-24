package org.brijframework.jpa.factories.internal;

import java.lang.annotation.Annotation;

import org.brijframework.jpa.factories.EntityModelFactory;
import org.brijframework.jpa.model.EntityModel;
import org.brijframework.jpa.util.EntityConstants;
import org.brijframework.jpa.util.EntityMapper;
import org.brijframework.util.reflect.ClassUtil;
import org.brijframework.util.reflect.ReflectionUtils;

public class AnnoEntityModelFactory extends EntityModelFactory {

	private static AnnoEntityModelFactory factory;

	public static AnnoEntityModelFactory getFactory() {
		if (factory == null) {
			factory = new AnnoEntityModelFactory();
		}
		return factory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AnnoEntityModelFactory loadFactory() {
		super.loadFactory();
		if(ClassUtil.isClass(EntityConstants.JPA_ENTITY)) {
			Class<? extends Annotation> entity=(Class<? extends Annotation>) ClassUtil.getClass(EntityConstants.JPA_ENTITY);
			for(Class<?> cls:ReflectionUtils.getInternalClassList()) {
				if(cls.isAnnotationPresent(entity)) {
					this.register(cls,entity);
				}
			}
		}
		return this;
	}

	private void register(Class<?> cls,Class<? extends Annotation> entity) {
		EntityModel entityModel=EntityMapper.getEntityModel(cls, entity);
		this.register(entityModel); 
	}

}
