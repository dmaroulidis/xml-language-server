package io.github.handofgod94.lsp.hover;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.eclipse.lsp4j.Hover;
import org.eclipse.lsp4j.MarkupContent;
import org.eclipse.lsp4j.MarkupKind;

/**
 * Hover information for attributes.
 */
public class AttributeHover implements XmlHover {

  private final String wordHovered;
  private final String parentTagName;

  @Inject
  AttributeHover(@Assisted("Tag") String wordHovered, @Assisted("Attribute") String parentTagName) {
    this.wordHovered = wordHovered;
    this.parentTagName = parentTagName;
  }

  @Override
  public Hover getHover() {
    MarkupContent content = new MarkupContent();
    content.setKind(MarkupKind.PLAINTEXT);
    content.setValue(wordHovered + ":" + parentTagName);
    Hover hover = new Hover(content);
    return hover;
  }
}
