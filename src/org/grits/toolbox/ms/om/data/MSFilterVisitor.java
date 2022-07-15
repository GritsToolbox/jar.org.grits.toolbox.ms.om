package org.grits.toolbox.ms.om.data;

import org.grits.toolbox.util.structure.glycan.filter.GlycanFilterException;
import org.grits.toolbox.util.structure.glycan.filter.om.ComboFilter;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterAnd;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterAntenna;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterGlycanFeature;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterLinkage;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterModification;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterMonosaccharide;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterNot;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterOr;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterSubstituent;
import org.grits.toolbox.util.structure.glycan.filter.om.GlycanFilterSubstructure;

/**
 * this interface is required because we cannot put the MS specific visitor class (which needs to access MS tables)
 * in this package due to cyclic dependencies. Therefore we need a generic interface and MS Specific visitor
 * will have to implement this interface.
 * 
 * IMPORTANT! This interface needs to be updated for each new filter type added to the system
 * 
 * @author sena
 *
 */
public interface MSFilterVisitor {
	
	boolean visit (IntensityFilter filter) throws GlycanFilterException;
	
	boolean visit(GlycanFilterAnd a_filterAnd) throws GlycanFilterException;
	boolean visit(GlycanFilterNot a_filterNot) throws GlycanFilterException;
	boolean visit(GlycanFilterOr a_filterOr) throws GlycanFilterException;
	boolean visit(GlycanFilterSubstructure a_glycanFilterSubstructure) throws GlycanFilterException;
	boolean visit(GlycanFilterSubstituent a_glycanFilterSubstituent) throws GlycanFilterException;
	boolean visit(GlycanFilterMonosaccharide a_glycanFilterMonosaccharide) throws GlycanFilterException;
	boolean visit(GlycanFilterModification a_glycanFilterModification) throws GlycanFilterException;
	boolean visit(GlycanFilterAntenna a_glycanFilterAntenna) throws GlycanFilterException;
	boolean visit(GlycanFilterLinkage a_glycanFilterLinkage) throws GlycanFilterException;
	boolean visit(GlycanFilterGlycanFeature a_glycanFilterGlycanFeature) throws GlycanFilterException;
	boolean visit(ComboFilter comboFilter) throws GlycanFilterException;
}
