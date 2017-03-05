package com.any.dao.base;

import org.hibernate.transform.DistinctResultTransformer;

import java.util.List;

/**
 * tyl
 * Created by avaio on 2017/3/5.
 */
public class AliasResultTransformer implements org.hibernate.transform.ResultTransformer {

    private String alias;

    public AliasResultTransformer(String alias) {
        this.alias = alias;
    }

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        for (int i = 0; i < aliases.length; i++) {
            if (alias.equals(aliases[i])) {
                return tuple[i];
            }
        }
        return tuple[0];
    }

    @Override
    public List transformList(List collection) {
        return DistinctResultTransformer.INSTANCE.transformList(collection);
    }
}
