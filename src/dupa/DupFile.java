package dupa;

import dupa.utils.HashDuplicatesList;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Glumoff
 */
public class DupFile {

  protected String name;
  protected long size;
  protected String hash = "";

  public DupFile(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getSize() {
    return size;
  }

  public String getSizeStr() {
    return String.format("%d", size);
  }

  public void setSize(long size) {
    this.size = size;
  }

  @Override
  public String toString() {
    return String.format("%s (%d b)", name, size);
  }

  private String makeFileHash(String alg) throws NoSuchAlgorithmException {
    return makeFileHash(alg, -1);
  }

  private String makeFileHash(String alg, long firstBytes) throws NoSuchAlgorithmException {
    String hashStr = "";
    MessageDigest md = MessageDigest.getInstance(alg);
    md.reset();
    try {
      BufferedInputStream in = new BufferedInputStream(new FileInputStream(getName()));
      try {
        int theByte;
        long pos = 0;
        while ((theByte = in.read()) != -1) {
          md.update((byte) theByte);
          pos++;
          if ((firstBytes > 0) && (pos >= firstBytes)) {
            break; // TODO: move this if to while condition
          }
        }
        in.close();
      } catch (IOException ex) {
        Logger.getLogger(HashDuplicatesList.class.getName()).log(Level.SEVERE, null, ex);
      }
      byte messageDigest[] = md.digest();
      StringBuilder hexString = new StringBuilder();
      for (int i = 0; i < messageDigest.length; i++) {
        String hex = Integer.toHexString(0xFF & messageDigest[i]);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      hashStr = hexString.toString();

    } catch (FileNotFoundException ex) {
      Logger.getLogger(HashDuplicatesList.class.getName()).log(Level.SEVERE, null, ex);
    }
    return hashStr;
  }

  public String getMd5(long firstBytes) {
    String hashStr = hash;
    if ((hashStr.equals("") && (firstBytes <= 0)) || (firstBytes > 0)) {
      try {
        hashStr = makeFileHash("MD5", firstBytes);
      } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(DupFile.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return hashStr;
  }

  public String getMd5() {
    return getMd5(0);
  }
}