package dupa;

import dupa.events.*;
import dupa.utils.DuplicatesList;
import dupa.utils.FileWalker;
import dupa.utils.HashDuplicatesList;
import java.util.ArrayList;

/**
 *
 * @author Alexander Glumoff
 */
public class DupFinder implements DupFileListener {

  private FileList allFiles;
  private DuplicatesList dupsOnSize;
  private DuplicatesList dupsOnName;
  private HashDuplicatesList dupsOnHash;
  private ArrayList<String> searchPathes = new ArrayList<>();
  private DupFileEventProducer eventProducer;

  public DupFinder() {
    allFiles = new FileList();
    dupsOnSize = new DuplicatesList();
    dupsOnName = new DuplicatesList();
    dupsOnHash = new HashDuplicatesList();
    eventProducer = new DupFileEventProducer();
  }

  public void find() {
    FileWalker fw = new FileWalker();
    //DuplicatesList dups = new DuplicatesList();
    eventProducer.addListener(this);
    fw.setEventProducer(eventProducer);
    for (String path : searchPathes) {
      fw.walk(path);
    }
  }

  public void addPath(String path) {
    searchPathes.add(path);
  }

  public HashDuplicatesList getDupsOnHash() {
    return dupsOnHash;
  }

  public void setDupsOnHash(HashDuplicatesList dupsOnHash) {
    this.dupsOnHash = dupsOnHash;
  }

  public DuplicatesList getDupsOnName() {
    return dupsOnName;
  }

  public void setDupsOnName(DuplicatesList dupsOnName) {
    this.dupsOnName = dupsOnName;
  }

  public DuplicatesList getDupsOnSize() {
    return dupsOnSize;
  }

  public void setDupsOnSize(DuplicatesList dupsOnSize) {
    this.dupsOnSize = dupsOnSize;
  }

  @Override
  public void NewFileFound(FileFoundEvent ev) {
    //System.out.println("File found: " + ev.getFoundedFile());
    
    DupFile F = ev.getFile();
    allFiles.add(F);
    Duplicate dup = dupsOnSize.collect(F);
    if (dup != null) {
      eventProducer.fireSizeDuplicateFound(dup);
    }
  }

  @Override
  public void SizeDuplicateFound(SizeDuplicateFoundEvent ev) {
//    System.out.println("Size duplicate found: " + ev.getDup());
    Duplicate dup = ev.getDup();
    // Assume that last file is last founded file
    DupFile F = dup.get(dup.size() - 1); // TODO: perform null testing
    Duplicate hdup = dupsOnHash.collect(F);
    //System.out.println("dupsOnHash.collect = " + hdup);
    if (hdup != null) {
      eventProducer.fireHashDuplicateFound(F);
    }
    if (dup.size() == 2) { // this means we`re firing HashEvent first time
      F = dup.get(0); // TODO: perform null testing
      hdup = dupsOnHash.collect(F);
      //System.out.println("dupsOnHash.collect = " + hdup);
      if (hdup != null) {
        eventProducer.fireHashDuplicateFound(F);
      }
    }
  }

  @Override
  public void HashDuplicateFound(HashDuplicateFoundEvent ev) {
    System.out.println("Exact duplicate found: " + ev.getFile());
  }
}
