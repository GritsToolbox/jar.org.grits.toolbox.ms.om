package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class MoleculeSettings extends Molecule
{
	private Integer m_count = null;

	public MoleculeSettings() {
		super();
		this.m_count = null;
	}
	
	public MoleculeSettings( Molecule a_molecule ) {
		super( a_molecule.getLabel(), a_molecule.getMass(), a_molecule.getName() );
	}
	
    public MoleculeSettings(String a_label, Double a_mass, String a_name, Integer a_count ) {
		super(a_label, a_mass, a_name);
		this.m_count = a_count;
	}
	
    public Integer getCount()
    {
        return m_count;
    }
    @XmlAttribute(name="count")
    public void setCount(Integer a_count)
    {
        m_count = a_count;
    }
}
