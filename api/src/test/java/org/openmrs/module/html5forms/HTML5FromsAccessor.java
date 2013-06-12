package org.openmrs.module.html5forms;

import java.util.List;

public class HTML5FromsAccessor {
    private HTML5Forms forms;

    public HTML5FromsAccessor(HTML5Forms forms) {
        this.forms = forms;
    }

    public List<HTML5Form> getList() {
        return forms.list;
    }
}
