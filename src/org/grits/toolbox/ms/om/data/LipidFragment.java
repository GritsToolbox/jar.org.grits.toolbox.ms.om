package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Lipid fragment pattern and number
 * @author Masaaki Matsubara
 *
 */
@XmlType
public class LipidFragment {
	// TODO: Add type
	public static final String TYPE_XXX = "XXX";
	public static final String UNKNOWN = "-1";

	private String m_type = null;
	private String m_number = null;

	public String getType() {
		return this.m_type;
	}

	@XmlAttribute(name="type")
	public void setType(String a_type) {
		this.m_type = a_type;
	}

	public String getNumber() {
		return this.m_number;
	}

	@XmlAttribute(name="number")
	public void setNumber(String a_number) {
		this.m_number = a_number;
	}
}
