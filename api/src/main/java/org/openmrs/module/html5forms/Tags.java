package org.openmrs.module.html5forms;

import java.util.Collection;
import java.util.List;
import java.util.Set;

//TODO: Do not extend ArrayList but wrap it around a list. This is done because of hibernate limitation
public class Tags {

    Collection<HTML5FormTag> list;

    public Tags(Set<HTML5FormTag> tags) {
        list = tags;
    }

    public Tags(List<HTML5FormTag> tags) {
        list = tags;
    }
}
