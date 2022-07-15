package org.grits.toolbox.ms.om.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author D Brent Weatherly (dbrentw@uga.edu)
 * 
 * XML class for Fragment object in Object Model
 *
 */

@XmlType
public class Fragment
{
    public static final String TYPE_A = "A";
    public static final String TYPE_B = "B";
    public static final String TYPE_C = "C";
    public static final String TYPE_X = "X";
    public static final String TYPE_Y = "Y";
    public static final String TYPE_Z = "Z";
    public static final String    UNKNOWN = "-1";
    private String m_type = null;
    private String m_number = null;
    public String getType()
    {
        return m_type;
    }
    @XmlAttribute(name="type")
    public void setType(String a_type)
    {
        m_type = a_type;
    }
    public String getNumber()
    {
        return m_number;
    }
    @XmlAttribute(name="number")
    public void setNumber(String a_number)
    {
        m_number = a_number;
    }
}
