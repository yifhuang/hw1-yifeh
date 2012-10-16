package myCpe;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import myCpe.line;
import myCpe.tool.PosTagNamedEntityRecognizer;
import myCpe.tool.hmmChunk;
import myCpe.tool.hmmConfidentChunk;
/**
 * Example annotator that detects room numbers using Java 1.4 regular expressions.
 */
public class getSentence extends JCasAnnotator_ImplBase {
  //private Pattern mYorktownPattern = Pattern.compile("[P](\\d)*\\s");
  private Pattern mYorktownPattern = Pattern.compile("([^ ]*)[ ](.*)");//add[\n] at the end when single

  /**
   * @throws AnalysisEngineProcessException 
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    PosTagNamedEntityRecognizer NounGetter;
    Map<Integer, Integer> tmpMap = null;
    /*try {
    NounGetter = new PosTagNamedEntityRecognizer();
  } catch (ResourceInitializationException e) {
    throw  new AnalysisEngineProcessException(e);
  }*/
    hmmConfidentChunk myChunk;
    try {
      myChunk = new hmmConfidentChunk("/Users/Yitei/Documents/workspace/11791/hw1-yifeih/src/main/resources/data/ne-en-bio-genetag.HmmChunker");
    } catch (ResourceInitializationException e) {
      throw  new AnalysisEngineProcessException(e);
    } catch (Exception e) {
      throw  new AnalysisEngineProcessException(e);
    }
    // get document text
   String docText = aJCas.getDocumentText();
    // search for Yorktown room numbers
    Matcher matcher = mYorktownPattern.matcher(docText);
    while (matcher.find()) {
      // found one - create annotation
      line annotation = new line(aJCas);
      nounPhrases nouns = null;
      annotation.setBegin(matcher.start(2));
      annotation.setEnd(matcher.end(2));
      //System.out.println(matcher.end(1)-matcher.start(1));
      annotation.setId(matcher.group(1));
      annotation.setSentence(matcher.group(2));
      annotation.addToIndexes();
      //tmpMap = NounGetter.getGeneSpans(matcher.group(2));
      try {
        tmpMap = myChunk.getgene(matcher.group(2));
      } catch (IOException e) {
        // TODO Auto-generated catch block
        throw  new AnalysisEngineProcessException(e);
      } 
      Iterator<Entry<Integer, Integer>> it = tmpMap.entrySet().iterator();
      
      //look for space
      String curSentence = matcher.group(2);
      int[] spaceBeforeAt = new int[curSentence.length()+1];
      int spaceBefore = 0;
      for(int i=0;i<curSentence.length();i++){
        spaceBeforeAt[i] = spaceBefore;
        if(curSentence.charAt(i) == ' ')
          spaceBefore++;
      }
      //look for space end
      while   (it.hasNext()){    
        nouns = new nounPhrases(aJCas);
        Map.Entry entry = (Map.Entry) it.next()   ;    
        Integer start = (Integer)entry.getKey();// + matcher.start(2);    
        Integer end = (Integer)entry.getValue();// + matcher.start(2);
        nouns.setNouns(matcher.group(2).substring(start, end));
        nouns.setBegin(start-spaceBeforeAt[start]);
        nouns.setEnd(end-1-spaceBeforeAt[end-1]);
        nouns.setId(matcher.group(1));
        nouns.addToIndexes(); 
      }  
    }
  }

}

