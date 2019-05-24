package org.brijframework.jpa.builder;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

import org.brijframework.jpa.group.EntityDataGroup;
import org.brijframework.util.reflect.FieldUtil;
import org.brijframework.util.support.Access;

public class EntryComparator implements Comparator<EntityDataGroup>{

	@Override
	public int compare(EntityDataGroup o1, EntityDataGroup o2) {
		Object entity1=o1.getEntityObject();
		Object entity2=o2.getEntityObject();
		List<Field> fields1=FieldUtil.getAllField(entity1.getClass(),Access.PRIVATE);
		for(Field field1: fields1) {
			if(entity2.getClass().isAssignableFrom(field1.getType())) {
				return 1;
			}
		}
		return -1;
	}
}