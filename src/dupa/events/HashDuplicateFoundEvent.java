package dupa.events;

import dupa.DupFile;

/**
 *
 * @author glumoff
 */
public class HashDuplicateFoundEvent extends FileFoundEvent {

  public HashDuplicateFoundEvent(Object source, DupFile dfile) {
    super(source, dfile);
  }

}