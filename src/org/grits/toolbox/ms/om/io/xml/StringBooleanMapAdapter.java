/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grits.toolbox.ms.om.io.xml;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author ubuntu
 */
public class StringBooleanMapAdapter extends XmlAdapter<StringBooleanMapElements[], Map<String, Boolean>> {

    public StringBooleanMapElements[] marshal(Map<String, Boolean> arg0) throws Exception {
        StringBooleanMapElements[] mapElements = new StringBooleanMapElements[arg0.size()];
        int i = 0;
        for (Map.Entry<String, Boolean> entry : arg0.entrySet()) {
            mapElements[i++] = new StringBooleanMapElements(entry.getKey(), entry.getValue());
        }

        return mapElements;
    }

    public Map<String, Boolean> unmarshal(StringBooleanMapElements[] arg0) throws Exception {
        Map<String, Boolean> r = new HashMap<String, Boolean>();
        for (StringBooleanMapElements mapelement : arg0) {
            r.put(mapelement.key, mapelement.value);
        }
        return r;
    }
    
}
