package com.rddi.registerapp.dto;

import java.util.List;

public class ValidateContractResponse {

	List<String> messages;
	List<SchemaValidationMessage> schemaValidationMessages;
	
	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<SchemaValidationMessage> getSchemaValidationMessages() {
		return schemaValidationMessages;
	}

	public void setSchemaValidationMessages(List<SchemaValidationMessage> schemaValidationMessages) {
		this.schemaValidationMessages = schemaValidationMessages;
	}

	public static class SchemaValidationMessage {
		
		private String level;
		private String domain;
		private String keyword;
		private String message;
		private Schema schema;
		private Instance instance;
		private List<String> required;
		private List<String> missing;
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public String getDomain() {
			return domain;
		}
		public void setDomain(String domain) {
			this.domain = domain;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Schema getSchema() {
			return schema;
		}
		public void setSchema(Schema schema) {
			this.schema = schema;
		}
		public Instance getInstance() {
			return instance;
		}
		public void setInstance(Instance instance) {
			this.instance = instance;
		}
		public List<String> getRequired() {
			return required;
		}
		public void setRequired(List<String> required) {
			this.required = required;
		}
		public List<String> getMissing() {
			return missing;
		}
		public void setMissing(List<String> missing) {
			this.missing = missing;
		}
	}
	
	public static class Schema {
		private String loadingURI;
		private String pointer;
		
		public String getLoadingURI() {
			return loadingURI;
		}
		public void setLoadingURI(String loadingURI) {
			this.loadingURI = loadingURI;
		}
		public String getPointer() {
			return pointer;
		}
		public void setPointer(String pointer) {
			this.pointer = pointer;
		}
	}
	
	public static class Instance {
		private String pointer;

		public String getPointer() {
			return pointer;
		}

		public void setPointer(String pointer) {
			this.pointer = pointer;
		}		
	}
	 

}
