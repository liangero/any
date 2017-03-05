package com.any.dao.base;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.engine.spi.TypedValue;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.CompositeType;
import org.hibernate.type.Type;

import java.util.*;

/**
 * tyl
 * Created by avaio on 2017/3/5.
 */
public class Example extends org.hibernate.criterion.Example {

    private final Object exampleEntity; //与父类同一引用
    private PropertySelector selector;
    private final Set<String> excludedProperties = new HashSet<>();

    /**
     * Allow subclasses to instantiate as needed.
     *
     * @param exampleEntity The example bean
     * @param selector      The property selector to use
     */
    protected Example(Object exampleEntity, PropertySelector selector) {
        super(exampleEntity, selector);
        this.selector = selector;
        this.exampleEntity = exampleEntity;
    }

    public static Example create(Object exampleEntity) {
        if (exampleEntity == null) {
            throw new NullPointerException("null example entity");
        }
        return new Example(exampleEntity, NotNullPropertySelector.INSTANCE);
    }

    @Override
    public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) {
        final EntityPersister meta = criteriaQuery.getFactory().getMetamodel().entityPersister(
                criteriaQuery.getEntityName(criteria)
        );
        final List<String> propertyNames = getPropertyNames(meta);
        final List<Type> propertyTypes = getPropertyTypes(meta);
        final List<Object> values = getPropertyValues(meta);
        final List<TypedValue> list = new ArrayList<>();
        for (int i = 0; i < propertyNames.size(); i++) {
            final Object value = values.get(i);
            final Type type = propertyTypes.get(i);
            final String name = propertyNames.get(i);

            final boolean isVersionProperty = i == meta.getVersionProperty();

            if (!isVersionProperty && isPropertyIncluded(value, name, type)) {
                if (propertyTypes.get(i).isComponentType()) {
                    addComponentTypedValues(name, value, (CompositeType) type, list, criteria, criteriaQuery);
                } else {
                    addPropertyTypedValue(value, type, list);
                }
            }
        }

        return list.toArray(new TypedValue[list.size()]);
    }


    @Override
    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
        final StringBuilder buf = new StringBuilder().append('(');
        final EntityPersister meta = criteriaQuery.getFactory().getMetamodel().entityPersister(
                criteriaQuery.getEntityName(criteria)
        );
        final List<String> propertyNames = getPropertyNames(meta);
        final List<Type> propertyTypes = getPropertyTypes(meta);
        final List<Object> propertyValues = getPropertyValues(meta);
        for (int i = 0; i < propertyNames.size(); i++) {
            final Object propertyValue = propertyValues.get(i);
            final String propertyName = propertyNames.get(i);

            final boolean isVersionProperty = i == meta.getVersionProperty();
            if (!isVersionProperty && isPropertyIncluded(propertyValue, propertyName, propertyTypes.get(i))) {
                if (propertyTypes.get(i).isComponentType()) {
                    appendComponentCondition(
                            propertyName,
                            propertyValue,
                            (CompositeType) propertyTypes.get(i),
                            criteria,
                            criteriaQuery,
                            buf
                    );
                } else {
                    appendPropertyCondition(
                            propertyName,
                            propertyValue,
                            criteria,
                            criteriaQuery,
                            buf
                    );
                }
            }
        }

        if (buf.length() == 1) {
            buf.append("1=1");
        }

        return buf.append(')').toString();
    }

    private boolean isPropertyIncluded(Object value, String name, Type type) {
        return !excludedProperties.contains(name) && !type.isAssociationType() && selector.include(value, name, type);
    }

    private List<String> getPropertyNames(EntityPersister meta) {
        String[] propertyNames = meta.getPropertyNames();
        List<String> result = new ArrayList<>();
        Collections.addAll(result, propertyNames);
        result.add(meta.getIdentifierPropertyName());
        return result;
    }

    private List<Type> getPropertyTypes(EntityPersister meta) {
        Type[] propertyTypes = meta.getPropertyTypes();
        List<Type> result = new ArrayList<>();
        Collections.addAll(result, propertyTypes);
        result.add(meta.getIdentifierType());
        return result;
    }

    private List<Object> getPropertyValues(EntityPersister meta) {
        Object[] propertyValues = meta.getPropertyValues(exampleEntity);
        List<Object> result = new ArrayList<>();
        Collections.addAll(result, propertyValues);
        result.add(meta.getIdentifier(exampleEntity, null));
        return result;
    }

    public org.hibernate.criterion.Example excludeProperty(String name) {
        excludedProperties.add(name);
        return this;
    }
}
