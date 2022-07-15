package org.grits.toolbox.ms.om.io.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.grits.toolbox.ms.om.data.Annotation;
import org.grits.toolbox.ms.om.data.Data;
import org.grits.toolbox.ms.om.data.DataHeader;
import org.grits.toolbox.ms.om.data.Feature;
import org.grits.toolbox.ms.om.data.FeatureSelection;
import org.grits.toolbox.ms.om.data.GlycanAnnotation;
import org.grits.toolbox.ms.om.data.GlycanFeature;
import org.grits.toolbox.ms.om.data.GlycanScansAnnotation;
import org.grits.toolbox.ms.om.data.Method;
import org.grits.toolbox.ms.om.data.ScanFeatures;
import org.grits.toolbox.ms.om.data.ScansAnnotation;
import org.grits.toolbox.util.structure.glycan.filter.om.FilterSetting;
import org.grits.toolbox.util.structure.glycan.util.FilterUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/*
 * @author Originally, Khalifeh Al Jadda
 * @author dbrentw, D Brent Weatherly (dbrentw@uga.edu)
 * 
 * Utility class to read and write archive file(s) according to the GRITS object model
 */
public class AnnotationWriter {
	private static final Logger logger = Logger.getLogger(AnnotationWriter.class);

	public final static String DATA_FILE = "data.xml";
	public final static String DATA_HEADER = "dataHeader.xml";
	public final static String SETTINGS_FILE = "settings.xml";
//	public static final String TEMP_FOLDER = "temp";

	/* createAnnZipFilePerScan:  Marshalls ScanFeatures into XML files for each scan 
	 * 
	 * NOTE: this method must have been a utility method for Khalifeh's thesis because it isn't called at all within GRITS anymore.
	 * 
	 * @param Data object
	 * 
	 */
	public void createAnnZipFilePerScan(Data data) throws IOException{
		try{

			JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
			List<Class> contextList = new ArrayList<>(Arrays.asList(AnnotationReader.filterClassContext));
			contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
			//List<Class> contextList = new ArrayList<>(Arrays.asList(FilterUtils.filterClassContext));
			contextList.add(FilterSetting.class);
			contextList.add(ScanFeatures.class);
			JAXBContext jaxbContextAnn = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			Marshaller jaxbMarshallerAnn = jaxbContextAnn.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			//compress the annotations	
			Iterator<Entry<Integer, ScanFeatures>> it = data.getScanFeatures().entrySet().iterator();
			while(it.hasNext()){
				Entry<Integer, ScanFeatures> item = it.next();
				int scanId = item.getKey();
				if(scanId != 1){
					File f = new File("./zip/"+scanId+".xml");
					//write the file to the archive file
					jaxbMarshallerAnn.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					//jaxbMarshallerAnn.marshal(data.getScanFeatures().get(scanId), compressedFile);
					jaxbMarshallerAnn.marshal(data.getScanFeatures().get(scanId), f);
					it.remove();
					//compressedFile.closeEntry();	
				}
			}
		}catch(Exception ex){
			logger.error("Exception in AnnotationWriter:createAnnZipFilePerScan." , ex);
		}

	}

	/* writeAnnotationsPerAnalyte:  Marshalls ScanFeatures into XML files for each scan 
	 *  
	 * @param Data object
	 * 
	 */
	public void writeAnnotationsPerAnalyte(ScansAnnotation annotations, String path) throws IOException{
		File prevFile = null;
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(annotations.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			File f = new File(path + File.separator + annotations.getStringAnnotationId() + ".xml");
			if( f.exists() ) {
				prevFile = new File(path + File.separator + annotations.getStringAnnotationId() + ".xml.bak");
				if(prevFile.exists()) {
					prevFile.delete();
				}
				boolean bRes = f.renameTo(prevFile);
				if( ! bRes ) {
					prevFile = null;
					throw new IOException("Unable to backup the xml file.");
				}
			}
			//write the file to the archive file
			//jaxbMarshallerAnn.marshal(data.getScanFeatures().get(scanId), compressedFile);
			jaxbMarshaller.marshal(annotations, f);
			prevFile = null;
		}catch(Exception ex){
			logger.error("Exception in writeAnnotationsPerAnalyte." , ex);
		}
		if( prevFile != null && prevFile.exists() ) {
			File f = new File(path + File.separator + annotations.getStringAnnotationId() + ".xml");
			prevFile.renameTo(f);
		}
	}

	/* writeDataHeaderToZip:  Writes a DataHeader object to an open ZipOutputStream
	 * 
	 * @param DataHeader : from the GRITS object model
	 * @param ZipOutputStream : open compressed file output stream for the archive
	 * 
	 */
	public void writeDataHeaderToZip( DataHeader dHeader, ZipOutputStream compressedFile ) {
		JAXBContext jaxbDataHeaderContext = null;
		Marshaller jaxbDataHeaderMarshaller = null;
		try {
			//List<Class> contextList = new ArrayList<>(Arrays.asList(FilterUtils.filterClassContext));
			List<Class> contextList = new ArrayList<>(Arrays.asList(AnnotationReader.filterClassContext));
			contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
			contextList.add(FilterSetting.class);
			contextList.add(DataHeader.class);
			jaxbDataHeaderContext = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
			jaxbDataHeaderMarshaller = jaxbDataHeaderContext.createMarshaller();
		} catch (JAXBException e1) {
			logger.error(e1.getMessage(), e1);
			return;
		} catch ( Exception e2 ) {
			logger.error(e2.getMessage(), e2);
			return;
		}
		try {
			jaxbDataHeaderMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			compressedFile.putNextEntry(new ZipEntry(AnnotationWriter.DATA_HEADER));
			jaxbDataHeaderMarshaller.marshal(dHeader, compressedFile);
			AnnotationObjectCache.clearCache();
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} catch (JAXBException e2) {
			logger.error(e2.getMessage(), e2);
		} catch ( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}
		try {
			compressedFile.closeEntry();
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} catch ( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}

	}

	/* writeMethodToZip:  Writes a Method object to an open ZipOutputStream
	 * 
	 * @param Method : from the GRITS object model
	 * @param ZipOutputStream : open compressed file output stream for the archive
	 * 
	 */	
	public void writeMethodToZip( Method method, ZipOutputStream compressedFile ) {
		JAXBContext jaxbMethodContext = null;
		Marshaller jaxbMethodMarshaller = null;
		try {
			//List<Class> contextList = new ArrayList<>(Arrays.asList(FilterUtils.filterClassContext));
			List<Class> contextList = new ArrayList<>(Arrays.asList(AnnotationReader.filterClassContext));
			contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
			contextList.add(FilterSetting.class);
			contextList.add(Method.class);
			jaxbMethodContext = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
			jaxbMethodMarshaller = jaxbMethodContext.createMarshaller();
		} catch (JAXBException e1) {
			logger.error(e1.getMessage(), e1);
			return;
		} catch ( Exception e2 ) {
			logger.error(e2.getMessage(), e2);
			return;
		}
		try {
			jaxbMethodMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			compressedFile.putNextEntry(new ZipEntry(AnnotationWriter.SETTINGS_FILE));
			jaxbMethodMarshaller.marshal(method, compressedFile);
			AnnotationObjectCache.clearCache(AnnotationWriter.SETTINGS_FILE);
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} catch (JAXBException e2) {
			logger.error(e2.getMessage(), e2);
		} catch ( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}
		try {
			compressedFile.closeEntry();
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} catch ( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}		
	}

	/* writeDataToZip:  Writes a Data object to an open ZipOutputStream
	 * 
	 * @param Data : from the GRITS object model
	 * @param ZipOutputStream : open compressed file output stream for the archive
	 * 
	 */	
	public void writeDataToZip(Data data, ZipOutputStream compressedFile ) {
		JAXBContext jaxbDataContext = null;
		Marshaller jaxbDataMarshaller = null;
		try {
			//List<Class> contextList = new ArrayList<>(Arrays.asList(FilterUtils.filterClassContext));
			List<Class> contextList = new ArrayList<>(Arrays.asList(AnnotationReader.filterClassContext));
			contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
			contextList.add(FilterSetting.class);
			contextList.add(Data.class);
			jaxbDataContext = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
			jaxbDataMarshaller = jaxbDataContext.createMarshaller();
		} catch (JAXBException e1) {
			logger.error(e1.getMessage(), e1);
			return;
		} catch ( Exception e2 ) {
			logger.error(e2.getMessage(), e2);
			return;
		}
		try {
			jaxbDataMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			compressedFile.putNextEntry(new ZipEntry(AnnotationWriter.DATA_FILE));
			// testing not serializing the scanfeatures in the data object.
			HashMap<Integer, ScanFeatures> hm = data.getScanFeatures();
			data.setScanFeatures(null);
			jaxbDataMarshaller.marshal(data, compressedFile);
			AnnotationObjectCache.clearCache();
			data.setScanFeatures(hm);
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} catch (JAXBException e2) {
			logger.error(e2.getMessage(), e2);
		} catch ( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}
		try {
			compressedFile.closeEntry();
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} catch ( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}		
	}

	/* writeScanFeaturesToZip:  Writes ScanFeatures that are associated with each scan in the Data object to an open ZipOutputStream
	 * 
	 * @param Data : from the GRITS object model
	 * @param ZipOutputStream : open compressed file output stream for the archive
	 * @param String : the temporary path to the individual analyte to feature files generated by GELATO during annotation
	 * 
	 */	
	public void writeScanFeaturesToZip(Data data, ZipOutputStream compressedFile, String analyteFilesPath ) throws Exception {
		JAXBContext jaxbContextAnn = null;
		Marshaller jaxbMarshallerAnn = null;
		try {
			//	List<Class> contextList = new ArrayList<>(Arrays.asList(FilterUtils.filterClassContext));
			List<Class> contextList = new ArrayList<>(Arrays.asList(AnnotationReader.filterClassContext));
			contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
			contextList.add(FilterSetting.class);
			contextList.add(ScanFeatures.class);
			jaxbContextAnn = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));
			jaxbMarshallerAnn = jaxbContextAnn.createMarshaller();
		} catch (JAXBException e1) {
			logger.error(e1.getMessage(), e1);
			return;
		} catch ( Exception e2 ) {
			logger.error(e2.getMessage(), e2);
			return;
		}
		HashMap<String,String> idCheckerForMS1 = new HashMap<String,String>();
		try {
			if( data.getScans() != null ) {
				for(Integer scanId : data.getScans().keySet()){
					ScanFeatures features = data.getScanFeatures().get(scanId);
					if( features == null ) {
						continue;
					}
					try {
						compressedFile.putNextEntry(new ZipEntry(scanId+".xml"));
						jaxbMarshallerAnn.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						jaxbMarshallerAnn.marshal(features,compressedFile);
						AnnotationObjectCache.clearCache(scanId+".xml");
					} catch (IOException e1) {
						logger.error(e1.getMessage(), e1);
					} catch (JAXBException e2) {
						logger.error(e2.getMessage(), e2);
					} catch ( Exception ex ) {
						logger.error(ex.getMessage(), ex);
					}
					try {
						compressedFile.closeEntry();
						if(data.getAnnotatedScan().get(scanId) != null){
							for(String gog : data.getAnnotatedScan().get(scanId)){
								idCheckerForMS1.put(gog, "seen");
							}
						}
					} catch (IOException e1) {
						logger.error(e1.getMessage(), e1);
					} catch ( Exception ex ) {
						logger.error(ex.getMessage(), ex);
					}
				}
			}
		} catch ( Exception ex ) {
			logger.error(ex.getMessage(), ex);
			return;
		}

		try {
			deleteAnnotationsPerAnalyte(analyteFilesPath,idCheckerForMS1);
		} catch ( Exception ex ) {
			logger.error(ex.getMessage(), ex);
		}
	}

	/* getZipOutputStream:  Creates and opens a ZipOutputStream to write an archive file
	 * 
	 * @param String : the path to write the the archive
	 * @returns ZipOutputStream
	 * 
	 */	
	public static ZipOutputStream getZipOutputStream( String zipPath ) {
		// the zipPath better already have ".zip" appended!
		FileOutputStream ms_final;
		try {
			ms_final = new FileOutputStream(zipPath);
			ZipOutputStream compressedFile = new ZipOutputStream(ms_final);
			return compressedFile;
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/* getArchiveFilePath: Appends the file extension to the archive file
	 * 
	 * @param String : the path to the archive w/out the file extension
	 * @returns String : the full archive file with the extension
	 * 
	 */		
	public static String getArchiveFilePath( String _sPreArchiveFilePath ) {
		return _sPreArchiveFilePath + ".zip";
	}

	/* generateScansAnnotationFiles: "Master" method to create a complete archive
	 * Calls other methods to write appropriate data to the archive
	 * 
	 * @param String : the path to the temporary analyte-to-feature files created by Annotation tool
	 * @param Data : the filled Data object from the object model
	 * @param String : the destination path of the archive
	 * @param boolean : tells whether or not to write the DataHeader object
	 * @param boolean : tells whether or not to write the Method object
	 * @param boolean : tells whether or not to write the Data object
	 * @param boolean : tells whether or not to write the ScanFeatures by scan number
	 * 
	 * @throws IOException
	 * 
	 */		
	public void generateScansAnnotationFiles(String analyteFilesPath, Data data, String zipPath, 
			boolean bWriteHeader, boolean bWriteMethod, boolean bWriteData, boolean bWriteScanFeatures) throws IOException{
		// the zipPath better already have ".zip" appended!
		ZipOutputStream compressedFile = null;
		try {
			compressedFile = AnnotationWriter.getZipOutputStream(zipPath);
		}catch(Exception ex){
			logger.error("Error initializing zip output stream for path: " + zipPath, ex);
			return;
		}
		try {
			if( bWriteHeader ) {
				writeDataHeaderToZip(data.getDataHeader(), compressedFile);
			}
			if( bWriteMethod ) {
				writeMethodToZip(data.getDataHeader().getMethod(), compressedFile);
			}
			if( bWriteData ) {
				writeDataToZip(data, compressedFile);
			}
			if( bWriteScanFeatures ) {
				writeScanFeaturesToZip(data, compressedFile, analyteFilesPath);
			}
		}catch(Exception ex){
			logger.error("Exception in AnnotationWriter:generateScansAnnotationFiles." , ex);
		}
		try {
			compressedFile.close();
		}catch(Exception ex){
			logger.error("Error closing compressed file", ex);
		}
	}

	/* writeSingleScanToZipFile: Writes ScanFeatures for a particular scan to a pre-existing archive.
	 * Useful if data are updated during the use of GRITS or if external quantitation is added/modified
	 * 
	 * @param int : The scan number to update
	 * @param ScanFeatures : ScanFeatures that have been updated and should be written
	 * @param String : path to the archive
	 * 
	 */		
	public void writeSingleScanToZipFile(int scanNum, ScanFeatures scanFeatures, String zipFilePath){
		try{
			String zipFolder = new File(zipFilePath).getParentFile().getAbsolutePath();
			//List<Class> contextList = new ArrayList<>(Arrays.asList(FilterUtils.filterClassContext));
			List<Class> contextList = new ArrayList<>(Arrays.asList(AnnotationReader.filterClassContext));
			contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
			contextList.add(FilterSetting.class);
			contextList.add(ScanFeatures.class);
			JAXBContext jaxbContextAnn = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));    
			Marshaller jaxbMarshallerAnn = jaxbContextAnn.createMarshaller();
			String sXMLFile = scanNum + ".xml";
			File scanFile = new File(zipFolder + File.separator + sXMLFile);
			//write the file to the archive file
			jaxbMarshallerAnn.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//jaxbMarshallerAnn.marshal(data.getScanFeatures().get(scanId), compressedFile);
			jaxbMarshallerAnn.marshal(scanFeatures,scanFile);
			AnnotationObjectCache.clearCache(sXMLFile);

			if( ! scanFile.exists() ) {
				throw new IOException("New XML file doesn't exist!");
			}
			// first extract and backup the existing file
			//compress the annotations	
			ZipFile zipFile = new ZipFile(zipFilePath);
			// remove the existing file
			zipFile.removeFile(sXMLFile);

			// add the new file
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			zipFile.addFile(scanFile, parameters);
			scanFile.delete();
		}catch(Exception ex){
			logger.error("Exception in AnnotationWriter:writeSingleScanToZipFile." , ex);
		}
	}

	public void deleteAllTempFiles(String path) throws IOException{
		try{

			File dir = new File(path);
			for(String gog : dir.list()){
				File f = new File(gog);
				f.delete();
			}

		}catch(Exception ex){
			logger.error("Exception in deleteAllTempFiles." , ex);
		}

	}

	public void deleteAnnotationsPerAnalyte(String path,HashMap<String,String> ids) throws IOException{
		try{
			for(String id : ids.keySet()){
				try {
					File f = new File(path + File.separator + id +".xml");
					f.delete();
				} catch( Exception e ) {
					logger.error(e.getMessage(), e);
				}
			}

		}catch(Exception ex){
			logger.error("Exception in deleteAnnotationsPerAnalyte." , ex);
		}

	}

	/* writeDataHeaderToArchive: Writes DataHeader to a pre-existing archive.
	 * Useful if data are updated during the use of GRITS or if external quantitation is added/modified
	 * 
	 * @param DataHeader : The DataHeader to update
	 * @param String : path to the archive
	 * 
	 */		
	public void writeDataHeaderToArchive(DataHeader dHeader,String zipFilePath){
		try{
			String zipFolder = new File(zipFilePath).getParentFile().getAbsolutePath();
			//List<Class> contextList = new ArrayList<>(Arrays.asList(FilterUtils.filterClassContext));
			List<Class> contextList = new ArrayList<>(Arrays.asList(AnnotationReader.filterClassContext));
			contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
			contextList.add(FilterSetting.class);
			contextList.add(DataHeader.class);
			JAXBContext jaxbContextDataHeader = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));	    
			Marshaller jaxbMarshallerAnn = jaxbContextDataHeader.createMarshaller();
			File headerFile = new File(zipFolder + File.separator + AnnotationWriter.DATA_HEADER);
			//write the file to the archive file
			jaxbMarshallerAnn.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshallerAnn.marshal(dHeader,headerFile);
			AnnotationObjectCache.clearCache(AnnotationWriter.DATA_HEADER);
			if( ! headerFile.exists() ) {
				throw new IOException("New XML file doesn't exist!");
			}

			//compress the annotations	
			ZipFile zipFile = new ZipFile(zipFilePath);
			zipFile.removeFile(AnnotationWriter.DATA_HEADER);
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			zipFile.addFile(headerFile, parameters);
			headerFile.delete();
		} catch(Exception ex) {
			logger.error("Exception in AnnotationWriter:writeDataHeaderToArchive." , ex);
		}
	}

	/* writeDataToZip: Writes Data to a pre-existing archive.
	 * Useful if data are updated during the use of GRITS or if external quantitation is added/modified
	 * 
	 * @param Data : The Data to update
	 * @param String : path to the archive
	 * 
	 */		
	public void writeDataToZip( Data data, String zipFilePath ) {
		try{
			String zipFolder = new File(zipFilePath).getParentFile().getAbsolutePath();
			List<Class> contextList = new ArrayList<>(Arrays.asList(AnnotationReader.filterClassContext));
			contextList.addAll(Arrays.asList(FilterUtils.filterClassContext));
			contextList.add(FilterSetting.class);
			contextList.add(Data.class);
			JAXBContext jaxbContextData = JAXBContext.newInstance(contextList.toArray(new Class[contextList.size()]));    
			Marshaller jaxbMarshallerAnn = jaxbContextData.createMarshaller();
			jaxbMarshallerAnn.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			File dataFile = new File(zipFolder + File.separator + AnnotationWriter.DATA_FILE);
			//write the file to the archive file
			//jaxbMarshallerAnn.marshal(data.getScanFeatures().get(scanId), compressedFile);
			jaxbMarshallerAnn.marshal(data,dataFile);
			AnnotationObjectCache.clearCache(AnnotationWriter.DATA_FILE);
			if( ! dataFile.exists() ) {
				throw new IOException("New XML file doesn't exist!");
			}	
			
			//compress the annotations	
			ZipFile zipFile = new ZipFile(zipFilePath);
			zipFile.removeFile(AnnotationWriter.DATA_FILE);
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			zipFile.addFile(dataFile, parameters);
			dataFile.delete();
			
		} catch(Exception ex) {
			logger.error("Exception in AnnotationWriter:writeDataToZip." , ex);
		}
	}

	/* convertScanAnnotationFilesToGlycanAnnotationFiles
	 * 
	 * NOTE: this method must have been a utility method for Khalifeh's thesis because it isn't called at all within GRITS anymore.
	 *  
	 *
	public void convertScanAnnotationFilesToGlycanAnnotationFiles(String zipFileName,String outputPath){
		try{
			AnnotationReader reader = new AnnotationReader();
			Data data = reader.readDataWithoutFeatures(zipFileName);
			HashMap<Integer,Annotation> annotationIds = getAnnotationIds(data);
			for(Integer annId : annotationIds.keySet()){
				logger.debug("Now Processing Glycan " + annotationIds.get(annId).getStringId());
				List<Integer> scanIds = getScanIds(annotationIds.get(annId),data);
				ScansAnnotation gsa = new ScansAnnotation();
				for(Integer scanId : scanIds){
					ScanFeatures sf = reader.readScanAnnotation(zipFileName, scanId);
					gsa.setAnnotationId(annId);
					gsa.setStringAnnotationId(annotationIds.get(annId).getStringId();
					List<Feature> glycanFeatures = new ArrayList<Feature>();
					for(Feature feature : sf.getFeatures()){
						GlycanFeature f = (GlycanFeature)feature;
						if(f.getAnnotationId().equals(annId))
							glycanFeatures.add(f);
					}
					gsa.getScanAnnotations().put(scanId, glycanFeatures);	
				}
				logger.debug("Now done Glycan " + annotationIds.get(annId).getStringId());
				writeAnnotationsPerGlycan(gsa, outputPath);
			}
		}catch(Exception e){
			logger.error("Exception in AnnotationWriter:convertScanAnnotationFilesToGlycanAnnotationFiles." , e);
		}
	}
*/
	
	/* convertScanAnnotationFilesToAnnotationFilesManuallySelected
	 * 
	 * NOTE: this method must have been a utility method for Khalifeh's thesis because it isn't called at all within GRITS anymore.
	 *  
	 */
	public void convertScanAnnotationFilesToAnnotationFilesManuallySelected(String zipFileName,String outputPath){
		try{
			AnnotationReader reader = new AnnotationReader();
			Data data = reader.readDataWithoutFeatures(zipFileName);
			ScanFeatures scan1Fs = reader.readScanAnnotation(zipFileName, 1);
			HashMap<Long,List<Integer>> selectedAnnotations = new HashMap<Long,List<Integer>>();
			selectedAnnotations = getSelectedAnnotations(scan1Fs);
			for(Long key : selectedAnnotations.keySet())
				logger.debug(key);
			HashMap<Integer,Annotation> annotationIds = getAnnotationIds(data);
			for(Integer annId : annotationIds.keySet()){
				logger.debug("Now Processing Analyate " + annotationIds.get(annId).getStringId());
				List<Integer> scanIds = getScanIds(annotationIds.get(annId),data);
				ScansAnnotation gsa = new ScansAnnotation();
				//gsa = new GlycanScansAnnotation();
				for(Integer scanId : scanIds){
					logger.debug("going to check the selected glycans with: " + selectedAnnotations.keySet().size()+":"+annId+":"+scanId);
					if(!selectedAnalyte(selectedAnnotations,annId,scanId,data))
						continue;
					logger.debug("Now found a selected scan");
					ScanFeatures sf = reader.readScanAnnotation(zipFileName, scanId);
					gsa.setAnnotationId(annId);
					gsa.setStringAnnotationId(annotationIds.get(annId).getStringId());
					List<Feature> features = new ArrayList<Feature>();
					for(Feature feature : sf.getFeatures()){
						if(feature.getAnnotationId().equals(annId))
							features.add(feature);
					}
					gsa.getScanAnnotations().put(scanId, features);	

					//if the scan has subscans add them to the annotations
					if(data.getScans().get(scanId) !=  null && data.getScans().get(scanId).getSubScans().size() != 0){
						for(Integer subScanId : data.getScans().get(scanId).getSubScans()){
							logger.debug("Now found a Subscan");
							sf = reader.readScanAnnotation(zipFileName, subScanId);
							if(sf !=null ){
								logger.debug("^^Now found annotated SubScan^^");
								gsa.setAnnotationId(annId);
								gsa.setStringAnnotationId(annotationIds.get(annId).getStringId());
								features = new ArrayList<Feature>();
								for(Feature feature : sf.getFeatures()){
									if(feature.getAnnotationId().equals(annId))
										features.add(feature);
								}
								gsa.getScanAnnotations().put(subScanId, features);	
							}
						}
					}
				}
				logger.debug("Now done Glycan " + annotationIds.get(annId).getStringId());
				//check if this glycan got selected otherwise ignore it
				if(gsa.getScanAnnotations().keySet().size() != 0){
					logger.debug("Now writing annotation");
					writeAnnotationsPerAnalyte(gsa, outputPath);
				}
			}
		}catch(Exception e){
			logger.error("Exception in AnnotationWriter:convertScanAnnotationFilesToGlycanAnnotationFiles." , e);
		}
	}

	private boolean selectedAnalyte(HashMap<Long,List<Integer>>selectedAnnotations,int annId,int scanId,Data data){
		Long mz = Math.round(data.getScans().get(scanId).getPrecursor().getMz());
		logger.debug("mz is : " + mz);
		if(selectedAnnotations.get(mz)!=null){
			logger.debug("mz is found selected ");
			for(Integer id : selectedAnnotations.get(mz)){
				logger.debug("id: " + id + " annId: " + annId);
				if(id == annId){
					logger.debug("id: is equal to annId: ");
					return true;
				}else{
					logger.debug("id: is not equal to annId: ");
				}
			}
		}
		return false;
	}

	private HashMap<Long,List<Integer>> getSelectedAnnotations(ScanFeatures sf){
		HashMap<Long,List<Integer>> selectedScans = new HashMap<Long,List<Integer>>();
		for(Feature feature : sf.getFeatures()){
			for( FeatureSelection selection : feature.getFeatureSelections() ) {
				if(selection.getManuallySelected()){
					if(selectedScans.get(Math.round(feature.getMz())) == null){
						List<Integer> gogs = new ArrayList<Integer>();
						gogs.add(feature.getAnnotationId());
						selectedScans.put(Math.round(feature.getMz()), gogs);
						logger.debug("featureMz:" + feature.getMz());
					}else{
						selectedScans.get(Math.round(feature.getMz())).add(feature.getAnnotationId());
					}
				}
			}
		}
		logger.debug("selected scans = " + selectedScans.size());
		return selectedScans;

	}

	public HashMap<Integer,Annotation> getAnnotationIds(Data data){
		HashMap<Integer,Annotation> glycanAnnIds = new HashMap<Integer,Annotation>();
		for(Annotation ann : data.getAnnotation()){
			glycanAnnIds.put(ann.getId(), ann);
		}
		return glycanAnnIds;
	}

	public List<Integer> getScanIds(Annotation ann,Data data){
		List<Integer> ids = new ArrayList<Integer>();
		for(String scanId : ann.getScores().keySet()){
			ids.add(Integer.parseInt(scanId));
			//			if(data.getScans().get(Integer.parseInt(scanId)) !=  null && data.getScans().get(Integer.parseInt(scanId)).getSubScans().size() != 0)
			//				ids.addAll(data.getScans().get(Integer.parseInt(scanId)).getSubScans());
		}
		return ids;
	}

}
