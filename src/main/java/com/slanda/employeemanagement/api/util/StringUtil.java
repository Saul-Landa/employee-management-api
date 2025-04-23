package com.slanda.employeemanagement.api.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.owasp.encoder.Encode;

import static com.slanda.employeemanagement.api.util.constants.LogConstants.LINE_RETURN;

/**
 * String props class
 */
public final class StringUtil {

    /**
     * Detects escape characters and replaces them to avoid vulnerabilities,
     * applies formatting to the string
     * @param format String to be processed
     * @param args Object type arguments to be used to specify the values in the format of the string
     * @return Returns the encoded message
     */
    public static String encode(String format, Object... args) {
        return Encode.forJava(String.format(format, args));
    }

    /**
     * Detects escape characters and replaces them to avoid vulnerabilities,
     *      * applies formatting to the string
     * @param message String to be processed
     * @return Returns the encoded message
     */
    public static String encode(String message) {
        return Encode.forJava(message);
    }

    /**
     * Obtains the trace of an error in a secure manner
     * @param throwable Throwable object with trace detail
     * @return String with coded trace
     */
    public static String getSafeTrace(Throwable throwable) {
        String rep = LINE_RETURN.concat(ExceptionUtils.getStackTrace(throwable));

        return Encode.forJava(rep);
    }
}
