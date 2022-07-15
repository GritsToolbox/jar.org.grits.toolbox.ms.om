package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Ion extends Molecule
{
    private Integer m_charge = null;
    private Boolean m_polarity = null;
    
    public Ion() {
		// TODO Auto-generated constructor stub
	}
    
    public Ion( String a_label, Double a_mass, String a_name, Integer a_charge, Boolean a_polarity ) {
    	super( a_label, a_mass, a_name );
    	this.m_charge = a_charge;
    	this.m_polarity = a_polarity;
	}
    
    @Override
    public boolean equals(Object obj) {
    	if( ! (obj instanceof Ion) ) {
    		return false;
    	}
    	if( ! super.equals(obj) ) {
    		return false;
    	}
    	Ion other = (Ion) obj;
    	if( Integer.compare(getCharge(), other.getCharge()) != 0 ) {
    		return false;
    	}
    	if( Boolean.compare( getPolarity(), other.getPolarity() ) != 0 ) {
    		return false;
    	}
    	return true;
    }
    
    public Integer getCharge()
    {
        return m_charge;
    }
    @XmlAttribute(name="charge")
    public void setCharge(Integer a_charge)
    {
        m_charge = a_charge;
    }
    
    public Boolean getPolarity() {
		return m_polarity;
	}
    @XmlAttribute(name="polarity")   
    public void setPolarity(Boolean a_polarity) {
		this.m_polarity = a_polarity;
	}
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder(super.toString());
    	sb.append(", charge: " + m_charge + ", polarity: " + m_polarity);
    	return sb.toString();
    }
}
