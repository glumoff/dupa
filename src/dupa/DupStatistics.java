/*
 * Copyright (C) 2012 Alexander Glumoff
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dupa;

/**
 *
 * @author Alexander Glumoff
 */
public class DupStatistics {
  protected long filesFound;
  protected long sizeDupsFound;
  protected long hashDupsFound;

  public long getFilesFound() {
    return filesFound;
  }

  public void setFilesFound(long filesFound) {
    this.filesFound = filesFound;
  }

  public long getHashDupsFound() {
    return hashDupsFound;
  }

  public void setHashDupsFound(long hashDupsFound) {
    this.hashDupsFound = hashDupsFound;
  }

  public long getSizeDupsFound() {
    return sizeDupsFound;
  }

  public void setSizeDupsFound(long sizeDupsFound) {
    this.sizeDupsFound = sizeDupsFound;
  }
}
