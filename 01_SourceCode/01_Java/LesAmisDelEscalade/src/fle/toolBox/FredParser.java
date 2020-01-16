package fle.toolBox;

import exceptions.TypeNotConfiguredException;

/**
 * 
 * @author Frederic Leroux <br>
 * 
 * @version : 1.0
 * 
 * @Summary FredParser class : <br>
 *          Give number type intvalue(Double,Long,Float)<br>
 *          Parse string to wiched number type <br>
 *          Type Implemented : <br>
 *          Parse String to Inteteger and Inteteger(or int) to String <br>
 *          Parse String to Double and Double(or double) to String<br>
 *          Parse String to Float and Float(or float) to String <br>
 *          Parse String to Long and Long (or long) to String <br>
 */
//TODO implement other types
public class FredParser {

	public static final int toInt(String value) {
		int parsed = Integer.parseInt(value);
		return parsed;
	}

	public static final Integer toInteger(String value) {
		Integer parsed = Integer.parseInt(value);
		return parsed;
	}

	public static final Double toDouble(String value) {
		Double parsed = Double.parseDouble(value);
		return parsed;
	}

	public static final Long toLong(String value) {
		Long parsed = Long.parseLong(value);
		return parsed;
	}

	public static final Float toFloat(String value) {
		Float parsed = Float.parseFloat(value);
		return parsed;
	}

	public static final String asString(int value) {
		String parsed = String.valueOf(value);
		return parsed;
	}
	
	public static final String asString(char value) {
		String parsed = String.valueOf(value);
		return parsed;
	}

	public static final String asString(Integer value) {
		String parsed = String.valueOf(value);
		return parsed;
	}

	public static final String asString(Double value) {
		String parsed = String.valueOf(value);
		return parsed;
	}

	public static final String asString(Long value) {
		String parsed = String.valueOf(value);
		return parsed;
	}

	public static final String asString(Float value) {
		String parsed = String.valueOf(value);
		return parsed;
	}

	public static final int intValue(Double num) {
		int intValue = num.intValue();
		return intValue;
	}

	public static final int intValue(Long num) {
		int intValue = num.intValue();
		return intValue;
	}

	public static final int intValue(Float num) {
		int intValue = num.intValue();
		return intValue;
	}

	public static final Object numericParser(String type, String value) throws TypeNotConfiguredException  {
		
		Object parsedValue = null;
		String typeCompare = type.toLowerCase();		
		if(typeCompare.equals("integer")) {
			parsedValue = toInteger(value);
		}else if(typeCompare.equals("int")) {
			parsedValue = toInt(value);
		}else if(typeCompare.equals("double")) {
			parsedValue = toDouble(value);
		}else if(typeCompare.equals("long")) {
			parsedValue = toLong(value);
		}else if(typeCompare.equals("float")) {
			parsedValue = toDouble(value);
		}else {
			parsedValue = null;
		}		
		if(parsedValue==null) {
			throw new TypeNotConfiguredException();	
		}
		 
		//TODO 1- 12/12/2019 ASK AIW
		/*System.out.println("typeCompare " + typeCompare );
		
			try {
				parsedValue =  typeCompare.contains("integer") ? toInteger(value)
								: typeCompare.contains("double") ? toDouble(value)
										: typeCompare.contains("float") ? toFloat(value)
												: typeCompare.contains("long") ? toLong(value)
														:  null;
			} catch (NullPointerException e) {				
				throw new TypeNotConfiguredException();				
			}*/
		return parsedValue;

		

	}

}
