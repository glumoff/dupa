package dupa.events;

import dupa.DupFile;

/**
 *
 * @author glumoff
 */
public class SizeDuplicateFoundEvent extends FileFoundEvent {

  public SizeDuplicateFoundEvent(Object source, DupFile dfile) {
    super(source, dfile);
  }
}