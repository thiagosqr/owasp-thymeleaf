package com.github.thiagosqr.owasp.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Thiago Rios de Siqueira
 */
public class CsrfDialect extends AbstractDialect {

    private Supplier<HttpServletRequest> requestSupplier;

    public CsrfDialect(Supplier<HttpServletRequest> requestSupplier) {
        super();
        this.requestSupplier = requestSupplier;
    }

    public String getPrefix() {
        return "csrf";
    }

    @Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new CsrfLink(requestSupplier));
        return processors;
    }
}
