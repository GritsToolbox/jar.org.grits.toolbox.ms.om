package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Lipid database name and uri
 * @author Masaaki Matsubara
 *
 */
@XmlType
public class LipidDatabase {

	private String m_strDatabase = null;
	private String m_strURI = null;

	public String getDatabase() {
		return this.m_strDatabase;
	}
	@XmlAttribute(name="database")
	public void setDatabase(String a_database) {
		this.m_strDatabase = a_database;
	}

	public String getURI() {
		return this.m_strURI;
	}
	@XmlAttribute(name="uri")
	public void setURI(String a_uri) {
		this.m_strURI = a_uri;
	}
}
