package dupa.events;

import dupa.Duplicate;
import java.util.EventObject;

/**
 *
 * @author glumoff
 */
public class DuplicateFoundEvent extends EventObject {

  private Duplicate dup;

  public Duplicate getDup() {
    return dup;
  }
  
  public DuplicateFoundEvent(Object source, Duplicate dup) {
    super(source);
    this.dup = dup;
  }
}