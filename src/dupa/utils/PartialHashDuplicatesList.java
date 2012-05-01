package dupa.utils;

import dupa.DupFile;
import dupa.Duplicate;

/**
 *
 * @author Alexander Glumoff
 */
public class PartialHashDuplicatesList extends DuplicatesList {

  protected long firstBytes;

  public PartialHashDuplicatesList() {
    this(1024); // 1Kb by default
  }

  public PartialHashDuplicatesList(long firstBytes) {
    this.firstBytes = firstBytes;
  }

  @Override
  public Duplicate collect(DupFile F) {
    String hash = F.getMd5(firstBytes);
    return collect(hash, F);
  }
}
