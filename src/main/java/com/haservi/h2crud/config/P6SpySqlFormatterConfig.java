package com.haservi.h2crud.config;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class P6SpySqlFormatterConfig implements MessageFormattingStrategy {

    private static final String QUERY_SLOW = "\u001B[31m" + "Slow" + "\u001B[0m";
    private static final String QUERY_FAST = "\u001B[32m" + "Fast" + "\u001B[0m";
    private static final int QUERY_CHECK_LIMIT = 1000;

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(this.getClass().getName());
    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        sql = formatSql(category, sql);
        String stackTrace = stackTrace();
        String querySpeedCheck = getQuerySpeedCheck(elapsed);
        return String.format("[%s] | %d ms, %s | %s | %s", category, elapsed, querySpeedCheck, stackTrace, formatSql(category, sql));
    }

    private static String getQuerySpeedCheck(long elapsed) {
        if (QUERY_CHECK_LIMIT < elapsed) {
            return QUERY_SLOW;
        }
        return QUERY_FAST;
    }

    private String stackTrace() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        List<StackTraceElement> filteredStackTrace = Arrays.stream(stackTrace)
                .filter(element -> element.toString().startsWith("com.haservi.h2crud")
                        && !element.toString().contains(ClassUtils.getUserClass(this).getName()))
                .toList();

        return filteredStackTrace.toString();
    }

    private String formatSql(String category, String sql) {
        if (sql != null && !sql.trim().isEmpty() && Category.STATEMENT.getName().equals(category)) {
            String trim = sql.trim().toLowerCase(Locale.ROOT);
            String formattedSql;

            if (trim.startsWith("create") || trim.startsWith("alter") || trim.startsWith("comment")) {
                formattedSql = FormatStyle.DDL.getFormatter().format(sql);
            } else {
                formattedSql = FormatStyle.BASIC.getFormatter().format(sql);
            }
            return formattedSql;
        }

        return sql;
    }

}
