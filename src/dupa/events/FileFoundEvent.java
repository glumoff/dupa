package dupa.events;

import dupa.DupFile;
import java.util.EventObject;

/**
 *
 * @author glumoff
 */
public class FileFoundEvent extends EventObject {

  private DupFile foundedFile;

  public FileFoundEvent(Object source, DupFile dfile) {
    super(source);
    this.foundedFile = dfile;
  }

  public DupFile getFile() {
    return foundedFile;
  }
}
