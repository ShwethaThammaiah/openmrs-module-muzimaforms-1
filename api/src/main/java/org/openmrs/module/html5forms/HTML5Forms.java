package org.openmrs.module.html5forms;

import java.util.List;

public class HTML5Forms {
    public List<HTML5Form> getList() {
        return list;
    }

    List<HTML5Form> list;

    public HTML5Forms(List<HTML5Form> list) {
        this.list = list;
    }


}
