package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_PROJECT_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("dl/");
    public static final Prefix PREFIX_REPOURL = new Prefix("ru/");
    public static final Prefix PREFIX_PROJECT_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_PROJECT_TAG = new Prefix("tg/");
    public static final Prefix PREFIX_TASK = new Prefix("tk/");
    public static final Prefix PREFIX_TASK_FILTER_BY_ASSIGNEE = new Prefix("ta/");
    public static final Prefix PREFIX_TASK_FILTER_BY_NAME = new Prefix("tn/");
    public static final Prefix PREFIX_TASK_FILTER_BY_DEADLINE = new Prefix("td/");

}
