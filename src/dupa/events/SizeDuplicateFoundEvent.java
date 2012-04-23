package dupa.events;

import dupa.Duplicate;

/**
 *
 * @author glumoff
 */
public class SizeDuplicateFoundEvent extends DuplicateFoundEvent {

  public SizeDuplicateFoundEvent(Object source, Duplicate dup) {
    super(source, dup);
  }
}