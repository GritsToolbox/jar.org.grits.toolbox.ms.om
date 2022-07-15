package org.grits.toolbox.ms.om.data;



public class IonAdduct extends Ion
{
	private Integer m_count = null;
	
	public IonAdduct() {
		super();
		this.m_count = null;
	}
	
    public IonAdduct(String a_label, Double a_mass, String a_name, Integer a_charge, Boolean a_polarity, Integer a_count) {
		super(a_label, a_mass, a_name, a_charge, a_polarity);
		this.m_count = a_count;
	}

    public Integer getCount()
    {
        return m_count;
    }

    public void setCount(Integer a_count)
    {
        m_count = a_count;
    }
}
