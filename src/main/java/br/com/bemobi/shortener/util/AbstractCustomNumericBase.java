package br.com.bemobi.shortener.util;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class AbstractCustomNumericBase {
	protected abstract String getDigits();
	
	public String convertFromLong(long n) {
		final int base = getDigits().length();

		long d = n;		
		long q;
		int r;
		Deque<Integer> v = new ArrayDeque<Integer>();

		do {
			q = d / base;
			r = (int)(d % base);
			d = q;

			v.push(r);
		} while (q > 1);

		if (q == 1) {
			v.push(1);
		}
		
		StringBuffer sb = new StringBuffer();
		while(v.size() > 0) {
			sb.append(getDigits().charAt(v.pop()));
		}

		return sb.toString();
	}
}
