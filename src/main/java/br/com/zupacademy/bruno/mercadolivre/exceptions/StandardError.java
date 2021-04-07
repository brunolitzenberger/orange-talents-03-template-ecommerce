package br.com.zupacademy.bruno.mercadolivre.exceptions;

import java.util.ArrayList;
import java.util.List;

public class StandardError {

	private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldMessage> fieldErrors = new ArrayList<>();

	public void addError(String message) {
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message) {
        FieldMessage fieldError = new FieldMessage(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldMessage> getErrors() {
        return fieldErrors;
    }

    public int getNumberOfErrors() {
        return this.globalErrorMessages.size() + this.fieldErrors.size();
    }
	
	
}
