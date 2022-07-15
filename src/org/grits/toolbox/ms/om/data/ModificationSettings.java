package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class ModificationSettings extends Molecule
{
	private String m_modificationType = null;

	public ModificationSettings() {
		super();
		this.m_modificationType = null;
	}
	
	public ModificationSettings(String a_label, Double a_mass, String a_name, String a_modificationType ) {
		super(a_label, a_mass, a_name);
		this.m_modificationType = a_modificationType;
	}

    public String getModificationType()
    {
        return m_modificationType;
    }
    @XmlAttribute(name="modType")
    public void setModificationType(String a_modificationType)
    {
        m_modificationType = a_modificationType;
    }
}
