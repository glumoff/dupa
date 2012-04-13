/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dupa;

import java.io.File;

/**
 *
 * @author Alexander Glumoff
 */
public class DupFile extends File {

  protected String md5sum;

  public String getMd5sum() {
    return md5sum;
  }

  public void setMd5sum(String md5sum) {
    this.md5sum = md5sum;
  }
  
  public DupFile(String pathname) {
    super(pathname);
  }

  @Override
  public DupFile[] listFiles() {
    return (DupFile[]) super.listFiles();
  }
  
  
  
}
