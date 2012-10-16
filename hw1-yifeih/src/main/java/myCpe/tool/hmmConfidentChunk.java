package myCpe.tool;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.ConfidenceChunker;

import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Strings;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.resource.ResourceInitializationException;
import myCpe.tool.webCheck;
public class hmmConfidentChunk {


    static final int MAX_N_BEST_CHUNKS = 8;
    static private File modelFile;
    static private ConfidenceChunker chunker;
    public hmmConfidentChunk(String text) throws ResourceInitializationException,Exception {
      if(modelFile == null){
        modelFile = new File(text);
        chunker = (ConfidenceChunker) AbstractExternalizable.readObject(modelFile);
        System.out.println("init chuner!");
      }
    }
    public Map<Integer, Integer> getgene(String text) throws IOException  {
            webCheck checker = new webCheck();
            Map<Integer, Integer> begin2end = new HashMap<Integer, Integer>();
            char[] cs = text.toCharArray();
            double upper = 0.7;
            double lower = 0.4;
            Iterator<Chunk> it = chunker.nBestChunks(cs,0,cs.length,MAX_N_BEST_CHUNKS);
            for (int n = 0; it.hasNext(); ++n) {
                Chunk chunk = it.next();
                double conf = java.lang.Math.pow(2.0,chunk.score());
                if (conf>upper){
                  begin2end.put(chunk.start(), chunk.end());
                }
                else if(conf>lower){
                  try {
                    if(checker.check(text.substring(chunk.start(), chunk.end())))
                      begin2end.put(chunk.start(), chunk.end());//System.out.println("yes");
                  } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("wrong");
                  } 
                  
                }
                
            }
            return begin2end;

    }
}

