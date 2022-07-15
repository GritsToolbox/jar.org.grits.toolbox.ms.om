package org.grits.toolbox.ms.om.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class IonSettings extends Ion
{
	@XmlElement(name="ionCount")
    private List<Integer> m_counts = new ArrayList<Integer>();

	public IonSettings() {
		super();
	}

	public IonSettings( Ion a_ion ) {
		super( a_ion.getLabel(), a_ion.getMass(), a_ion.getName(), a_ion.getCharge(), a_ion.getPolarity() );
	}
	
    public IonSettings(String a_label, Double a_mass, String a_name, Integer a_charge, Boolean a_polarity) {
		super(a_label, a_mass, a_name, a_charge, a_polarity);
	}
	
    @Override
    public boolean equals(Object obj) {
    	if( ! (obj instanceof Ion) ) {
    		return false;
    	}
    	if( ! super.equals(obj) ) {
    		return false;
    	}
    	IonSettings other = (IonSettings) obj;
    	if( getCounts().size() != other.getCounts().size() ) {
    		return false;
    	}
    	for( Integer iCount : getCounts() ) {
    		if( ! other.getCounts().contains(iCount) ) { 
    			return false;
    		}
    	}
    	return true;
    }
    
    @XmlTransient
    public List<Integer> getCounts()
    {
        return m_counts;
    }
      
    public void setCounts(List<Integer> a_counts)
    {
        m_counts = a_counts;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder( super.toString() );
    	if( getPolarity() ) {
    		sb.append(" + ");
    	} else {
    		sb.append(" - ");
    	}
    	if( m_counts != null ) {
        	sb.append(" (");
        	String sw = "";
    		for (int i = 0; i < m_counts.size(); i++ ) {
    			sb.append(sw);
    			sb.append(Integer.toString(m_counts.get(i)));
    			sw = ",";
    		}
    		sb.append(")");
    	} else {
        	sb.append(" (NO COUNTS)");
    	}
    	return sb.toString();
    }
    
}
