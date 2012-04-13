/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dupa.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import dupa.DupFile;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Alexander Glumoff
 */
public class DuplicatesList {

  private HashMap<Long, ArrayList<DupFile>> candidates;
  private HashMap<String, ArrayList<DupFile>> duplicates;

  public DuplicatesList() {
    this.candidates = new HashMap<Long, ArrayList<DupFile>>();
    this.duplicates = new HashMap<String, ArrayList<DupFile>>();
  }

//  public void DuplicatesList() {
//    candidates = new HashMap<>();
//    duplicates = new HashMap<>();
//  }
//  @Override
//  public boolean add(File f) {
//    
//    return super.add(f);
//  }
//  private File findSize(long size) {
//    File f = null;
//    for (File file : this) {
//      //if (file.length() == size)
//    }
//    return f;
//  }
  public boolean collect(DupFile f) {
    Long fsize = f.length();
    if (this.candidates.containsKey(fsize)) {
      this.candidates.get(fsize).add(f);
    } else {
      ArrayList<DupFile> list = new ArrayList<DupFile>();
      list.add(f);
      this.candidates.put(fsize, list);
    }
    return true;//super.put(Long.valueOf(f.length()), );
  }

  public void cleanUp() {
    Set set = this.candidates.entrySet();
    Iterator it = set.iterator();
    while (it.hasNext()) {
      Map.Entry<Long, ArrayList<DupFile>> me = (Map.Entry<Long, ArrayList<DupFile>>) it.next();
      if (me.getValue().size() < 2) {
        it.remove();
      }
    }
  }

  public void checkCandidates(HashSumFileAction hashSumFileAction) {
    Set set = this.candidates.entrySet();
    Iterator it = set.iterator();
    while (it.hasNext()) {
      Map.Entry<Long, ArrayList<DupFile>> me = (Map.Entry<Long, ArrayList<DupFile>>) it.next();
      Iterator it2 = me.getValue().iterator();
      while (it2.hasNext()) {
        DupFile f = (DupFile) it2.next();
        String md5sum = "";
        try {
          // calc hash
          md5sum = makeFileHash(f, "MD5");
          //System.out.println(f.getPath() + ": " + md5sum);
        } catch (NoSuchAlgorithmException ex) {
          Logger.getLogger(DuplicatesList.class.getName()).log(Level.SEVERE, null, ex);
        }
        // add to dups
        //Long fsize = f.length();
        if (this.duplicates.containsKey(md5sum)) {
          this.duplicates.get(md5sum).add((DupFile) f);
        } else {
          ArrayList<DupFile> list = new ArrayList<DupFile>();
          list.add((DupFile) f);
          this.duplicates.put(md5sum, list);
        }
      }
      //cleanUp dups
      Set set2 = this.duplicates.entrySet();
      Iterator it3 = set2.iterator();
      while (it3.hasNext()) {
        Map.Entry<Long, ArrayList<DupFile>> me2 = (Map.Entry<Long, ArrayList<DupFile>>) it3.next();
        if (me2.getValue().size() < 2) {
          it3.remove();
        }
      }
    }
  }

  private String makeFileHash(DupFile f, String alg) throws NoSuchAlgorithmException {
    String md5sum = "";
    MessageDigest md = MessageDigest.getInstance(alg);
    md.reset();
    try {
      BufferedInputStream in = new BufferedInputStream(new FileInputStream(f.getPath()));
      int theByte = 0;
      try {
        while ((theByte = in.read()) != -1) {
          md.update((byte) theByte);
        }
        in.close();
      } catch (IOException ex) {
        Logger.getLogger(DuplicatesList.class.getName()).log(Level.SEVERE, null, ex);
      }
      byte messageDigest[] = md.digest();
      StringBuffer hexString = new StringBuffer();
      for (int i = 0; i < messageDigest.length; i++) {
        String hex = Integer.toHexString(0xFF & messageDigest[i]);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      md5sum = hexString.toString();

    } catch (FileNotFoundException ex) {
      Logger.getLogger(DuplicatesList.class.getName()).log(Level.SEVERE, null, ex);
    }
    return md5sum;
  }

  public ArrayList<DupFile> toList() {
    ArrayList<DupFile> list = new ArrayList<>();
    Set set = this.duplicates.entrySet();
    Iterator it = set.iterator();
    while (it.hasNext()) {
      Map.Entry<String, ArrayList<DupFile>> me = (Map.Entry<String, ArrayList<DupFile>>) it.next();
      Iterator it2 = me.getValue().iterator();
      while (it2.hasNext()) {
        DupFile f = (DupFile) it2.next();
        //f.setMd5sum(me.getKey());
        list.add(f);
      }
    }
    return list;
  }

  public HashMap<String, ArrayList<DupFile>> getHashMap() {
    return this.duplicates;
  }
}
