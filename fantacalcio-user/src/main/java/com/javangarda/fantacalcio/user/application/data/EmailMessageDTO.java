package com.javangarda.fantacalcio.user.application.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

public class EmailMessageDTO {

	@Getter
	private Set<String> recipientEmails;
	@Getter
	private Set<String> ccRecipientEmails;
	@Getter
	private Set<String> bccRecipientEmails;
	@Getter
	private String title;
	@Getter
	private String contentPlain;
	@Getter
	private String contentHtml;
	
	private EmailMessageDTO(EmailMessageDTOBuilder builder){
		this.recipientEmails = builder.recipients;
		this.title = builder.title;
		this.ccRecipientEmails = builder.ccRecipients;
		this.bccRecipientEmails = builder.bccRecipients;
		this.contentPlain = builder.contentPlain;
		this.contentHtml = builder.contentHtml;
	}
	
	public static EmailMessageDTOBuilder create(String title, String... recipients){
		return new EmailMessageDTOBuilder(title, recipients);
	}
	
	public static class EmailMessageDTOBuilder {
		
		private String title;
		private Set<String> recipients;
		private Set<String> ccRecipients;
		private Set<String> bccRecipients;
		private String contentPlain;
		private String contentHtml;
		
		private EmailMessageDTOBuilder(String title, String[] recipients) {
			this.title = title;
			this.recipients = new HashSet<String>(Arrays.asList(recipients));
			
		}

		public EmailMessageDTOBuilder addRecipients(String... emails){
			this.recipients.addAll(Arrays.asList(emails));
			return this;
		}
		
		public EmailMessageDTOBuilder addCcRecipients(String... emails) {
			if(this.ccRecipients==null) {
				this.ccRecipients = new HashSet<>();
			}
			this.ccRecipients.addAll(Arrays.asList(emails));
			return this;
		}
		
		public EmailMessageDTOBuilder addBccRecipients(String... emails) {
			if(this.bccRecipients==null) {
				this.bccRecipients = new HashSet<>();
			}
			this.bccRecipients.addAll(Arrays.asList(emails));
			return this;
		}
		
		public EmailMessageDTOBuilder contentPlain(String content) {
			this.contentPlain=content;
			return this;
		}
		
		public EmailMessageDTOBuilder contentHtml(String content) {
			this.contentHtml=content;
			return this;
		}
		
		public EmailMessageDTO build(){
			return new EmailMessageDTO(this);
		}
		
	}
}
