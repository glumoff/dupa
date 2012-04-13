package dupa.utils;

import dupa.DupFile;
//import java.io.File;

/**
 *
 * @author Alexander Glumoff
 */
public class FileWalker {

  private FindFileAction defaultAction;

  public FindFileAction getAction() {
    return defaultAction;
  }

  public void setAction(FindFileAction defaultAction) {
    this.defaultAction = defaultAction;
  }

  public void walk(DupFile dir) {
    if (dir.isDirectory()) {
      if (dir.canRead()) {
        String[] FilesList = dir.list();
        for (String fname : FilesList) {
          DupFile F = new DupFile(fname);
          if (F.isDirectory()) {
            walk(F);
          } else {
            doAction(F);
          }
        }
      }
    }
  }

  private boolean doAction(DupFile F) {
    boolean res = true;
    getAction().run(F);
    return res;
  }
}