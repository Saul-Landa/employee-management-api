package com.slanda.employeemanagement.api.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Class for log message management
 */
@Slf4j
public class LogUtil {

    /**
     * Sends a message to the log with DEBUG level
     * @param message Message to write in the log
     */
    public static void debug(String message) {
        log.debug(StringUtil.encode(message));
    }

    /**
     * Send a message to the log with DEBUG level according to the specified format and parameters
     * @param message Message to write in the log
     * @param args List of arguments to specify in the message
     */
    public static void debug(String message, Object... args) {
        log.debug(StringUtil.encode(message, args));
    }

    /**
     * Sends a message to the log with INFO level
     * @param message Message to write in the log
     */
    public static void info(String message) {
        log.info(StringUtil.encode(message));
    }

    /**
     * Send a message to the log with INFO level according to the specified format and parameters
     * @param message Message to write in the log
     * @param args List of arguments to specify in the message
     */
    public static void info(String message, Object... args) {
        log.info(StringUtil.encode(message, args));
    }

    /**
     * Sends a message to the log with ERROR level
     * @param message Message to write in the log
     */
    public static void error(String message) {
        log.error(StringUtil.encode(message));
    }

    /**
     * Send a message to the log with INFO level according to the specified format and parameters
     * @param message Message to write in the log
     * @param args List of arguments to specify in the message
     */
    public static void error(String message, Object... args) {
        log.error(StringUtil.encode(message, args));
    }
}
