package org.grits.toolbox.ms.om.io.xml;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.grits.toolbox.ms.om.data.Annotation;
import org.grits.toolbox.ms.om.data.Scan;

public class IntegerAnnotationMapAdapter extends XmlAdapter<IntegerAnnotationMapElement[], Map<Integer, Annotation>> {
    public IntegerAnnotationMapElement[] marshal(Map<Integer, Annotation> arg0) throws Exception {
    	IntegerAnnotationMapElement[] mapElements = new IntegerAnnotationMapElement[arg0.size()];
        int i = 0;
        for (Map.Entry<Integer, Annotation> entry : arg0.entrySet())
            mapElements[i++] = new IntegerAnnotationMapElement(entry.getKey(), entry.getValue());

        return mapElements;
    }

    public Map<Integer, Annotation> unmarshal(IntegerAnnotationMapElement[] arg0) throws Exception {
        Map<Integer, Annotation> r = new HashMap<Integer, Annotation>();
        for (IntegerAnnotationMapElement mapelement : arg0)
            r.put(mapelement.annId, mapelement.annotation);
        return r;
    }
}
