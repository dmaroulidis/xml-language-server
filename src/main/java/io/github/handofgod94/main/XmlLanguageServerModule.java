package io.github.handofgod94.main;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import io.github.handofgod94.common.DocumentManagerFactory;
import io.github.handofgod94.lsp.diagnostic.DiagnosticErrorHandler;
import io.github.handofgod94.lsp.diagnostic.XmlDiagnosticServiceFactory;
import io.github.handofgod94.lsp.hover.AttributeHover;
import io.github.handofgod94.lsp.hover.TagHover;
import io.github.handofgod94.lsp.hover.XmlHover;
import io.github.handofgod94.lsp.hover.XmlHoverFactory;
import io.github.handofgod94.lsp.hover.provider.XmlHoverProviderFactory;
import io.github.handofgod94.schema.resolve.SchemaResolver;
import io.github.handofgod94.schema.resolve.XsdSchemaResolver;

/**
 * Google guice module for Language server.
 * It defines all the binding configuration required for object
 * instantiation.
 */
public class XmlLanguageServerModule extends AbstractModule {

  @Override
  protected void configure() {

    // Includes bindings for DTD and XSD documents.
    bind(SchemaResolver.class).annotatedWith(Names.named("Xsd")).to(XsdSchemaResolver.class);

    // Bindings for concrete class
    bind(DiagnosticErrorHandler.class);

    // FactoryBuilders for assisted injections
    install(new FactoryModuleBuilder().build(DocumentManagerFactory.class));
    install(new FactoryModuleBuilder().build(XmlDiagnosticServiceFactory.class));
    install(new FactoryModuleBuilder().build(XmlHoverProviderFactory.class));
    install(new FactoryModuleBuilder()
        .implement(XmlHover.class, Names.named("Tag"), TagHover.class)
        .implement(XmlHover.class, Names.named("Attribute"), AttributeHover.class)
        .build(XmlHoverFactory.class));
  }
}
