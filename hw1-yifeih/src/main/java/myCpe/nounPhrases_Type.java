
/* First created by JCasGen Sat Oct 13 21:35:55 EDT 2012 */
package myCpe;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat Oct 13 21:35:57 EDT 2012
 * @generated */
public class nounPhrases_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (nounPhrases_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = nounPhrases_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new nounPhrases(addr, nounPhrases_Type.this);
  			   nounPhrases_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new nounPhrases(addr, nounPhrases_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = nounPhrases.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("myCpe.nounPhrases");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "myCpe.nounPhrases");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "myCpe.nounPhrases");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_nouns;
  /** @generated */
  final int     casFeatCode_nouns;
  /** @generated */ 
  public String getNouns(int addr) {
        if (featOkTst && casFeat_nouns == null)
      jcas.throwFeatMissing("nouns", "myCpe.nounPhrases");
    return ll_cas.ll_getStringValue(addr, casFeatCode_nouns);
  }
  /** @generated */    
  public void setNouns(int addr, String v) {
        if (featOkTst && casFeat_nouns == null)
      jcas.throwFeatMissing("nouns", "myCpe.nounPhrases");
    ll_cas.ll_setStringValue(addr, casFeatCode_nouns, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public nounPhrases_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_nouns = jcas.getRequiredFeatureDE(casType, "nouns", "uima.cas.String", featOkTst);
    casFeatCode_nouns  = (null == casFeat_nouns) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_nouns).getCode();

  }
}



    