package org.openmrs.module.html5forms.api.db.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.html5forms.HTML5Form;
import org.openmrs.module.html5forms.HTML5XForm;
import org.openmrs.module.html5forms.api.db.hibernate.HTML5FormDAO;
import org.openmrs.module.html5forms.api.impl.EnketoServiceImpl;
import org.openmrs.module.xforms.Xform;

import java.util.List;

public class HTML5FormDAOImpl implements HTML5FormDAO {
    private SessionFactory factory;

    public HTML5FormDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public HTML5FormDAOImpl(HTML5FormDAOImpl html5FormDAO, EnketoServiceImpl enketoService) {
    }

    public List<HTML5Form> getAll() {
        return (List<HTML5Form>) session().createCriteria(HTML5Form.class).list();

    }

    //TODO: Move this to a named query
    public List<HTML5XForm> getXForms() {
        return (List<HTML5XForm>) session().createQuery("from HTML5XForm where id not in (select id from HTML5Form)").list();
    }

    public void saveForm(HTML5Form form) {
        session().saveOrUpdate(form);
    }

    public HTML5Form findById(Integer id) {
        return (HTML5Form) session().get(HTML5Form.class, id);
    }

    public Xform getXform(int id) {
        return (Xform) session().get(Xform.class, id);
    }

    private Session session() {
        return factory.getCurrentSession();
    }
}
