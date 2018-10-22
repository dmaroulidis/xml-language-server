package io.github.handofgod94.common;

import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.Range;
import org.eclipse.lsp4j.TextDocumentItem;

/**
 * Singleton document manager. Provides functionaloty to handle document querying.
 * This is for directly working on documentItem and not on actual xml content.
 */

public class DocumentManager {

  private static DocumentManager instance;

  // documentItem on which accessor wants to query
  private TextDocumentItem documentItem;

  // full document text as array of strings
  private String[] documentLines = null;

  private DocumentManager() {
    // Empty constructor for document manager
  }

  public void init(TextDocumentItem documentItem) {
    this.documentItem = documentItem;
    this.documentLines = XmlUtil.getDocumentLines.apply(documentItem.getText());
  }

  /**
   * Retrive singleton instance of DocumentManager
   * @param
   * @return Instnace of DocumenManager
   */
  public static DocumentManager getInstance() {
    if (instance == null) {
      return new DocumentManager();
    } else {
      return instance;
    }
  }

  /**
   * Get string between a given range.
   *
   * @param range Range describing the start and end position
   * @return string having contents of the document between range.
   */
  public String getStringBetweenRange(Range range) {
    String word = "";

    // Initialize start and end position
    Position startPosition = range.getStart();
    Position endPosition = range.getEnd();

    int startLine = startPosition.getLine();
    int startColumn = startPosition.getCharacter();
    int endLine = endPosition.getLine();
    int endColumn = endPosition.getCharacter();

    if (startLine != endLine) {
      // TODO: If range is between multiple lines
    } else if (startLine > 0) {
      String line = documentLines[startLine];
      if ((line.length() > 0)
        && (endColumn < line.length())
        && (startColumn < endColumn)) {
        word = line.substring(startColumn, endColumn);
      }
    }

    return word;
  }

  /**
   * Returns a range containing boundary of a word from current position.
   * Inclusive of starting and excludes end
   *
   * @param position position in the document
   * @return Range having starting position and ending position of word
   */
  public Range getWordRangeAt(Position position) {
    // Current position
    String line = documentLines[position.getLine()];
    int column = position.getCharacter();

    int startColumn = column;
    int endColumn = column;

    Position start = new Position(position.getLine(), startColumn);
    Position end = new Position(position.getLine(), endColumn);
    Range range = new Range(start, end);

    if ((column < line.length()) && (Character.isJavaIdentifierPart(line.charAt(column)))) {
      // traverse both left and right side of character to get word
      // left side
      for (int i = column; i > 0 && Character.isJavaIdentifierPart(line.charAt(i)); --i) {
        startColumn = i;
      }

      // right side
      for (int i = column + 1;
           i < line.length() && Character.isJavaIdentifierPart(line.charAt(i));
           ++i) {
        endColumn = i;
      }
      start.setCharacter(startColumn);
      end.setCharacter(endColumn + 1);
    }

    return range;
  }

  /**
   * Returns character at particular position in the document.
   *
   * @param position Position in the document
   * @return Character at that position
   */
  public char getCharAt(Position position) {
    int lineNo = position.getLine();
    int column = position.getCharacter();
    return documentLines[lineNo].charAt(column);
  }

  /**
   * Get line in string format at given line number from document.
   * @param lineNo line number in the document (0 based index)
   * @return String having contents of the line
   */
  public String getLineAt(int lineNo) {
    return documentLines[lineNo];
  }

  /**
   * @return the documentLines
   */
  public String[] getDocumentLines() {
    return documentLines;
  }
}