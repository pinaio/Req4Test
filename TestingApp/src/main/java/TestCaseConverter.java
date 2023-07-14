import Entities.Testcase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
    public class TestCaseConverter implements Converter {

        @Inject
        private TestSystem testSystem;

        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }

            try {
                Long id = Long.valueOf(value);
                return testSystem.findTestCase(id);
            } catch (NumberFormatException e) {
                throw new ConverterException("The value is not a valid Testcase ID: " + value, e);
            }
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if (value == null) {
                return "";
            }

            if (value instanceof Testcase) {
                Long id = ((Testcase) value).getId();
                return (id != null) ? String.valueOf(id) : null;
            } else {
                throw new ConverterException("The value is not a valid Testcase instance: " + value);
            }
        }

}


