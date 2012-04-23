package dupa.utils;

import dupa.DupFile;
import dupa.events.DupFileEventProducer;
import java.io.File;
//import java.io.File;

/**
 *
 * @author Alexander Glumoff
 */
public class FileWalker {

  private DupFileEventProducer eventProducer;

  public void walk(String parentPath) {
    File parentFile = new File(parentPath);
    if (parentFile.isDirectory()) {
      if (parentFile.canRead()) {
        String[] fList = parentFile.list();
        if (!parentPath.endsWith(File.separator)) {
          parentPath += File.separator;
        }
        for (String fname : fList) {
          File F = new File(parentPath + fname);
          if (F.isDirectory()) {
            walk(F.getAbsolutePath());
          } else {
            DupFile df = new DupFile(F.getAbsolutePath());
            df.setSize(F.length());
            eventProducer.fireNewFileFound(df);
          }
        }
      }
    }
  }

  public void setEventProducer(DupFileEventProducer eventProducer) {
    this.eventProducer = eventProducer;
  }
}