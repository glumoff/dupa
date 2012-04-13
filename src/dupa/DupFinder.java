package dupa;

import dupa.utils.*;
import java.util.ArrayList;


/**
 *
 * @author Alexander Glumoff
 */
public class DupFinder {

  private ArrayList<DupFile> searchPathes = new ArrayList<>();

  public DuplicatesList find() {
    DuplicatesList dups = findCandidates();
    dups.checkCandidates(new HashSumFileAction());
    return dups;
  }

  public void addPath(String path) {
    searchPathes.add(new DupFile(path));
  }

  private DuplicatesList findCandidates() {
    FileWalker fw = new FileWalker();
    DuplicatesList dups = new DuplicatesList();
    fw.setAction(new DupFileAction(dups));
    for (DupFile f : searchPathes) {
      fw.walk(f);
    }
    dups.cleanUp();
    return dups;
  }
}
