package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Molecule
{
    private String m_label = null;
    private Double m_mass = null;
    private String m_name = null;
    
    public Molecule() {
    	this.m_label = null;
    	this.m_mass = null;
    	this.m_name = null;
	}
    
    public Molecule( String a_label, Double a_mass, String a_name ) {
    	this.m_label = a_label;
    	this.m_mass = a_mass;
    	this.m_name = a_name;
	}
    
    @Override
    public boolean equals(Object obj) {
    	if( ! (obj instanceof Molecule) ) 
    		return false;
    	Molecule other = (Molecule) obj;
    	if( ! getLabel().equals(other.getLabel() ) ) {
    		return false;
    	} 
    	if( Double.compare(getMass(), other.getMass()) != 0 ) {
    		return false;
    	}
    	return true;
    }
    
    public String getLabel()
    {
        return m_label;
    }
    @XmlAttribute(name="label")
    public void setLabel(String a_label)
    {
        m_label = a_label;
    }
    public Double getMass()
    {
        return m_mass;
    }
    @XmlAttribute(name="mass")
    public void setMass(Double a_mass)
    {
        m_mass = a_mass;
    }
    public String getName()
    {
        return m_name;
    }
    @XmlAttribute(name="name")
    public void setName(String a_name)
    {
        m_name = a_name;
    }
    
    @Override
    public String toString() {
    	return "Molecule: " + m_label + ", " + m_mass + ", " + m_name;
    }
    
}
