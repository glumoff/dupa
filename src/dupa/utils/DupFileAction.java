/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dupa.utils;

import dupa.DupFile;
//import java.io.File;

//import java.io.File;

//import java.io.File;


/**
 *
 * @author Alexander Glumoff
 */
public class DupFileAction extends FindFileAction {

  //private ArrayList<File> candidatesList;
  private DuplicatesList list;
  
  public DupFileAction(DuplicatesList list) {
    this.list = list;
    //this.candidatesList = candidatesList;
  }
  
  //@Override
  public void run(DupFile file) {
    //System.out.print(file.getPath() + ": " + file.length() + "\n");
    list.collect(file);
  }

}
