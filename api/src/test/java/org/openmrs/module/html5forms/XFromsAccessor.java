package org.openmrs.module.html5forms;

import java.util.List;

public class XFromsAccessor {
    private XForms forms;

    public XFromsAccessor(XForms forms) {
        this.forms = forms;
    }

    public List<XForm> getList() {
        return forms.list;
    }
}
