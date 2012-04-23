package dupa.utils;

import dupa.DupFile;
import dupa.Duplicate;
import java.util.HashMap;

/**
 *
 * @author Alexander Glumoff
 */
public class DuplicatesList extends HashMap<String, Duplicate> {

  public Duplicate collect(DupFile F) {
    return collect(F.getSizeStr(), F);
  }
  public Duplicate collect(String key, DupFile F) {
    Duplicate dup = this.get(key);
    if (dup != null) {
      dup.add(F);
    } else {
      dup = put(key, new Duplicate(F));
    }
    return dup;
  }
}
