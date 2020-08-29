package acme.components;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.format.Formatter;

import acme.datatypes.Email;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.MessageHelper;
import acme.framework.helpers.StringHelper;


public class EmailFormatter implements Formatter<Email>{

	@Override
	public String print(Email object, Locale locale) {
		assert object != null;
		assert locale != null;
		
		String result;
		String userText, domainText, displayNameText;
		
		displayNameText = object.getDisplayName() == null ? " " : String.format("%s <", object.getDisplayName());
		userText = String.format("%s", object.getUser());
		domainText = String.format("@%s", object.getDomain());
		
		result = String.format("%s%s%s>", displayNameText, userText, domainText);
		return result;
	}

	@Override
	public Email parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;
		
		UserAccount user = new UserAccount();
		Email result;
		String userCodeRegex, domainCodeRegex, displayNameCodeRegex, emailRegex;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;
		String userEmail, domainEmail, displayNameEmail;
		
		displayNameCodeRegex = user.getIdentity().getName()+ " <";
		userCodeRegex = "^((?!\\\\.)"+ user.getUsername();
		domainCodeRegex = "^(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
		
		emailRegex = String.format("^\\s*(?<DN>%1$s)(\\s+\\((?<U>%2$s)\\)\\s+|\\s+)(?<D>%3$s)\\s*$",
				displayNameCodeRegex, userCodeRegex, domainCodeRegex);
		
		pattern =  Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcher = pattern .matcher(text);
		
		if (!matcher.find()) {
			errorMessage = MessageHelper.getMessage("default.error.conversion", null, "Invalid value", locale);
			throw new ParseException(errorMessage, 0);
		} else {
			displayNameEmail = matcher.group("DN");
			userEmail = matcher.group("U");
			domainEmail = matcher.group("D");

			result = new Email();
			result.setDisplayName(displayNameEmail);
			result.setUser(userEmail);
			result.setDomain(domainEmail);
		}

		return result;
	}

}
