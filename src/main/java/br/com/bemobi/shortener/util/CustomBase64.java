package br.com.bemobi.shortener.util;

public class CustomBase64 extends AbstractCustomNumericBase {
	@Override
	protected String getDigits() {
		return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-.";
	}

}
