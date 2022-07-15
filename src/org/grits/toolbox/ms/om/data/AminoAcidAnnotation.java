package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

public abstract class AminoAcidAnnotation extends Annotation
{
    private List<Modification> m_modification = new ArrayList<Modification>();

    public List<Modification> getModification()
    {
        return m_modification;
    }

    public void setModification(List<Modification> a_modification)
    {
        m_modification = a_modification;
    }
}
