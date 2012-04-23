package dupa.events;

/**
 *
 * @author glumoff
 */
public interface DupFileListener {

  public void NewFileFound(FileFoundEvent ev);

  public void SizeDuplicateFound(SizeDuplicateFoundEvent ev);
  
  public void HashDuplicateFound(HashDuplicateFoundEvent ev);
}
