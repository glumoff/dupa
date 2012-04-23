package dupa.events;

import dupa.DupFile;
import dupa.Duplicate;
import java.util.ArrayList;

/**
 *
 * @author glumoff
 */
public class DupFileEventProducer {

  private ArrayList<DupFileListener> listeners = new ArrayList<>();

  public void addListener(DupFileListener listener) {
    listeners.add(listener);
  }

  public DupFileListener[] getListeners() {
    return listeners.toArray(new DupFileListener[listeners.size()]);
  }

  public void removeListener(DupFileListener listener) {
    listeners.remove(listener);
  }

  public void fireNewFileFound(DupFile file) {
    FileFoundEvent ev = new FileFoundEvent(this, file);
    for (DupFileListener listener : listeners) {
      listener.NewFileFound(ev);
    }
  }

  public void fireSizeDuplicateFound(DupFile file) {
    SizeDuplicateFoundEvent ev = new SizeDuplicateFoundEvent(this, file);
    for (DupFileListener listener : listeners) {
      listener.SizeDuplicateFound(ev);
    }
  }
  public void fireHashDuplicateFound(Duplicate dup) {
    HashDuplicateFoundEvent ev = new HashDuplicateFoundEvent(this, dup);
    for (DupFileListener listener : listeners) {
      listener.HashDuplicateFound(ev);
    }
  }
  
  
}
