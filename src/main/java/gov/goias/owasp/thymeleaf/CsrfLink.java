package gov.goias.owasp.thymeleaf;

import org.owasp.csrfguard.CsrfGuard;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

/**
 * <b>Título:</b> CsrfLink
 * <br><b>Descrição:</b> Define componente HTML que<br>
 * substitui atributo do elemento usado com novo link habilitando<br>
 * suporte a CSRF <br>
 * Ex: &lt;a href="aluno/1/delete" csrf:token_for="href" .../&gt;
 * <br><b>Copyright:</b> Copyright(c) 2015
 * <br><b>Empresa:</b> SEGPLAN
 * @author Thiago Rios de Siqueira
 */
public class CsrfLink extends AbstractAttrProcessor {

    private Supplier<HttpServletRequest> requestSupplier;

    public CsrfLink(Supplier<HttpServletRequest> requestSupplier) {
        super("token_for");
        this.requestSupplier = requestSupplier;
    }

    public int getPrecedence() {
        // A value of 10000 is higher than any attribute in the
        // SpringStandard dialect. So this attribute will execute
        // after all other attributes from that dialect, if in the
        // same tag.
        return 10000;
    }


    @Override
    protected ProcessorResult processAttribute(final Arguments arguments, final Element element, final String attributeName){

        String attr2Change = element.getAttributeValue(attributeName);
        String attrOrigVal = element.getAttributeValue(attr2Change);

        CsrfGuard csrfGuard = CsrfGuard.getInstance();
        String tokenValue = csrfGuard.getTokenValue(requestSupplier.get(), buildUri(attrOrigVal));
        String tokenName = csrfGuard.getTokenName();

        element.setAttribute(attr2Change, String.format("%s?%s=%s", attrOrigVal, tokenName, tokenValue));

        return ProcessorResult.ok();
    }

    public String buildUri(String page) {
        String uri = page;

        String contextPath = requestSupplier.get().getContextPath();

        if (!page.startsWith("/")) {
            uri = contextPath + "/" + page;
        }

        return uri;
    }

}
