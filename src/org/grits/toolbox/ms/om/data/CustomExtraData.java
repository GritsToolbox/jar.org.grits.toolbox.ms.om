package org.grits.toolbox.ms.om.data;

import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

@XmlType
public class CustomExtraData {
	private static final Logger logger = Logger.getLogger(CustomExtraData.class);
	
	@XmlElement(name="key")
	protected String m_key;
    
    @XmlElement(name="label")
	protected String m_label;
    
    @XmlElement(name="description")
	protected String m_description;
    
    @XmlElement(name="type")
	protected Type m_type;
    
	@XmlElement(name="doubleFormat")
	protected String m_doubleFormat = null;
	
	@XmlTransient
	private DecimalFormat m_doubleDecFormat = null;
	
	@XmlEnum(String.class)
	public enum Type {Integer, Double, String, Boolean};

	public CustomExtraData() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomExtraData( String a_key, String a_label, String a_description, Type a_type) {
		setKey(a_key);
		setLabel(a_label);
		setDescription(a_description);
		setType(a_type);
	}

	public CustomExtraData( String a_key, String a_label, String a_description, Type a_type, String  a_doubleFormat) {
		this(a_key, a_label, a_description, a_type);
		setDoubleFormat(a_doubleFormat);
	}

	@Override
	public boolean equals(Object arg0) {
		if( ! (arg0 instanceof CustomExtraData ) ) 
			return false;
		
		CustomExtraData ced = (CustomExtraData) arg0;
		return ( this.m_key.equals(ced.m_key ) );		// if keys the same, same object
	}
	
	
	@XmlTransient
	public String getDescription() {
		return m_description;
	}
	public void setDescription(String a_description) {
		this.m_description = a_description;
	}
	
	@XmlTransient
	public String getKey() {
		return m_key;
	}
	public void setKey(String a_key) {
		this.m_key = a_key;
	}
	
	@XmlTransient
	public String getLabel() {
		return m_label;
	}
	public void setLabel(String a_label) {
		this.m_label = a_label;
	}
	
	@XmlTransient
	public Type getType() {
		return m_type;
	}
	public void setType(Type a_type) {
		this.m_type = a_type;
	}
	
	@XmlTransient
	public DecimalFormat getDoubleFormat() {
		if( this.m_doubleFormat == null ) { // default double format
			this.m_doubleFormat = "0.0000"; 
		}
		if( this.m_doubleDecFormat == null ) {
			this.m_doubleDecFormat = new DecimalFormat(this.m_doubleFormat);
		}
		return this.m_doubleDecFormat;
	}	
	public void setDoubleFormat(String m_doubleFormat) {
		this.m_doubleFormat = m_doubleFormat;
		try {
			this.m_doubleDecFormat = new DecimalFormat(this.m_doubleFormat);
		} catch( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}
	}
	
	@Override
	public String toString() {
		return "Key: " + this.m_key + ", Label: " + this.m_label + ", Type: " + this.m_type + ", Dec format: " + this.m_doubleFormat;
	}
}
