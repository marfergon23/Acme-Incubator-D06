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
		String emailText, displayNameText;
		
		displayNameText = object.getDisplayName() == null ? " " : String.format("%s <", object.getDisplayName());
		emailText = String.format("%s", object.getEmail());

		result = String.format("%s%s>", displayNameText, emailText);
		return result;
	}

	@Override
	public Email parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;
		
		UserAccount user = new UserAccount();
		Email result;
		String emailCodeRegex, displayNameCodeRegex, emailRegex;
		Pattern pattern;
		Matcher matcher;
		String errorMessage;
		String email, displayNameEmail;
		
		displayNameCodeRegex = user.getIdentity().getName()+ " <";
		emailCodeRegex = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
		
		emailRegex = String.format("^\\s*(?<DN>%1$s)(?<E>%2$s)\\s*$",
				displayNameCodeRegex, emailCodeRegex);
		
		pattern =  Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		matcher = pattern .matcher(text);
		
		if (!matcher.find()) {
			errorMessage = MessageHelper.getMessage("default.error.conversion", null, "Invalid value", locale);
			throw new ParseException(errorMessage, 0);
		} else {
			displayNameEmail = matcher.group("DN");
			email = matcher.group("E");
			

			result = new Email();
			result.setDisplayName(displayNameEmail);
			result.setEmail(email);
		}

		return result;
	}

}
