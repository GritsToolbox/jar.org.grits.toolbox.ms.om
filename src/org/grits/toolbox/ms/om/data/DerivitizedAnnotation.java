package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="derivitizedAnnotation")
public class DerivitizedAnnotation extends Annotation
{
    private String m_perDerivatisationType = null;
        
    public DerivitizedAnnotation()
    {
        super();
        this.setType("org.grits.toolbox.ms.om.data.DerivitizedAnnotation");
    }    
    
    public String getPerDerivatisationType() {
        return m_perDerivatisationType;
    }
    @XmlAttribute(name="derivatisationType")
    public void setPerDerivatisationType(String perDerivatisationType) {
        this.m_perDerivatisationType = perDerivatisationType;
    }
}
